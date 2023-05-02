package org.example;

import javax.swing.*;
/*
* Has been tested in different project and
* works fine ,but when i tried to test it here there is an exception
* but not related to this code or any code i wrote
* */

public class CalculationsGUI {
    JFrame f;
    CalculationsGUI(){
        f=new JFrame();
        String data [][]={   //placeholders for testing purposes
                {"1","2","3","4","5","6"},
                {"1","2","3","4","5","6"},
                {"1","2","3","4","5","6"}
        };
        String[] columns ={
                "WT",
                "TAT",
                "RT",
                "AvgWT",
                "AvgTAT",
                "AvgRT",
        };
        JTable jt=new JTable(data,columns);
        jt.setBounds(30,40,200,300);
        jt.setEnabled(false);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        f.setSize(500,120);
        f.setVisible(true);
        f.setTitle("Priority Scheduling Algorithm Calculations");
    }
}
