package org.example.Controllers;
import org.example.Models.Process;
import java.util.ArrayList;

//through here we handle all incoming inputs that come through the GUI and we send
//them to other classes to be processed
public class InputHandler {

    public static void input(ArrayList<Process> p, String SPID, String SAT, String SBT, String SPL) throws Exception {
        int PID=Integer.parseInt(SPID);
        int AT=Integer.parseInt(SAT);
        int BT=Integer.parseInt(SBT);
        int PL=Integer.parseInt(SPL);
        if (PID<0||AT<0||BT<0||PL<0){
            throw new Exception("No negative Numbers Please");  //Exception to make sure user doesn't
        }else{                                                  //input negative # of process
            p.add(new Process(PID,AT,BT,PL));
        }
    }
}
