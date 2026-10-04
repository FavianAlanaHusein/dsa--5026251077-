package prelab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    static void problem1() {
        List<String> playlist = new ArrayList<String>();
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("ADD")) {
                String song = sc.nextLine().trim();
                playlist.add(song);
            } else if (command.equals("INSERT")) {
                int index = sc.nextInt();
                String song = sc.nextLine().trim();
                playlist.add(index, song);
            } else if (command.equals("REMOVE")) {
                String song = sc.nextLine().trim();
                playlist.remove(song);
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Set<String> participants = new HashSet<String>();
        List<String> order = new ArrayList<String>();
        int duplicates = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();

            if (participants.contains(name)) {
                duplicates++;
            } else {
                participants.add(name);
                order.add(name);
            }
        }
        sc.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        for (int i = 0; i < order.size(); i++) {
            System.out.println((i + 1) + ". " + order.get(i));
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    static void problem3() {
        Map<String, Integer> stock = new HashMap<String, Integer>();
        List<String> order = new ArrayList<String>();
        int failedSales = 0;
        Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (sc.hasNext()) {
            String type = sc.next();
            String product = sc.next();
            int quantity = sc.nextInt();

            if (type.equals("ADD")) {
                if (!stock.containsKey(product)) {
                    stock.put(product, 0);
                    order.add(product);
                }
                stock.put(product, stock.get(product) + quantity);
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= quantity) {
                    stock.put(product, stock.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println("===== Problem 3 =====");
        for (int i = 0; i < order.size(); i++) {
            String product = order.get(i);
            System.out.println(product + ": " + stock.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}