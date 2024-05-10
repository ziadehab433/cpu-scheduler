package com.mycompany.projectos;

import java.util.ArrayList;

public class GanttChart {
    private ArrayList<GanttNode> ChartArray;

    GanttChart(){
        ChartArray = new ArrayList<>();
    }

    public void addNode(String name, int start, int end){
        GanttNode newNode = new GanttNode(name, start , end);

        ChartArray.add(newNode);
    }

    public ArrayList<GanttNode> getChartArray() {
        return ChartArray;
    }

    public void setChartArray(ArrayList<GanttNode> chartArray) {
        ChartArray = chartArray;
    }
}
