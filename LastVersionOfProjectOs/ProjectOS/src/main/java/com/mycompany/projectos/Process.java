package com.mycompany.projectos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Comparator;

/**
 *
 * @author dell
 */
public class Process
{
    private static int idCounter = 0;
    private int processId;
    private  int arrivalTime;
    private int burstTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;
    private  int responseTime;
    private  int completionTime;


public Process(int arrivalTime , int burstTime , int priority)
{
    this.processId = ++idCounter;

    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.priority = priority;

    this.waitingTime = -1;
    this.turnaroundTime = -1;
    this.responseTime = -1;
    this.completionTime = -1;
}
//Setters
    public void setProcessId(int processId) {
        this.processId = processId;
    }
public void setCompletionTime(int completionTime)
{
    this.completionTime = completionTime;
}
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setWaitingTime(int watingTime) {
        this.waitingTime = watingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
    
//getters

    public int getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getResponseTime() {
        return responseTime;
    }
    public int getCompletionTime()
    {
        return completionTime;
    }
}

class sortByArrivalTime implements Comparator<Process>{
    @Override
    public int compare(Process p1, Process p2) {
        return p2.getArrivalTime() - p1.getArrivalTime();
    }
}

