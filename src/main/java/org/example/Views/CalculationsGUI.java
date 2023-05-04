package org.example.Views;

import org.example.Models.Process;
import org.example.Controllers.Scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/*This class is the GUI that displays all calculations info after user press "Run"
* in a different Jframe*/
public class CalculationsGUI {
    JFrame f;
    public CalculationsGUI(ArrayList <Process>p){
        f=new JFrame();
        String data [][]=new String[p.size()][4];

        for (int i=0;i<p.size();i++){
            data[i][0]="p"+p.get(i).getPID();
            data[i][1]=""+(p.get(i).getWaitTime());
            data[i][2]=""+(p.get(i).getTurnAroundTime());
            data[i][3]=""+(p.get(i).getResponseTime());
        }

        String[] columns ={
                "PID",
                "WT",
                "TAT",
                "RT"
        };

        //all next lines are for the design of the GUI
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable jt=new JTable(model);
        JLabel jLAVGTAT=new JLabel("Avg TAT:"+ Scheduler.calculateAVGTAT(p));
        JLabel jLAVGRT=new JLabel("Avg RT:"+Scheduler.calculateAVGRT(p));
        JLabel jLAVGWT=new JLabel("Avg WT:"+Scheduler.calculateAVGWT(p));
        jt.setBounds(30,40,200,300);
        jt.setEnabled(false);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        f.setSize(500,120);
        f.setVisible(true);
        f.setTitle("Priority Scheduling Algorithm Calculations");

        JPanel panel = new JPanel(new GridLayout(1, 3));
        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        panel.add(jLAVGWT);
        panel.add(jLAVGRT);
        panel.add(jLAVGTAT);
        panel.setSize(500,500);
        f.add(panel);
        f.setMinimumSize(new Dimension(600, 400));
        f.setMaximumSize(new Dimension(600, 400));

    }
}
