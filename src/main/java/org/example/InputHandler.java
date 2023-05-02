package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {

    public static void input(ArrayList<Process> p,String PID,String AT,String BT,String PL){
        p.add(new Process(Integer.parseInt(PID),Integer.parseInt(AT),Integer.parseInt(BT),Integer.parseInt(PL)));
    }
}
