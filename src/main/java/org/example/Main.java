package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        double avgResponse=0, avgWait=0, avgTurnAround=0;
        ArrayList<Process>p=new ArrayList<>();
        InputHandler.inputProcess(p);
        Scheduler s=new Scheduler(p);
        s.scheduleSimulate();
        s.optimiseGanttChart();
        avgResponse=s.calculateAVGRT(s.getFinishedProcesses());
        avgTurnAround=s.calculateAVGTAT(s.getFinishedProcesses());
        avgWait=s.calculateAVGWT(s.getFinishedProcesses());
    }
}