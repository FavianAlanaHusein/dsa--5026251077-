package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int banyakEx = 0;

        while (sc.hasNextInt()) {
            if (banyakEx > 0) {
                System.out.println();
            }
            banyakEx++;

            int totalBranch = sc.nextInt();
            int[] branches = new int[totalBranch];
            for (int i = 0; i < totalBranch; i++) {
                branches[i] = sc.nextInt();
            }

            int totalTarget = sc.nextInt();
            int[] targets = new int[totalTarget];
            for (int i = 0; i < totalTarget; i++) {
                targets[i] = sc.nextInt();
            }

            for (int i = 1; i <= 3; i++) {
                int current = branches[i];
                int j = i - 1;

                while (j >= 0 && branches[j] < current) {
                    branches[j + 1] = branches[j];
                    j--;
                }
                branches[j + 1] = current;
            }

            for (int i = 0; i < totalBranch; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(branches[i]);
            }
            System.out.println();

            for (int i = 0; i < totalTarget; i++) {
                int found = -1;
                for (int j = 0; j < totalBranch; j++) {
                    if (branches[j] == targets[i]) {
                        found = j;
                        break;
                    }
                }

                if (found != -1) {
                    System.out.println(targets[i] + ": FOUND at index " + found);
                } else {
                    System.out.println(targets[i] + ": NOT FOUND");
                }
            }
        }

        sc.close();
    }
}