package org.example;

import java.util.ArrayList;

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
    /*
    * To the future me who will debug this code I would like to apologize
    * the process this code conduct the following
    * first is, take the first arriving process and add it to the readyQueue    *  will check whether it's the first time this process runs, based on that the process will set the response
    * time accordingly
    * then we will proceed to subtract 1 from the burst time,
    * make a gantt record in our gantt chart, then check whether it's zero or not, based on the answer
    * this will determine whether the process goes back to the readyQueue or not, then we increase the current time by one,
    * then we go into a for loop to check if a new process arrived, according to the current times and the processes saved
    * if a new process arrives ,it is then added to the readyQueue and the loop continues until no processes remain in either the
    * readyQueue or the processes arrayList
    * */
    public void scheduleSimulate(){
        readyQueue = new PriorityQueue(processes.size());
        while (!processes.isEmpty()||!readyQueue.isEmpty()){
            if (!processes.isEmpty()){
                for (int i=0;i<processes.size();i++) {
                    if (processes.get(i).getArrivalTime() == currentTime){
                        readyQueue.addProcess(processes.get(i));
                        processes.remove(i);
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
                //we might not need a gant chart
                ganttChart.add(new GanttRecord(currentTime ,currentTime+1 ,currentWorking.getPID()));

                if (currentWorking.getBurstTime()!=0){
                    readyQueue.addProcess(currentWorking);
                }else{
                    currentWorking.setPExitTime(currentTime+1);
                    currentWorking.setTurnAroundTime(currentWorking.getPExitTime()-currentWorking.getArrivalTime());
                    currentWorking.setWaitTime(currentWorking.getTurnAroundTime()- currentWorking.getInitialBT());
                    finishedProcesses.add(currentWorking);
                }
            }
            currentTime++;
        }
    }
    //is under development
    public void optimiseGanttChart(){
        ArrayList<GanttRecord> newGantt=new ArrayList<>();
        int inT=0,extT=0,currentPID=ganttChart.get(0).getPID(),counter=0;
        while (extT<=currentTime){
            if (currentPID!=ganttChart.get(counter).getPID()){

            }
        }
        ganttChart=newGantt;
    }
    public static double calculateAVGTAT(ArrayList<Process> p){
        int sum=0;
        double avg;
        for (int i=0;i<p.size();i++){
            sum+=p.get(i).getTurnAroundTime();
        }
        avg=sum/p.size();
        return avg;
    }
    public static double calculateAVGRT(ArrayList<Process> p){
        int sum=0;
        double avg;
        for (int i=0;i<p.size();i++){
            sum+=p.get(i).getResponseTime();
        }
        avg=sum/p.size();
        return avg;
    }
    public static double calculateAVGWT(ArrayList<Process> p){
        int sum=0;
        double avg;
        for (int i=0;i<p.size();i++){
            sum+=p.get(i).getWaitTime();
        }
        avg=sum/p.size();
        return avg;
    }
    private ArrayList<Process> arrangeByTime(ArrayList<Process> p){
        ArrayList<Process>tp=p;
        for (int i=0;i<tp.size();i++){
            for (int j=0;j<tp.size();j++){
                Process temp;
                if (tp.get(i).getArrivalTime()<tp.get(j).getArrivalTime()){
                    temp=tp.get(i);
                    tp.set(i,tp.get(j));
                    tp.set(j,temp);
                }
            }
        }
        return tp;
    }

    public ArrayList<Process> getFinishedProcesses() {
        return finishedProcesses;
    }

    public ArrayList<GanttRecord> getGanttChart() {
        return ganttChart;
    }
}
