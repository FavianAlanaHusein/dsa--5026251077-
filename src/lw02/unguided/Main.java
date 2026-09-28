package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args){

LinkedList<String> title = new LinkedList<>();
LinkedList<Integer> currentStock = new LinkedList<>();

title.add(0, "Kalkulus");
title.add(1, "Fisika");
title.add(2, "Statistika");

currentStock.add(0, 2);
currentStock.add(1, 1);
currentStock.add(2, 2);

LinkedList<String[]> memberRecords = new LinkedList<>();

Queue<String[]> queue = new LinkedList<>();
Stack<String[]> failedRequests = new Stack<>();

    Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

    while(scanner.hasNext()){
        String[] record = new String[4];

            record[0] = scanner.next();
            record[1] = scanner.next();
            record[2] = scanner.next();
            record[3] = scanner.next();
            memberRecords.add(record);
        }
    
        scanner.close();
    
        queue.addAll(memberRecords);
        while(!queue.isEmpty()){
            String[] Requests = queue.poll();
    
            String name = Requests[0];
            String bookTitle = Requests[1];
    
            String[] member = null;
    
            for (String[] data : memberRecords){
            if(data[0].equals(name)){
                member = data;
                break;
            }
        }

        if(member == null){
            member = new String[]{name,"0"};
            memberRecords.add(member);
        }
        }

        System.out.println("=== Successfully Processed Requests ===");

        System.out.println("=== Remaining Book Stock ===");
        for (int i = 0; i < title.size(); i++) {
            System.out.println(title.get(i) + " : " + currentStock.get(i));
        }
        System.out.println("=== Failed Requests ===");
        while (!failedRequests.isEmpty()) {
            String[] failed = failedRequests.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}