package org.example.Controllers;

import org.example.Models.Process;

import java.util.ArrayList;

public class InputHandler {

    public static void input(ArrayList<Process> p, String SPID, String SAT, String SBT, String SPL) throws Exception {
        int PID=Integer.parseInt(SPID);
        int AT=Integer.parseInt(SAT);
        int BT=Integer.parseInt(SBT);
        int PL=Integer.parseInt(SPL);
        if (PID<0||AT<0||BT<0||PL<0){
            throw new Exception("No negative Numbers Please");
        }else{
            p.add(new Process(PID,AT,BT,PL));
        }
    }
}
