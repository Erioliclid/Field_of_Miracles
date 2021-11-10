package GamePackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quest {
    public String question;
    public String answer;


    public String hideAnswer = ""; //Спрятанный ответ, который постепенно открывается. Высвечивается в консоли
    char[] hideArray; //участвует в проверке правильных букв и сравнивает с ответом.
    int numberOfRightLettersAll = 0;  // Сколько букв отгадано до совершения ошибки
    int numberOfRightLetters = 0;  //Сколько букв отгадано за одно вращение барабана
    int numberOfOpenedLetters = 0;  //Сколько букв отгадано за сектор ПЛЮС
    int winScore = 0;  // сколько очков получено за угаданную букву
    List<Character> pastLetters = new ArrayList<>();  // пул уже названных букв. Предотвращает повторение

    public Quest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    static Quest q1 = new Quest("Keyword for interface inheritance", "implements");
    static Quest q2 = new Quest("A class that accommodates numbers from -2147483648 to 2147483647", "integer");
    static Quest q3 = new Quest("The scope of visibility is limited by its own class and the successor classes", "protected");
    static Quest q4 = new Quest("A keyword that limits the number of threads working with a block to one", "synchronized");
    static Quest q5 = new Quest("One of the principles of OOP", "encapsulation");

    public static Quest[] questArray = {q1, q2, q3, q4, q5};


    public String hiding() {   //прячет ответ за *
        for (int i = 0; i < questArray[Game.game.round].answer.length(); i++) {

            questArray[Game.game.round].hideAnswer = questArray[Game.game.round].hideAnswer + "*";
        }
        questArray[Game.game.round].hideArray = questArray[Game.game.round].hideAnswer.toCharArray();
        questArray[Game.game.round].hideAnswer = Arrays.toString(questArray[Game.game.round].hideArray);
        return questArray[Game.game.round].hideAnswer;
    }

    public String openLetterByNumber() throws InterruptedException {  //открывает буквы по номеру (plus)

        numberOfOpenedLetters = 0;
        if (questArray[Game.game.round].answer.charAt(Player.choice) == questArray[Game.game.round].hideArray[Player.choice]) {
            System.out.println("This letter is open already. Choose another");
            if (Game.game.qualificationQueue[Game.game.round][Game.game.move] == Player.mainPayer) {
                Player.openLetterManual();
            } else {
                Player.openLetterAuto();
            }
        } else {
            for (int i = 0; i < answer.length(); i++) {
                if (questArray[Game.game.round].answer.charAt(i) == questArray[Game.game.round].answer.charAt(Player.choice)) {
                    questArray[Game.game.round].hideArray[i] = questArray[Game.game.round].answer.charAt(Player.choice);
                    numberOfOpenedLetters++;
                }
            }
            hideAnswer = Arrays.toString(hideArray);
            if (numberOfOpenedLetters == 1) {
                System.out.println("You open the letter " + questArray[Game.game.round].answer.charAt(Player.choice));
            } else {
                System.out.println("You open " + numberOfOpenedLetters + " letter " + questArray[Game.game.round].answer.charAt(Player.choice));
            }
            System.out.println(questArray[Game.game.round].hideAnswer);
            Game.checkWinner();
        }

        return questArray[Game.game.round].hideAnswer;
    }

    public void checkRightLetters() throws InterruptedException {   //проверяет названную букву на наличие

        for (int i = 0; i < answer.length(); i++) {
            if (questArray[Game.game.round].answer.charAt(i) == Player.letter) {
                if (questArray[Game.game.round].hideArray[i] != Player.letter) {
                    questArray[Game.game.round].hideArray[i] = Player.letter;
                    questArray[Game.game.round].hideAnswer = Arrays.toString(hideArray);
                    numberOfRightLetters++;
                } else {
                    Game.waiting();
                    System.out.println("This letter is open already. Be more attentive");
                    if (Game.game.qualificationQueue[Game.game.round][Game.game.move].equals(Player.mainPayer)) {
                        Player.enterLetterManual();
                    } else {
                        Player.enterLetterAuto();
                    }
                    break;
                }
            }
        }
        if (numberOfRightLetters != 0&&Game.game.round<4) {
            scoring();
        }
        openLetter();
    }

    public int scoring() throws InterruptedException {  //подсчитывает полученные очки

        if (Game.drum.sector > 100) {
            Game.game.qualificationQueue[Game.game.round][Game.game.move].score += Game.drum.sector * numberOfRightLetters;
            winScore = Game.drum.sector * numberOfRightLetters;
        } else if (Game.drum.sector == 1 || Game.drum.sector == 4) {
            Game.game.qualificationQueue[Game.game.round][Game.game.move].score += 2000;
            winScore = 2000;
        } else if (Game.drum.sector == 5) {
            Game.game.qualificationQueue[Game.game.round][Game.game.move].score = Game.game.qualificationQueue[Game.game.round][Game.game.move].score * (2 + (numberOfRightLetters - 1));
            winScore = Game.game.qualificationQueue[Game.game.round][Game.game.move].score * (2 + (numberOfRightLetters - 1)) - Game.game.qualificationQueue[Game.game.round][Game.game.move].score;
        }
        return Game.game.qualificationQueue[Game.game.round][Game.game.move].score;
    }

    public String openLetter() throws InterruptedException {  //открывает угаданные буквы
        Game.waiting();

        if (numberOfRightLetters > 0) {

            if (numberOfRightLetters == 1) {
                System.out.println("Hey! You found new letter " + Player.letter + " and get " + winScore + " points!");
            } else {
                System.out.println("Hey! You found new " + numberOfRightLetters + " letters " + Player.letter + " and get " + winScore + " points!");
            }


            System.out.println(hideAnswer);
            numberOfRightLettersAll += numberOfRightLetters;
            numberOfRightLetters = 0;

            if (numberOfRightLettersAll >= 3 && !Game.game.winRound) {
                Game.game.hidingMoneyInTheBox();
            }

            Game.checkWinner();
            if (!Game.game.winRound) {
                if (Game.drum.sector > 100) {
                    Game.waiting();
                    System.out.println("Rotate the drum again!");
                    Game.game.gameMove();
                }
            }
        } else {

            numberOfRightLettersAll=0;
            System.out.println("The answer does not have this letter");

        }
        return questArray[Game.game.round].hideAnswer;
    }
}