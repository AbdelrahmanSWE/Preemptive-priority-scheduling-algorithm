package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {

    public static void inputProcess(ArrayList<Process> p){
        Scanner in=new Scanner(System.in);
        System.out.println("what is the number of processes");
        int PID, AT,BT,PL,n=in.nextInt();
        for (int i=0;i<n;i++){
            System.out.println("Enter the first Process ID");
            PID=in.nextInt();
            System.out.println("Enter the Arrival Time");
            AT=in.nextInt();
            System.out.println("Enter the Burst Time");
            BT=in.nextInt();
            System.out.println("Enter the Priority Level");
            PL=in.nextInt();
            p.add(new Process(PID,AT,BT,PL));
        }
    }
}
