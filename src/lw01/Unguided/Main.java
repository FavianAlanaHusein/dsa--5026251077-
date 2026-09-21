package lw01.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Rental[] rentals = new Rental[100];
        int[] rentalUnits = new int[100];
        int rentalCount = 0;

        File file = new File("rentals.txt");
        if (!file.exists()) {
            file = new File("src/lw01/Unguided/rentals.txt");
        }

        try (Scanner scanner = new Scanner(file)) {
            int rentalCounter = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < rentalCounter; i++) {
                if (!scanner.hasNext()) break;
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                String typeNorm = type.trim().toLowerCase();

                if (typeNorm.equals("laptop")) {
                    rentals[rentalCount] = new LaptopRental(id, days);
                } else if (typeNorm.equals("projector")) {
                    rentals[rentalCount] = new ProjectorRental(id, days);
                } else {
                    throw new IllegalArgumentException("Unknown type: " + type);
                }
                rentalUnits[rentalCount] = units;
                rentalCount++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("rentals.txt not found");
            return;
        }

        for (int i = 0; i < rentalCount; i++) {
            System.out.println(rentals[i].getId() + " | " + rentals[i].label() + " | " + rentals[i].calculateCharge(rentalUnits[i]));
        }
    }
}