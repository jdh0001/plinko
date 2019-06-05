import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Plink {

    public static void main(final String[] args) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        String again = "y";

        int total = 6;
        int plays = 0;
        int previous = -1;
        Scanner scan = new Scanner(System.in);
        boolean twoToken = false;
        boolean freebie = false;
        boolean hide = false;
        boolean dontAsk = false;
        int earned = 0;

        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_PURPLE = "\u001B[35m";
        String theme = ANSI_WHITE;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> emptyList = new ArrayList<String>();
        ArrayList<Integer> pointList = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<String> collection = new ArrayList<String>();

        String skin = "O";
        collection.add(skin);
        map.put(1, 3);
        map.put(2, 2);
        map.put(3, 5);
        map.put(4, 0);
        map.put(5, 5);
        map.put(6, 1);
        map.put(7, 4);
        map.put(8, 9);
        map.put(9, 2);
        map.put(10, 0);
        map.put(11, 0);
        map.put(12, 2);
        map.put(13, 9);
        map.put(14, 4);
        map.put(15, 1);
        map.put(16, 5);
        map.put(17, 0);
        map.put(18, 5);
        map.put(19, 2);
        map.put(20, 3);

        for (int i = 0; i < 20; i++) {
            pointList.add(i, map.get(i + 1));
        }
        while (total >= 2) {
            int gameCost = 0;

            if (plays > 0) {
                Scanner s = new Scanner(System.in);
                if (plays % 3 == 0) {
                    System.out.println("Play Again? (2 Credits) y/n/gamble/box : ");
                    System.out.println("Loot box available with selection \"b\"");
                }
                else if (!hide) {
                    System.out.println("Play Again? (2 Credits) y/n/gamble/skins : ");
                }
                else {
                    System.out.println("Play Again? (2 Credits) y/n/gamble/skins/unhideShop : ");
                }
                again = s.nextLine();
            }
            if (again.equals("n")) {
                System.out.println("\nBye");
                break;
            }
            if (again.contains("u")) {
                hide = false;
            }
            if (again.contains("s") && !collection.isEmpty()) {
                System.out.println("Select skin: ");
                for (int i = 0; i < collection.size(); i++) {
                    System.out.print((i + 1) + ": " + collection.get(i) + " , ");
                }
                Scanner s = new Scanner(System.in);
                int choice = s.nextInt();
                skin = collection.get(choice - 1);
                System.out.println("Skin Updated: " + skin);
            }
            else if (again.contains("s")) {
                System.out.println("No skins in inventory");
            }

            if (again.contains("b") && plays % 3 == 0) {
                System.out.print("\033[H\033[2J");
                System.out.println(" __________\n" + "|         |\n" + "|  loot   |\n" + "|_________|\n" + "|         |\n" + "|         |\n" + "|_________|");
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\033[H\033[2J");
                System.out.println("___________\n" + "|loot __--/  \n" + "|  __/\n" + "|_/________\n" + "|         |\n" + "|         |\n" + "|_________|");
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\033[H\033[2J");
                ArrayList<String> lootbox = new ArrayList<String>(Arrays.asList("B", "50 credits", "+", "!", "%", "~", "&", "*", ANSI_YELLOW + "$" + theme,
                        "         ___------__\n" + "  |\\__-- /\\       _ -\n" + "  |/_   __       _\n" + "  // \\ /  \\     /__\n" + "  |  o|  0|__      --_\n"
                                + "  \\\\____-/ __ \\   ___ -\n" + "  (@@   ___/  / /_\n" + "     -_____---   --_\n" + "     //  \\ \\\\       -\n"
                                + "   //|\\__/  \\\\   ___ -\n" + "   \\_\\\\_____/  \\-|\n" + "       // \\\\--\\|\n" + "  ____//  ||_\n"
                                + " /___//_\\ /__|"));
                Random rand = new Random();
                int prize = rand.nextInt(lootbox.size());

                System.out.println("___________\n" + "|loot __--/  \n" + "|  __/ " + lootbox.get(prize) + "   \n" + "|_/________\n" + "|         |\n"
                        + "|         |\n" + "|_________|");
                TimeUnit.SECONDS.sleep(1);

                if (!lootbox.get(prize)
                        .contains("credits")) {
                    System.out.println("Press 1 for OK, 2 for Select Skin");
                    Scanner s = new Scanner(System.in);
                    String ok = s.nextLine();
                    if (ok.contains("2")) {
                        skin = lootbox.get(prize);
                    }
                    collection.add(skin);
                }
                else {
                    total += 50;
                }

            }
            if (again.contains("g")) {
                System.out.println("Winning chance: 1/3 , Payout: x3");
                System.out.println("You have: " + (total - 2) + " , Bet: ");

                Scanner s = new Scanner(System.in);
                int bet = s.nextInt();
                if (bet > total - 2) {
                    System.out.println("Bet reduced to \"all in\"");
                    bet = total - 2;
                }
                if (bet < 0) {
                    bet = 0;
                }
                Random rand = new Random();
                int luck = rand.nextInt(2);
                if (luck == 0) {
                    total += (bet * 2);
                    System.out.println("You win. New Total: " + (total - 2));
                }
                else {
                    System.out.println("You lose.");
                    total -= bet;
                    if (total < 2) {
                        System.out.println("Insufficient Credits Remaining.");
                        break;
                    }
                }
            }

            if (!freebie) {
                twoToken = false;
            }

            if (total - 2 >= 10 && !hide) {
                Scanner s = new Scanner(System.in);
                System.out.println("Visit Shop? (y/n/hide forever)");
                String visit = s.nextLine();
                if (visit.contains("h")) {
                    hide = true;
                }
                if (visit.contains("y")) {
                    HashMap<Integer, Integer> cost = new HashMap<Integer, Integer>();
                    ArrayList<String> skins = new ArrayList<String>();
                    skins.add(ANSI_YELLOW + "#" + theme);
                    skins.add(ANSI_YELLOW + "@" + theme);
                    skins.add(ANSI_PURPLE);
                    skins.add(ANSI_GREEN);
                    cost.put(1, 100);
                    cost.put(2, 100);
                    cost.put(3, 1000);
                    cost.put(4, 1000);
                    System.out.println("\nCredits: " + total);
                    System.out.println("Choose a Skin (1: # :" + cost.get(1) + " Credits , 2: @ :" + cost.get(2) + " Credits)");
                    System.out.println("Or Recolor the Board (3: Purple : " + cost.get(3) + " Credits , 4: Green : " + cost.get(4) + " Credits)");
                    System.out.println("9 to exit");

                    int choice = s.nextInt();
                    if (choice >= 3 && choice != 9) {
                        if ((total - 2) >= cost.get(choice)) {
                            total -= cost.get(choice);
                            theme = skins.get(choice - 1);
                            System.out.println(theme + "Color Changed.");
                        }
                        else {
                            System.out.println("Insufficient Credits");
                            System.out.println("Choose a Skin (1,2): ");
                            System.out.println(skins.toString());
                            choice = s.nextInt();
                            if ((total - 2) >= cost.get(choice)) {
                                total -= cost.get(choice);
                                theme = skins.get(choice - 1);
                                System.out.println("Color Changed.");
                            }
                        }
                    }
                    if (choice <= 2) {
                        if ((total - 2) >= cost.get(choice)) {
                            total -= cost.get(choice);
                            skin = skins.get(choice - 1);
                            collection.add(skin);
                            System.out.println("Skin acquired: " + skins.get(choice - 1));
                        }
                        else {
                            System.out.println("Insufficient Credits");
                            System.out.println("Choose a Skin (1,2): ");
                            System.out.println(skins.toString());
                            choice = s.nextInt();
                            if ((total - 2) >= cost.get(choice)) {
                                total -= cost.get(choice);
                                skin = skins.get(choice - 1);
                                System.out.println("Skin acquired: " + skins.get(choice - 1));
                            }
                        }
                    }

                }
            }

            list.clear();
            emptyList.clear();
            for (int i = 0; i < 20; i++) {
                list.add("U");
                emptyList.add("U");
            }

            total -= 2;
            gameCost += 2;
            if (total >= 3 && plays > 0 && !dontAsk) {
                System.out.println(
                        "Activate Double Token Mode for 3 Points? Current Total: " + total + "\nLocation of Second Token is Random.\n(y/n/assumeDouble): ");
                Scanner in = new Scanner(System.in);
                String pay = in.nextLine();
                if (pay.contains("a")) {
                    dontAsk = true;
                    twoToken = true;
                    total -= 3;
                    gameCost += 3;
                    System.out.println("Always Double.");
                }
                if (pay.contains("y")) {
                    twoToken = true;
                    total -= 3;
                    gameCost += 3;
                    System.out.println("Activated.");
                }
                else if (freebie = false) {
                    twoToken = false;
                }
            }
            else if (total >= 3 && plays > 0 && dontAsk) {
                twoToken = true;
                total -= 3;
                gameCost += 3;
            }

            System.out.println(theme + "Credits: " + total);
            if (plays == 0) {
                System.out.println("First Game is Free.");
            }
            System.out.println("\nStart position 1-20: ");

            int position = scan.nextInt();
            Random rand = new Random();
            int position2 = position;
            while (position2 == position) {
                position2 = rand.nextInt(19) + 1;
            }

            //DOUBLE TOKEN?
            if (twoToken) {
                list.remove(position2 - 1);
                list.add(position2 - 1, skin);
            }

            if (position > 20) {
                position = 20;
            }
            else if (position < 1) {
                position = 1;
            }
            list.remove(position - 1);
            list.add(position - 1, skin);

            System.out.println("\033[H\033[2J");
            int line = 0;
            if (line == 0) {
                //PRINT INITIAL LIST
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;
            if (line == 1) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;
            if (line == 2) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;

            if (line == 3) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;

            if (line == 4) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;

            if (line == 5) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;

            if (line == 6) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;

            if (line == 7) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }
                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.print(theme + "\033[H\033[2J");
            line++;

            if (line == 8) {
                //CALCULATE NEXT POSITION
                list.remove(position - 1);
                list.add(position - 1, "U");
                position = calcPos(position);
                list.remove(position - 1);
                list.add(position - 1, skin);

                if (twoToken) {
                    list.remove(position2 - 1);
                    list.add(position2 - 1, "U");
                    position2 = calcPos(position2);
                    while (position2 == position) {
                        position2 = calcPos(position2);
                    }
                    list.remove(position2 - 1);
                    list.add(position2 - 1, skin);
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(emptyList.get(i));
                }

                System.out.println();
                for (int i = 0; i < list.size(); i++) { //TOKEN LIST
                    if (list.get(i)
                            .equals(skin)) {
                        System.out.print(ANSI_CYAN + list.get(i) + theme);
                    }
                    else {
                        System.out.print(list.get(i));
                    }
                }
                System.out.println(ANSI_YELLOW);
                for (int i = 0; i < pointList.size(); i++) {
                    System.out.print(pointList.get(i)
                            .toString());
                }
            }

            if (twoToken) {
                earned = map.get(position) + map.get(position2);
                if (skin.contains("$")) {
                    earned = earned * 2;
                }
                total += earned;

            }
            else {
                earned = map.get(position);
                if (skin.contains("$")) {
                    earned = earned * 2;
                }
                total += earned;
            }
            System.out.println(theme + "\n\nEarned: " + earned + " points after spending " + gameCost);
            System.out.println("Total: " + total + " points in " + (plays + 1) + " play/s");
            plays++;
            if (previous == earned) {
                System.out.println("Two in a row!");
                if (previous == 0 && total >= 2) {
                    System.out.println("Two zeros? Double Token Activated.");
                    freebie = true;
                    twoToken = true;
                }
                else {
                    freebie = false;
                    twoToken = false;
                }
            }
            previous = earned;
            if (total < 2) {
                System.out.println("Insufficient Credits for New Game");
            }
        }
    }

    private static int calcPos(int position) {
        // TODO Auto-generated method stub
        Random rand = new Random();
        boolean right = rand.nextBoolean();
        if (right) {
            position++;
            if (position > 20) {
                position = 20;
            }
        }
        else {
            position--;
            if (position < 1) {
                position = 1;
            }
        }
        return position;
    }
}
