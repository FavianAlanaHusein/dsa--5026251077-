package lw04.unguided;

import java.util.Scanner;

public class Main {

    static void sortingCards(int[] card, int passes) {
        int totalCard = card.length;

        for (int pass = 0; pass < passes; pass++) {
            int indexTerkecil = pass;

            for (int next = pass + 1; next < totalCard; next++) {
                if (card[next] < card[indexTerkecil]) {
                    indexTerkecil = next;
                }
            }

            int currentCard = card[pass];
            card[pass] = card[indexTerkecil];
            card[indexTerkecil] = currentCard;
        }
    }

    static int findCard(int[] card, int target) {
        for (int position = 0; position < card.length; position++) {
            if (card[position] == target) {
                return position;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt")); // bisa ganti cards2.txt buat cek cards2

        int totalCard = sc.nextInt();
        int[] card = new int[totalCard];
        for (int i = 0; i < totalCard; i++) {
            card[i] = sc.nextInt();
        }

        int totalRequest = sc.nextInt();
        int[] requestedCard = new int[totalRequest];
        for (int i = 0; i < totalRequest; i++) {
            requestedCard[i] = sc.nextInt();
        }

        sortingCards(card, 2);

        for (int i = 0; i < totalCard; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(card[i]);
        }
        System.out.println();

        for (int i = 0; i < totalRequest; i++) {
            int position = findCard(card, requestedCard[i]);

            if (position != -1) {
                System.out.println(requestedCard[i] + ": FOUND at index " + position);
            } else {
                System.out.println(requestedCard[i] + ": NOT FOUND");
            }
        }

        sc.close();
    }
}