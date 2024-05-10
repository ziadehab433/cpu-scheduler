package com.mycompany.projectos;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Object;

public class Algorithm {
    int time;
    int timeJump;
    ArrayList<Process> notThereYet;
    ArrayList<Process> waiting;
    GanttChart ganttChart;

    HashMap<Process, Integer> remainingBT;

    Algorithm(ArrayList<Process> processes){
        notThereYet = new ArrayList<Process>(processes);
        waiting = new ArrayList<Process>();
        ganttChart = new GanttChart();

        notThereYet.sort(new sortByArrivalTime());
        time = notThereYet.get( notThereYet.size() - 1 ).getArrivalTime();

        buildMap();
    }

    private void buildMap(){
        remainingBT = new HashMap<Process, Integer>();

        for( Process p : notThereYet) {
            remainingBT.put(p, p.getBurstTime());
        }
    }

    public ArrayList<Process> start(){
        ArrayList<Process> newPs = new ArrayList<>();

        while(!notThereYet.isEmpty() || !waiting.isEmpty()) {
            updateWaitingList();
            Process highest = getHighestPriorityWaiting();

            if(highest == null) {
                timeJump = notThereYet.get(notThereYet.size() - 1).getArrivalTime();
                ganttChart.addNode("NO", time, timeJump);

                time = timeJump;
                continue;
            }

            if(highest.getResponseTime() == -1) {
                highest.setResponseTime(time - highest.getArrivalTime());
            }

            if(!notThereYet.isEmpty()) {
                timeJump = Math.min(time + remainingBT.get(highest), notThereYet.get(notThereYet.size() - 1).getArrivalTime());
            }else {
                timeJump = time + remainingBT.get(highest);
            }

            if((timeJump - time) >= remainingBT.get(highest)) {
                highest.setWaitingTime( timeJump - highest.getBurstTime() - highest.getArrivalTime());
                highest.setTurnaroundTime( highest.getWaitingTime() + highest.getBurstTime() );
                highest.setCompletionTime( highest.getTurnaroundTime() + highest.getArrivalTime() );

                waiting.remove(highest);
                newPs.add(highest);
            }else {
                remainingBT.put(highest, remainingBT.get(highest) - (timeJump - time) );
            }

            ganttChart.addNode("P" + highest.getProcessId(), time, timeJump);

            time = timeJump;
        }

        return newPs;
    }

    public GanttChart getGanttChart(){
        return ganttChart;
    }

    private void updateWaitingList(){
        for(int i = notThereYet.size() - 1; i >= 0; i--) {
            if(notThereYet.get(i).getArrivalTime() > time) {
                break;
            }

            waiting.add(notThereYet.remove(i));
        }
    }

    private Process getHighestPriorityWaiting(){
        if(waiting.isEmpty()) {
            return null;
        }

        Process highest = waiting.getLast();
        for(Process p : waiting){
            if(p.getPriority() < highest.getPriority()) {
                highest = p;
            }
        }
        return highest;
    }
}
