package GamePackage;

public class PriseBank {
    String name;
    int cost;

    @Override
    public String toString() {
        return name;
    }

    public PriseBank(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    static PriseBank flat = new PriseBank("flat", 100_000);
    static PriseBank bike = new PriseBank("bike", 40_000);
    static PriseBank SetOfHouseholdAppliances = new PriseBank("SetOfHouseholdAppliances", 5_000);
    static PriseBank TripToPetersburg = new PriseBank("TripToPetersburg", 4_500);
    static PriseBank ElectricFireplace = new PriseBank("ElectricFireplace", 3_500);
    static PriseBank laptop = new PriseBank("laptop", 2_500);
    static PriseBank ElectricScooter = new PriseBank("ElectricScooter", 2_300);
    static PriseBank smartTV = new PriseBank("smartTV", 2_000);
    static PriseBank TravelKit = new PriseBank("TravelKit", 1_800);
    static PriseBank SmartSpeaker = new PriseBank("SmartSpeaker", 1_400);
    static PriseBank smartphone = new PriseBank("smartphone", 1_200);
    static PriseBank MusicSynthesizer = new PriseBank("MusicSynthesizer", 900);
    static PriseBank HealthBasket = new PriseBank("HealthBasket", 450);
    static PriseBank MotoristsKit = new PriseBank("MotoristsKit", 300);
    static PriseBank surprise = new PriseBank("surprise", 200);


    static PriseBank car = new PriseBank("car", 1_000_000);

    static PriseBank[] prisesRoom = {flat, bike, SetOfHouseholdAppliances, TripToPetersburg, ElectricFireplace, laptop, ElectricScooter, smartTV, TravelKit, SmartSpeaker, smartphone, MusicSynthesizer, HealthBasket, MotoristsKit, surprise};

    public static void showPrises() throws InterruptedException {
        Game.waiting();
        System.out.println();
        for (int i = 0; i < Game.game.qualificationQueue[3].length; i++) {
            if (Game.game.qualificationQueue[3][i] != null && Game.game.qualificationQueue[3][i].winner) {
                System.out.println("You won this game and you have " + Game.game.qualificationQueue[3][i].score+" points");
                System.out.println("Choose prises:");
                for (int j = 0; j < prisesRoom.length; j++) {
                    System.out.println(j + 1 + "  " + prisesRoom[j] + "    " + prisesRoom[j].cost + " points");
                }
            }
        }
    }

    public static void takePrises() throws InterruptedException {
        showPrises();
        Game.waiting();
        System.out.println();
        for (int i = 0; i < Game.game.qualificationQueue[3].length; i++) {
            if (Game.game.qualificationQueue[3][i] != null && Game.game.qualificationQueue[3][i].winner) {

                while (Game.game.qualificationQueue[3][i].score >= 200) {
                    if (Game.game.qualificationQueue[3][i].equals(Player.mainPayer)) {
                        Player.takePriseManual();
                    } else {
                        Player.takePriseAuto();
                    }
                    if (Game.game.qualificationQueue[3][i].score >= prisesRoom[Player.choice].cost) {

                        System.out.println(Game.game.qualificationQueue[3][i] + ": I choose: " + PriseBank.prisesRoom[Player.choice]);
                        Game.waiting();
                        Game.game.qualificationQueue[3][i].prises.add(prisesRoom[Player.choice]);
                        Game.game.qualificationQueue[3][i].score -= prisesRoom[Player.choice].cost;
                    }
                }
            }
        }
    }
}