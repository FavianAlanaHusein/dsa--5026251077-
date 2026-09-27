package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransactionProcessor {

    public static void main(String[] args) {

        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        try {
            File file = new File("transactions.txt");
            if (!file.exists()) {
                file = new File("src/lw02/prelab/transactions.txt");
            }

            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(" ");
                String name = data[0];
                String type = data[1];
                String amount = data[2];

                String[] transaction = { name, type, amount };
                transactionList.add(transaction);

                boolean customerExists = false;
                for (String[] customer : customerList) {
                    if (customer[0].equals(name)) {
                        customerExists = true;
                        break;
                    }
                }
                if (!customerExists) {
                    String[] newCustomer = { name, "0" };
                    customerList.add(newCustomer);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt not found.");
            return;
        }

        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactionList);

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;
            for (String[] c : customerList) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance = balance + amount;
                customer[1] = String.valueOf(balance);

            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedTransactions.push(transaction);
                } else {
                    balance = balance - amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customerList) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}