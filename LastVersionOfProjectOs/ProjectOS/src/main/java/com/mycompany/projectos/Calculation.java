package com.mycompany.projectos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dell
 */
public class Calculation {
    
    private ArrayList<Process> processes;
    
    //constractor
    public Calculation (List<Process> processes) {
        this.processes = (ArrayList<Process>) processes;
    }
    
        //Add Process to the array list with full details
    public void addProcess(int arrivalTime , int burstTime , int priority)
    {
        Process process = new Process(arrivalTime,burstTime,priority);
        processes.add(process);
    }
  
    
    
    
//    
//    public void calculateWaitingTime() {
//    PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getArrivalTime).thenComparingInt(Process::getPriority));
//    List<Process> completedProcesses = new ArrayList<>();
//    int currentTime = 0;
//
//    while (!processQueue.isEmpty() || !processes.isEmpty()) {
//        // Add arriving processes to the queue
//        while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
//            processQueue.add(processes.remove(0));
//        }
//
//        // If no process is in the queue, move time to the next arrival
//        if (processQueue.isEmpty()) {
//            currentTime = processes.get(0).getArrivalTime();
//        }
//
//        // Process the highest priority process in the queue
//        Process currentProcess = processQueue.poll();
//        int executionTime = Math.min(currentProcess.getBurstTime(), 1);
//        currentProcess.setBurstTime(currentProcess.getBurstTime() - executionTime);
//        currentTime += executionTime;
//
//        // Add back the process if it's not finished
//        if (currentProcess.getBurstTime() > 0) {
//            processQueue.add(currentProcess);
//        } else {
//            currentProcess.setCompletionTime(currentTime);
//            completedProcesses.add(currentProcess);
//        }
//    }
//
//    // Update the original list of processes
//    processes.addAll(completedProcesses);
//}

    
    
    // wrong alghorithm
//    public void calculateWaitingTime() {
//    int currentTime = 0;
//    for (Process process : processes) {
//        int completionTime = currentTime + process.getBurstTime();
//        process.setCompletionTime(completionTime);
//        int waitingTime = Math.max(0, completionTime - process.getArrivalTime() - process.getBurstTime());
//        process.setWaitingTime(waitingTime);
//        currentTime = completionTime;
//    }
//}
    
    
    //the mosr related
//public void calculateWaitingTime() {
//    int currentTime = 0;
//    boolean[] executed = new boolean[processes.size()]; // To track if a process has been executed
//    Arrays.fill(executed, false);
//
//    while (true) {
//        int highestPriority = Integer.MAX_VALUE;
//        int nextProcessIndex = -1;
//
//        // Find the highest priority process that has arrived and not yet executed
//        for (int i = 0; i < processes.size(); i++) {
//            Process process = processes.get(i);
//            if (!executed[i] && process.getArrivalTime() <= currentTime && process.getPriority() < highestPriority) {
//                highestPriority = process.getPriority();
//                nextProcessIndex = i;
//            }
//        }
//
//        // If no process is found, break the loop
//        if (nextProcessIndex == -1)
//            break;
//
//        // Execute the next process
//        Process nextProcess = processes.get(nextProcessIndex);
//        nextProcess.setWaitingTime(currentTime - nextProcess.getArrivalTime());
//
//        // Update the current time and mark the process as executed
//        currentTime += nextProcess.getBurstTime();
//        executed[nextProcessIndex] = true;
//    }
//}

public void calculateWaitingTime() {
    int currentTime = 0;
    for (Process process : processes) { 
        int completionTime = currentTime + process.getBurstTime();
        int turnaroundTime = completionTime - process.getArrivalTime();
        int waitingTime = turnaroundTime - process.getBurstTime();
        process.setWaitingTime(waitingTime);
        currentTime = completionTime;     }
}


public double averageWaitingTime() {
    double totalWaitingTime = 0;
    for (Process process : processes) {
        totalWaitingTime += process.getWaitingTime();
    }
    return totalWaitingTime / processes.size();
}

  
//    //Omar
//    public double averageWaitingTime()
//    {   double totalWaitingTime = 0;
//        for(Process process : processes)
//        {
//            totalWaitingTime+= process.getWatingTime();
//        }
//        return totalWaitingTime / processes.size();
//    }
    
    //Ahmed
    public double averageTurnaroundTime()
    {
            double totalTurnaroundTime = 0;
    for (Process process : processes) {
        totalTurnaroundTime += process.getTurnaroundTime();
    }
    return totalTurnaroundTime / processes.size();
}
    
       
//    //Rodayna
    public double averageResponseTime()
    {
            double totalResponsTime = 0;
    for (Process process : processes) {
        totalResponsTime += process.getResponseTime();
    }
    return totalResponsTime / processes.size();
}

        
}    
    //ziad
//    private void updateProcess()
//    {
//        
//    }
    

