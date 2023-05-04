package org.example.Views;

import org.example.Controllers.InputHandler;
import org.example.Models.Process;
import org.example.Controllers.Scheduler;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/*GUI for the processes entry frame includes
-frame
-labels
-buttons for both create and run
-table to enter input
{{the last part of the frame for Displaying the Gantt Chart }}
* */

//Special Thanks to Eng:Abdalla Essam Ali our OS Instructor ,for providing us with a part of the GUI code

public class GUI extends javax.swing.JFrame {
    private String[][] data;
    private int noProcesses;
    private ArrayList<Process>p;

    public GUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")

    //All components of the GUI
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelErr=new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Priority Scheduling Algorithm implementation");
        jLabelErr.setText("");
        jLabelErr.setForeground(Color.red);
        jLabel2.setText("Enter #Processes");
        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Run");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18,18,18)
                        .addComponent(jLabelErr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 108, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(115, 115, 115))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                    .addComponent(jLabelErr)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    //create button to show table to enter inputs
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] columns = {
            "Process Name",
            "Arrival Time",
            "Burst Time",
            "Priority Level",
            "ID"
        };
        System.out.println("Number of Processes " + jTextField1.getText());
        //Number Of Processes
        try{
            noProcesses = Integer.parseInt(jTextField1.getText());
            data = new String[noProcesses][5];
            for (int i = 0; i < data.length; i++) {
                data[i][0] = "P" + (i+1);
            }
            data[0][1]="0";
            JTable table = new JTable(data, columns) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return !(column == 0 || (row==0&&column==1));
                }
            };
            p=new ArrayList<>();

            table.getTableHeader().setReorderingAllowed(false);
            jButton2.setEnabled(true);
            jScrollPane2.setViewportView(table);
            jLabelErr.setText("");
        }
        catch(Exception E){
            System.out.println("bad process number");
            jLabelErr.setText("Invalid Input");
        }
    }
    //the "Run" button and its functions
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt){
        try{
            for (int i=0;i<noProcesses;i++){
                InputHandler.input(p,data[i][4],data[i][1],data[i][2],data[i][3]);
            }
            Scheduler s=new Scheduler(p);
            s.scheduleSimulate();
            CalculationsGUI g=new CalculationsGUI(s.getFinishedProcesses());
            JPanel canvas = new JPanel() {

                //this is the part responsible for displaying the Gantt chart
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int temp=0,i;
                    g.setFont(new Font("Serif", Font.BOLD, 14));
                    int locX=30,locY=20;
                    int pNameLocX=45,pNameLocY=30;
                    for (i=0 ;i<s.getGanttChart().get(s.getGanttChart().size()-1).getOutTime();i++) {
                        if (temp!=s.getGanttChart().get(i).getPID()){
                            g.drawString(String.valueOf(s.getGanttChart().get(i).getInTime()), locX, locY);
                            g.drawString("P"+s.getGanttChart().get(i).getPID(), pNameLocX, pNameLocY);//-----
                            g.drawLine(locX, 0, locX, 10);
                            temp=s.getGanttChart().get(i).getPID();
                        }
                        locX+=30;
                        pNameLocX+=30;
                    }
                    g.drawString(String.valueOf(i), locX, locY);
                    g.drawLine(locX, 0, locX, 10);
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(1000, 1000);
                }
            };
            jScrollPane1.setViewportView(canvas);
            jLabelErr.setText("");
        }catch (Exception e){
            System.out.println("bad input");
            jLabelErr.setText("Invalid input");
        }
    }



    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelErr;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;

}
