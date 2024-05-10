/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectos;


import java.util.Scanner;
//
/**
 *
 @author dell
 */
//public class ProjectOS {
//
//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        Scanner input = new Scanner(System.in);
//        Calculation c = new Calculation();
//        
//        
//        System.out.print("Enter the number of processes: ");
//        int numProcesses = input.nextInt();
//        for (int i = 0; i < numProcesses; i++) { 
//            System.out.println("Enter details for Process " + (i + 1) + ":"); 
//            System.out.print("Arrival Time: "); 
//            int arrivalTime = input.nextInt(); 
//            System.out.print("Burst Time: ");
//            int burstTime = input.nextInt();
//            System.out.print("Priority: "); 
//            int priority = input.nextInt(); 
//            c.addProcess(arrivalTime, burstTime, priority);
//           
//        }
//   c.calculateWaitingTime();
//   System.out.println( "avg waititng   "+c.averageWaitingTime());
//    
//    
//    
//    
//    }
//}


import com.mycompany.projectos.Calculation;
import java.util.*;

public class ProjectOS {

    public static void main(String[] args){
        ArrayList<Process> list = new ArrayList<Process>();

        list.add(new Process(0, 5, 6));
        list.add(new Process(1, 2, 4));
        list.add(new Process(2, 4, 3));
        list.add(new Process(3, 1, 1));
        list.add(new Process(4, 7, 2));

        Algorithm al = new Algorithm(list);

        al.start();
    }

    //public static void main(String[] args) {
    //    Scanner scanner = new Scanner(System.in);

    //    System.out.print("Enter the number of processes: ");
    //    int numProcesses = scanner.nextInt();

    //    List<Process> processes = new ArrayList<>();

    //    for (int i = 0; i < numProcesses; i++) {
    //        System.out.println("Process " + (i + 1) + ":");
    //        System.out.print("  Arrival Time: ");
    //        int arrivalTime = scanner.nextInt();
    //        System.out.print("  Burst Time: ");
    //        int burstTime = scanner.nextInt();
    //        System.out.print("  Priority: ");
    //        int priority = scanner.nextInt();
    //        processes.add(new Process( arrivalTime, burstTime, priority));
    //    }

    //    Calculation c = new Calculation((ArrayList<Process>)processes);
    //    //c.priorityPreemptive();

    // //   System.out.println("\nProcess\tWaiting Time\tTurnaround Time\tResponse Time");
    //    for (Process p : processes) {
    //        System.out.println( p.getWaitingTime() );
    //           c.calculateWaitingTime();
    //
    //     }

    //    System.out.println("\nAverage Waiting Time: " + c.averageWaitingTime());
    // //   System.out.println("Average Turnaround Time: " + c.averageTurnaroundTime());
    // //   System.out.println("Average Response Time: " + c.averageResponseTime());

    //    scanner.close();
    //}
}
