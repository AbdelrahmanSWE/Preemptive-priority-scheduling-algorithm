package org.example.Models;

//a class that simulates a real process
public class Process {

    //data to be input
    private final int PID;
    private final int arrivalTime;
    private int burstTime;
    private final int initialBT;
    private final int priorityLvl;
    private int PInTime;
    private int PExitTime;
    //data to be calculated
    private int waitTime;
    private int turnAroundTime;
    private int responseTime=-1;
    public Process(int PID,int arrivalTime,int burstTime, int priorityLvl){
        this.PID=PID;
        this.arrivalTime= arrivalTime;
        this.burstTime= burstTime;
        this.initialBT=burstTime;
        this.priorityLvl=priorityLvl;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPID() {
        return PID;
    }

    public int getPriorityLvl() {
        return priorityLvl;
    }
    public int getResponseTime() {
        return responseTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public int getWaitTime() {
        return waitTime;
    }
    public int getInitialBT(){
        return initialBT;
    }

    public int getPInTime() {
        return PInTime;
    }

    public int getPExitTime() {
        return PExitTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setPInTime(int PInTime) {
        this.PInTime = PInTime;
    }

    public void setPExitTime(int PExitTime) {
        this.PExitTime = PExitTime;
    }
}

