package org.example.Controllers;

import org.example.Models.GanttRecord;
import org.example.Models.PriorityQueue;
import org.example.Models.Process;

import java.util.ArrayList;
/*
* The Scheduler class is the main brain of this program here its decided which process
* Starts end which process ends depending on its priority level*/

public class Scheduler {
    private ArrayList <Process> processes;
    private ArrayList <GanttRecord> ganttChart;
    private PriorityQueue readyQueue;
    private ArrayList<Process> finishedProcesses;
    private int currentTime;
    public Scheduler(ArrayList<Process>processes){
        this.processes=arrangeByTime(processes);
        this.ganttChart=new ArrayList<>();
        this.currentTime=0;
        this.finishedProcesses=new ArrayList<>();
    }

    public void scheduleSimulate(){
        readyQueue = new PriorityQueue(processes.size());
        while (!processes.isEmpty()||!readyQueue.isEmpty()){
            if (!processes.isEmpty()){
                for (int i=0;i<processes.size();i++) {
                    if (processes.get(i).getArrivalTime() == currentTime){
                        readyQueue.addProcess(processes.get(i));
                        processes.remove(i);
                        i--;
                    }
                    else if(processes.get(i).getArrivalTime()>currentTime){
                        break;
                    }
                }
            }
            if (!readyQueue.isEmpty()){
                Process currentWorking=readyQueue.deQueue();
                if (currentWorking.getResponseTime()==-1) {
                    currentWorking.setPInTime(currentTime);
                    currentWorking.setResponseTime(currentWorking.getPInTime() - currentWorking.getArrivalTime());
                }
                currentWorking.setBurstTime(currentWorking.getBurstTime()-1);
                ganttChart.add(new GanttRecord(currentTime ,currentTime+1 ,currentWorking.getPID()));
                if (currentWorking.getBurstTime()!=0){
                    readyQueue.addProcess(currentWorking);
                }else{
                    currentWorking.setPExitTime(currentTime+1);
                    currentWorking.setTurnAroundTime(currentWorking.getPExitTime()-currentWorking.getArrivalTime());
                    currentWorking.setWaitTime(currentWorking.getTurnAroundTime()- currentWorking.getInitialBT());
                    finishedProcesses.add(currentWorking);
                }
                currentTime++;
            }
        }
        arrangeByPID(finishedProcesses);
    }

    /*The next three methods are used to calculate the Average for:
    * -Turnaround time
    * -Run time
    * -Waiting time
    *
    * then all of these are sent to the GUI to be displayed */
    public static double calculateAVGTAT(ArrayList<Process> p){
        int sum=0;
        double avg;
        for (Process process : p) {
            sum += process.getTurnAroundTime();
        }
        System.out.println("Calculating AVG TAT");
        avg= (double) sum /p.size();
        return avg;
    }
    public static double calculateAVGRT(ArrayList<Process> p){
        int sum=0;
        double avg;
        for (Process process : p) {
            sum += process.getResponseTime();
        }
        System.out.println("Calculating AVG RT");
        avg= (double) sum /p.size();
        return avg;
    }
    public static double calculateAVGWT(ArrayList<Process> p){
        int sum=0;
        double avg;
        for (Process process : p) {
            sum += process.getWaitTime();
        }
        System.out.println("Calculating AVG WT");
        avg= (double) sum /p.size();
        return avg;
    }
    //Method to arrange processes by the Arrival time the user assigns them Through the GUI

    private ArrayList<Process> arrangeByTime(ArrayList<Process> p){
        for (int i = 0; i< p.size(); i++){
            for (int j = 0; j< p.size(); j++){
                Process temp;
                if (p.get(i).getArrivalTime()< p.get(j).getArrivalTime()){
                    temp= p.get(i);
                    p.set(i, p.get(j));
                    p.set(j,temp);
                }
                else if (p.get(i).getArrivalTime()==p.get(j).getArrivalTime()
                        &&p.get(i).getPID()<p.get(j).getPriorityLvl()){
                    temp= p.get(i);
                    p.set(i, p.get(j));
                    p.set(j,temp);
                }
            }
        }
        System.out.println("Sorting By time");
        return p;
    }

    //Method to arrange processes by the ID the user assigns them Through the GUI
    private void arrangeByPID(ArrayList<Process> p){
        for (int i = 0; i< p.size(); i++){
            for (int j = 0; j< p.size(); j++){
                Process temp;
                if (p.get(i).getPID()< p.get(j).getPID()){
                    temp= p.get(i);
                    p.set(i, p.get(j));
                    p.set(j,temp);
                }
            }
        }System.out.println("Sorting by PID");
    }


    //getter for finished processes,so it can be called by other classes and Main
    public ArrayList<Process> getFinishedProcesses() {
        return finishedProcesses;
    }

    //getter for the Gantt chart ,so it can be called by other classes and Main
    public ArrayList<GanttRecord> getGanttChart() {
        return ganttChart;
    }
}
