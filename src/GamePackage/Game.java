package GamePackage;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    static Drum drum = new Drum();
    static Quest quest;
    int round;  //текущий раунд
    int move;   //Текущий ход
    static Game game = new Game();
    boolean winRound;   //проверка на победу в раунде
    int nullPlayer;   //Игрок, забравший приз или машину
    int moneyBox;  //шкатулка на три правильных ответа

    public Player[][] qualificationQueue = new Player[4][3];



    public static void waiting() throws InterruptedException {
            Thread.sleep(1000);
    }


    static void distributionByTurn() {  //распределение игроков по раундам и местам
        Random random = new Random();
        List<Player> playerListTemp = Player.playerList;
        for (int i = 0; i < game.qualificationQueue.length - 1; i++) {
            for (int j = 0; j < game.qualificationQueue[i].length; j++) {
                int randomPlayer = random.nextInt(playerListTemp.size());
                game.qualificationQueue[i][j] = playerListTemp.get(randomPlayer);
                playerListTemp.remove(randomPlayer);
            }
        }
    }

    void greeting() throws InterruptedException {   //приветствие
        System.out.println();
        System.out.println("Welcome to the Field of Miracles game!!!");
        System.out.println("We are playing round " + (round + 1));
        Game.waiting();
        if (round < 4) {
            System.out.println();
            System.out.println("Our players are:");
            Game.waiting();
            System.out.println(qualificationQueue[round][0] + " by the first table!");
            Game.waiting();
            System.out.println(qualificationQueue[round][1] + " by the second table!");
            Game.waiting();
            System.out.println(qualificationQueue[round][2] + " by the third table!");
            Game.waiting();
        } else {
            System.out.println("Our player is: " + Player.mainPayer);
        }

        System.out.println();
        System.out.println("The question is:");
        Game.waiting();
        System.out.println(Quest.questArray[round].question);
        Game.waiting();
        System.out.println();
        System.out.println("look at the board. The answer has " + Quest.questArray[round].answer.length() + " letters.");
        Quest.questArray[round].hiding();
        System.out.println(Quest.questArray[round].hideAnswer);
        Game.waiting();

    }

    void roundsByThree() throws InterruptedException {

        for (game.round = 0; game.round < qualificationQueue.length; game.round++) {

            if (round == qualificationQueue.length - 1) {  //пееход к финальному раунду
                finalRound();
            }

            quest = Quest.questArray[round];  //присвоение вопроса раунду
            winRound = false;
            nullPlayer = 0;
            //обнуление проверок на выигранный раунд и пустые места

            greeting();

            while (!winRound) { // очередность хода
                for (game.move = 0; game.move < qualificationQueue[round].length; game.move++) {
                    gameMove();
                    if (winRound) {
                        Quest.questArray[round].hideAnswer = "";
                        break;
                    }
                }
            }
        }

        PriseBank.takePrises();  //выбор призов после победы
        superFinal();  //суперигра
    }

    void finalRound() {
//        ищем победителя в каждом раунде и добавляем в список игроков и список победителей
        for (int i = 0; i < game.qualificationQueue.length - 1; i++) {
            for (int j = 0; j < game.qualificationQueue[i].length; j++) {
                if (game.qualificationQueue[i][j] != null && game.qualificationQueue[i][j].winner) {
                    Player.playerList.add(game.qualificationQueue[i][j]);
                    Player.winnerList.add(game.qualificationQueue[i][j]);
                }
            }
        }
//        Добавляем победителей в четвёртый раунд и обнуляем значение победителя
        for (int i = 0; i < qualificationQueue[qualificationQueue.length - 1].length; i++) {
            qualificationQueue[qualificationQueue.length - 1][i] = Player.winnerList.get(i);
            qualificationQueue[qualificationQueue.length - 1][i].winner = false;
        }
    }

    void superFinal() throws InterruptedException { //суперигра
        Scanner scanner = new Scanner(System.in);
        if (Player.mainPayer.winner) {
            System.out.println("Do you want to play SuperGame?");
            Player.yesOrNoManual();
            if (Player.choice == 1) {
                int moves = 0; //подсчёт открытых букв
                greeting();
                while (moves < 8) { //количество разрешенных букв
                    Player.enterLetterManual();
                    System.out.println(Quest.questArray[round].hideAnswer);
                    moves++;
                }
                System.out.println("You should say this word");
                String playerAnswer = scanner.nextLine();
                if (playerAnswer.equalsIgnoreCase(Quest.questArray[round].answer)) {
                    System.out.println("you Are winner!!!");
                }
            }
        }
    }

    void gameMove() throws InterruptedException { //ход игрока

        if (qualificationQueue[round][move] != null) { //проверка на отсутствующего игрока
            if (nullPlayer == 2) {
                qualificationQueue[round][move].winner = true;
                winRound = true;
                //Если двух игроков нет, то третий выигрывает, раунд заканчивается
            } else {
                nullPlayer = 0; //обнуление подсчёта пустых мест
                System.out.println();
                Game.waiting();
                System.out.println(Game.quest.hideAnswer);
                System.out.println(qualificationQueue[round][move] + " rotate the drum!");
                System.out.println(qualificationQueue[round][move].score + " score");
                System.out.println(qualificationQueue[round][move].money + " money");
                for (PriseBank prise : qualificationQueue[round][move].prises) {
                    System.out.println(prise);
                }
                System.out.println();
                Game.waiting();
                drum.rotate(qualificationQueue[round][move]);
            }
        } else {
            nullPlayer++;
            if (nullPlayer == 3) {
                winRound = true;
            }
        }
    }

    void hidingMoneyInTheBox() throws InterruptedException {  // прячет деньги в одну из двух шкатулок
        Random random = new Random();
        Game.waiting();
        System.out.println();
        System.out.println("Three right letters gave you a chance to win money!");
        System.out.println("Choose one box from two.");
        moneyBox = random.nextInt(2) + 1;
        if (Game.game.qualificationQueue[Game.game.round][Game.game.move] == Player.mainPayer) {
            Player.choseBoxManual();
        } else {
            Player.choseBoxAuto();
        }
    }

    void checkRightBox() throws InterruptedException {  //проверка угадал ли игрок шкатулку
        Game.waiting();
        if (Player.choice == moneyBox) {
            qualificationQueue[round][move].money += 5000;
            System.out.println("You open box with a money! You got 5000 rubles");
        } else {
            System.out.println("Money was in another box");
        }
        quest.numberOfRightLettersAll = 0;
    }

    public static boolean checkWinner() throws InterruptedException {  // проверка на победителя
        int checkFullOpenAnswer = 0;
        for (int i = 0; i < Quest.questArray[Game.game.round].answer.length(); i++) { //проверка на количество открытых букв в ответе
            if (Quest.questArray[Game.game.round].answer.charAt(i) == Quest.questArray[Game.game.round].hideArray[i]) {
                checkFullOpenAnswer++;
            }
        }
        if (checkFullOpenAnswer == Quest.questArray[Game.game.round].answer.length()) {
            game.winRound = true;
            game.qualificationQueue[game.round][game.move].winner = true;
            System.out.println();
            System.out.println( game.qualificationQueue[game.round][game.move] +" won this round!");

        }
        return game.winRound;
    }

    public static void main(String[] args) throws InterruptedException {
        Player.createNewPlayer();
        Player.addPlayerList();
        distributionByTurn();
        game.roundsByThree();
    }
}
