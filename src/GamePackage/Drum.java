package GamePackage;

import java.util.Random;

public class Drum {

    static public int trueKey; // прячет настоящий ключ от машины
    int sector; // сектор барабана

    // 1 - priseBox, 2 - plus, 3 - bankrupt, 4 - key, 5 - twice
    final int[] drumPoints = {0, 350, 400, 450, 500, 550, 600, 750, 800, 850, 1000, 1, 2, 3, 4, 5};


    public void rotate(Player test) throws InterruptedException {
        Random random = new Random();
        sector = drumPoints[random.nextInt(drumPoints.length)];

        if (sector == 0) {
            System.out.println("Sector Zero! You're missing a move.");
            System.out.println();

        } else if (sector == 350) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();

            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 400) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 450) {
            System.out.println("Sector " + sector + "! Choose a letter.");

            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 500) {
            System.out.println("Sector " + sector + "! Choose a letter.");

            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 550) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 600) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 750) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

        } else if (sector == 800) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }


        } else if (sector == 850) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }


        } else if (sector == 1000) {
            System.out.println("Sector " + sector + "! Choose a letter.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }

//________________prise________________________________________________________________________________________________________________________________________________________________
        } else if (sector == 1) {
            int moneyToGive;
            int delta;
            moneyToGive = random.nextInt(10) * 100;
            System.out.println("Sector PRISE! Do you want to try? (Y/N)");

            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.yesOrNoManual();
            } else {
                Player.yesOrNoAuto();
            }
            Game.waiting();
            if (Player.choice == 1) {
                System.out.println("What do you prefer? prise or " + moneyToGive + " rubles");
                System.out.println();


                int choices = 0;
                while (Player.choice == 2 && choices < 3) {
                    Game.waiting();
                    delta = random.nextInt(50) * 100;
                    moneyToGive += delta;
                    System.out.println("Ok. What if I give you " + moneyToGive + "?");
                    if (test.equals(Player.mainPayer)) {
                        Player.chosePriseBoxManual();
                    } else {
                        Player.chosePriseAuto();
                    }
                    choices++;
                    Game.waiting();
                }

                if (Player.choice == 1) {
                    Game.waiting();
                    System.out.println("Congratulation! you won " + moneyToGive + " rubles!");
                    test.money += moneyToGive;
                } else {
                    Game.waiting();
                    System.out.print("I give up and you can take your prise. You won ");
                    Game.waiting();
                    System.out.print(".");
                    Game.waiting();
                    System.out.print(".");
                    Game.waiting();
                    System.out.println(". ");
                    PriseBank prise = PriseBank.prisesRoom[random.nextInt(PriseBank.prisesRoom.length)];
                    test.prises.add(prise);
                    System.out.println((prise) + ". Congratulation!");
                    Game.waiting();
                    System.out.println("Take you prise and leave our show");
                    Game.game.qualificationQueue[Game.game.round][Game.game.move] = null;
                }
            } else {
                System.out.println("Well, choose a letter.");
                Game.waiting();
                if (test.equals(Player.mainPayer)) {
                    Player.enterLetterManual();
                } else {
                    Player.enterLetterAuto();
                }
            }

//________________plus_________________________________________________________________________________________________________________________________________________________________
        } else if (sector == 2) {
            System.out.println("Wow! sector PLUS! you can choose any letter and we open it.");
            Game.waiting();
            if (test.equals(Player.mainPayer)) {
                Player.openLetterManual();
            } else {
                Player.openLetterAuto();
            }

//________________bankrupt_____________________________________________________________________________________________________________________________________________________________

        } else if (sector == 3) {
            System.out.println("What's a pity! Sector BANKRUPT! All your score gone.");
            Game.game.qualificationQueue[Game.game.round][Game.game.move].score = 0;

//________________key__________________________________________________________________________________________________________________________________________________________________
        } else if (sector == 4) {

            System.out.println("Sector KEY! Do you want to try? (Y/N)");
            Game.waiting();

            if (test.equals(Player.mainPayer)) {
                Player.yesOrNoManual();
            } else {

                Player.yesOrNoAuto();
            }
            Game.waiting();
            if (Player.choice == 1) {

                trueKey = random.nextInt(6) + 1;
                System.out.println("Choose one from six keys and enter number of this. if you can open a car by this key, you can take this car");
                if (test.equals(Player.mainPayer)) {
                    Player.findKeyManual();
                } else {
                    Player.findKeyAuto();
                }
                Game.waiting();
                if (Player.choice == trueKey) {
                    System.out.println("Unbelievable! You open a car! You get a car!!!");
                    Game.game.qualificationQueue[Game.game.round][Game.game.move].prises.add(PriseBank.car);
                    System.out.println("Take you prise and leave our show");
                    Game.game.qualificationQueue[Game.game.round][Game.game.move] = null;
                } else {
                    System.out.println("Wrong key");
                }
            } else {
                System.out.println("Well, choose a letter.");
                Game.waiting();
                if (test.equals(Player.mainPayer)) {
                    Player.enterLetterManual();
                } else {
                    Player.enterLetterAuto();
                }
            }

//________________twice________________________________________________________________________________________________________________________________________________________________
        } else if (sector == 5) {
            System.out.println("Fantastic! sector TWICE! Your score x2, and you can choose a letter!");
            Game.game.qualificationQueue[Game.game.round][Game.game.move].score = Game.game.qualificationQueue[Game.game.round][Game.game.move].score * 2;

            if (test.equals(Player.mainPayer)) {
                Player.enterLetterManual();
            } else {
                Player.enterLetterAuto();
            }
        }
    }
}