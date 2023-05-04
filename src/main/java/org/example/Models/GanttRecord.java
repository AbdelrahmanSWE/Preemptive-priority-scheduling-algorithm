package org.example.Models;

public class GanttRecord {
    private int inTime;
    private final int PID;
    private int outTime;
    public GanttRecord(int in,int out,int ID){
        this.inTime=in;
        this.outTime=out;
        this.PID=ID;
    }

    public int getInTime() {
        return inTime;
    }

    public int getOutTime() {
        return outTime;
    }

    public int getPID() {
        return PID;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    //A GUI function to display GANTT
}
