package com.mycompany.projectos;
  
public class GanttNode {
    private String name;
    private int start;
    private int end;

    GanttNode(String name, int start, int end){
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public String getName() {
        return name;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
