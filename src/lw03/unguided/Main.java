package lw03.unguided;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    Map<String, Integer> checks = new LinkedHashMap<>();

    int rejectedOperations = 0;

    Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ", 3);
            String operation = parts[0];
            String classCode = parts[1];
            int students = Integer.parseInt(parts[2]);
        
                if (operation.equals("REGISTER")) {
                    if (!checks.containsKey(classCode)) {
                        checks.put(classCode, students);
                    } else {
                        int currentStudent = checks.get(classCode);
                        checks.put(classCode, currentStudent + students);
                    }

                } else if (operation.equals("WITHDRAW")) {
                    if (checks.containsKey(classCode) && checks.get(classCode) >= students) {
                        int currentStudent = checks.get(classCode);
                        checks.put(classCode, currentStudent - students);
                    } else {
                        rejectedOperations++;
                    }

                } else if (operation.equals("CHECK")) {
                    if (checks.containsKey(classCode)) {
                        System.out.println("Class code: " + classCode + ", Total students: " + checks.get(classCode));
                    } else if (!classCode.equals("CHECK")) {
                        rejectedOperations++;
                    }
                }
            }

            sc.close();

            System.out.println("===== Enrollment Checks =====");
                for (String classCode1 : checks.keySet()) {
                    System.out.println(classCode1 + ": " + checks.get(classCode1));
                }
            System.out.println();

            System.out.println("==== Final Enrollment ====");
                for (String classCode1 : checks.keySet()) {
                    System.out.println(classCode1 + ": " + checks.get(classCode1));
            }
            System.out.println();

            System.out.println("Rejected operations: " + rejectedOperations);
    }
}