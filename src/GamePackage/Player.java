package GamePackage;

import java.util.*;

public class Player {

    String firstName;
    char gender;
    int age;
    boolean winner = false;

    int score = 0;
    int money = 0;

    List<PriseBank> prises = new ArrayList<>();


    static char letter;
    static int choice;

    public Player() {
    }

    public Player(String firstName, char gender, int age) {
        this.firstName = firstName;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return
                firstName + " " + gender + " " + age;
    }

    static Player p1 = new Player("Helen", 'W', 28);
    static Player p2 = new Player("Alex", 'M', 35);
    static Player p3 = new Player("Lesly", 'W', 24);
    static Player p4 = new Player("Jack", 'M', 50);
    static Player p5 = new Player("Helga", 'W', 42);
    static Player p6 = new Player("Amanda", 'W', 37);
    static Player p7 = new Player("John", 'M', 25);
    static Player p8 = new Player("Steven", 'M', 40);
    static Player test = new Player("test", 'T', 0);
    static Player mainPayer = new Player();

    static List<Player> playerList = new ArrayList<>();
    static List<Player> winnerList = new ArrayList<>();

    static public void addPlayerList() { //состав игроков на игру
        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);
        playerList.add(p4);
        playerList.add(p5);
        playerList.add(p6);
        playerList.add(p7);
        playerList.add(p8);
        playerList.add(mainPayer);
    }

    public static Player createNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! You are a new player, right? Let's get acquainted.");
        System.out.print("Enter your name: ");
        mainPayer.firstName = scanner.nextLine();
        System.out.print("Enter your gender, " + mainPayer.firstName + ": ");
        mainPayer.gender = scanner.nextLine().charAt(0);
        System.out.print("And last but not least, enter your age: ");
        try {
            mainPayer.age = scanner.nextInt();
            System.out.println("Nice to meet you, " + mainPayer.firstName + ". Welcome to Field of Miracles!!!");
        } catch (InputMismatchException e) {
            System.out.println("Use numbers to enter your age");
            scanner.nextLine();
        }
        return mainPayer;
    }

    public static char enterLetterManual() throws InterruptedException {  // игрок-человек называет букву
        Scanner scanner = new Scanner(System.in);
        String letter2 = "";


        boolean loop = true;
        System.out.println("Enter chosen letter");
        while (loop) {
            letter2 = scanner.nextLine();
            try {
                if (letter2.charAt(0) >= 'a' && letter2.charAt(0) <= 'z' || letter2.charAt(0) >= 'A' && letter2.charAt(0) <= 'Z') {
                    loop = false;
                } else {
                    System.out.println("This is not a latin letter");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please, enter a letter");
            }

        }
        letter2 = letter2.toLowerCase();
        letter = letter2.charAt(0);
        Game.quest.checkRightLetters();
        return letter;
    }

    public static int choseBoxManual() throws InterruptedException {  // игрок-человек выбирает шкатулку
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            try {
                choice = scanner.nextInt();

                if (choice < 1) {
                    System.out.println("You should choose first or second box. Enter number of chosen box");
                } else if (choice > 2) {
                    System.out.println("You should choose first or second box. Enter number of chosen box");
                } else {
                    loop = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should choose first or second box. Enter number of chosen box");
                scanner.nextLine();
            }
        }
        System.out.println(choice);
        Game.game.checkRightBox();
        return choice;
    }

    public static int chosePriseBoxManual() { // игрок-человек выбирает деньги или приз
        Scanner scanner = new Scanner(System.in);
        String priseOrMoney;

        boolean loop = true;
        while (loop) {
            System.out.println("Enter your choice");
            priseOrMoney = scanner.nextLine();
            if (priseOrMoney.equalsIgnoreCase("money")) {
                choice = 1;
                loop = false;
            } else if (priseOrMoney.equalsIgnoreCase("prise")) {
                choice = 2;
                loop = false;
            } else {
                System.out.println("You should choose between prise and money. Write your choice");
            }
        }
        System.out.println(choice);
        return choice;
    }

    public static int findKeyManual() {  // игрок-человек выбирает ключ
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            try {
                choice = scanner.nextInt();

                if (choice < 1) {
                    System.out.println("You should choose only one key of the six. Enter number of chosen key");
                } else if (choice > 9) {
                    System.out.println("You should choose only one key of the six. Enter number of chosen key");
                } else {
                    loop = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should choose only one key of the six. Enter number of chosen key");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public static int openLetterManual() throws InterruptedException {  // игрок-человек выбирает номер буквы в слове, чтобы открыть (plus)
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            try {
                choice = scanner.nextInt() - 1;

                if (choice < 0) {
                    System.out.println("You should choose only one letter of the answer. Enter number of chosen letter");
                } else if (choice >= Game.quest.answer.length()) {
                    System.out.println("You should choose only one letter of the answer. Enter number of chosen letter");
                } else {
                    loop = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should choose only one letter of the answer. Enter number of chosen letter");
                scanner.nextLine();
            }
        }
        Thread.sleep(1000);
        Game.quest.openLetterByNumber();
        return choice;
    }

    public static int yesOrNoManual() throws InterruptedException {       // игрок-человек отвечает да или нет

        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {

            String answer = scanner.nextLine();
            if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y') {
                choice = 1;
                loop = false;
            } else if (answer.charAt(0) == 'n' || answer.charAt(0) == 'N') {
                choice = 2;
                loop = false;
            } else {
                System.out.println("Wrong answer");
            }
        }
        return choice;
    }

    public static int takePriseManual() {   // игрок-человек выбирает призы в конце игры
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            try {
                choice = scanner.nextInt();

                if (choice < 1) {
                    System.out.println("Wrong number of the prise");
                } else if (choice > 9) {
                    System.out.println("Wrong number of the prise");
                } else {
                    loop = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong number of the prise");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public static char enterLetterAuto() throws InterruptedException {  // игрок-робот называет букву
        Random random = new Random();
        int unicode = random.nextInt(26) + 97;
        letter = (char) unicode;

        while (Game.quest.pastLetters.contains(letter)) {
            unicode = random.nextInt(26) + 97;
            letter = (char) unicode;
        }

        System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": The letter  " + Player.letter);
        Game.quest.pastLetters.add(letter);
        Game.quest.checkRightLetters();
        return letter;
    }

    public static int choseBoxAuto() throws InterruptedException { // игрок-робот выбирает шкатулку
        Random random = new Random();
        choice = random.nextInt(2) + 1;
        System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": The box number " + choice);
        Game.game.checkRightBox();
        return choice;
    }

    // 1 - money, 2 - prise
    public static int chosePriseAuto() { // игрок-робот выбирает деньги или приз
        Random random = new Random();
        choice = random.nextInt(2) + 1;
        if (choice == 1) {
            System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": I choose money");
        } else {
            System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": I choose prise");
        }

        return choice;
    }

    public static int findKeyAuto() {   // игрок-робот выбирает ключ
        Random random = new Random();
        choice = random.nextInt(6) + 1;
        System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": I choose key number " + choice);
        return choice;
    }

    public static int openLetterAuto() throws InterruptedException {  // игрок-робот выбирает номер буквы в слове, чтобы открыть (plus)

        Random random = new Random();
        choice = random.nextInt(Quest.questArray[Game.game.round].answer.length());
        while (Quest.questArray[Game.game.round].hideArray[choice] == Quest.questArray[Game.game.round].answer.charAt(choice)) {
            choice = random.nextInt(Quest.questArray[Game.game.round].answer.length());
        }
        System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": I choose letter number " + (choice + 1));
        Game.quest.openLetterByNumber();
        return choice;
    }

    public static int yesOrNoAuto() throws InterruptedException {   // игрок-робот отвечает да или нет

        Random random = new Random();
        choice = random.nextInt(2) + 1;
        if (choice == 1) {
            System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": Yes");
        } else {
            System.out.println(Game.game.qualificationQueue[Game.game.round][Game.game.move] + ": No");
        }
        return choice;
    }

    public static int takePriseAuto() {   // игрок-робот выбирает призы в конце игры
        Random random = new Random();
        choice = random.nextInt(15);
        return choice;
    }
}