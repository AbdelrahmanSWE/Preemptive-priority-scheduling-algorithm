package org.example.Models;

import java.util.ArrayList;

public class PriorityQueue {
    ArrayList <Process> processQueue;
    int numberOfProcesses;
    public PriorityQueue(int processes){
        processQueue=new ArrayList<>();
        this.numberOfProcesses=processes;
    }

    public void addProcess( Process p){
        int i;
        if (processQueue.isEmpty()){
            processQueue.add(p);
        }
        else {
            for (i=0;i<processQueue.size();i++){
                if ((p.getPriorityLvl()<processQueue.get(i).getPriorityLvl())) {
                    processQueue.add(i,p);
                    break;
                } else if (p.getPriorityLvl()==processQueue.get(i).getPriorityLvl()
                        &&(p.getArrivalTime()<processQueue.get(i).getArrivalTime())) {
                    processQueue.add(i,p);
                    break;
                } else if (p.getPriorityLvl()==processQueue.get(i).getPriorityLvl()
                        &&(p.getArrivalTime()==processQueue.get(i).getArrivalTime())
                        &&p.getPID()<processQueue.get(i).getPID()) {
                    processQueue.add(i,p);
                    break;
                }
            }
            if (i== processQueue.size()){
                processQueue.add(p);
            }
        }
    }
    public Process deQueue(){
        Process p=processQueue.get(0);
        processQueue.remove(0);
        return p;
    }
    public boolean isEmpty(){
        return processQueue.isEmpty();
    }
}
