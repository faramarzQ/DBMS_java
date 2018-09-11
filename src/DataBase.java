/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faramarz
 */

import java.util.Scanner;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import sun.tools.jar.resources.jar;

public class DataBase extends javax.swing.JFrame {
        
    	//declaring variables
        static String graph = "";
        static int k = 0;
        static String[] graphArr = new String[100];
        static int d = graphArr.length;
        static boolean b = false;
        static int x = 0;
        
        //########################
        //Conflict Serializability
        //########################
        public void CS(String[] array1) {
            int functions = array1.length-1;
	    //cheking input of array for W->W and W->R and R->W and puting the result in a string
            for(int i=0 ; i<functions ; i++){
            if(array1[i].charAt(3) == 'W'){
                char Data = array1[i].charAt(5);
                    for(int j=i+1 ; j<=functions ; j++){
                        if(array1[j].charAt(3) == 'W'){
                            if(array1[j].charAt(5) == Data && array1[i].charAt(1) != array1[j].charAt(1)){
                                graph = graph + String.valueOf(array1[i].charAt(1));
                                graph = graph + "->" + String.valueOf(array1[j].charAt(1)) + ",";
                                k++;
                            }
                        }
                        else if(array1[j].charAt(3) == 'R'){
                            if(array1[j].charAt(5) == Data && array1[i].charAt(1) != array1[j].charAt(1)){
                                graph = graph + String.valueOf(array1[i].charAt(1));
                                graph = graph + "->" + String.valueOf(array1[j].charAt(1)) + ",";
                                k++;
                            }
                        }
                    }
            } 
            else if(array1[i].charAt(3) == 'R'){
                char Data = array1[i].charAt(5);
                for(int j=i+1 ; j<=functions ; j++){
                        if(array1[j].charAt(3) == 'W'){
                            if(array1[j].charAt(5) == Data && array1[i].charAt(1) != array1[j].charAt(1)){
                                graph = graph + String.valueOf(array1[i].charAt(1));
                                graph = graph + "->" + String.valueOf(array1[j].charAt(1)) + ",";
                                k++;
                            }
                        }
                    }
            }
        }
        
        //split string from ',' to array
        graphArr = graph.split(",");
        
        //cheking if there is any loop in an array
        for(int i = 0 ; i < d ; i++){
                x = graphArr[i].charAt(0);
                int z = graphArr[i].charAt(3);
                for(int j = 0 ; j < d ; j++){
                    if(graphArr[j] != null){
                        if(graphArr[j].charAt(0) == z){
                            z = graphArr[j].charAt(3);
                            if(z == x){
                                System.out.println("not conflict serializable!");
                                jLabel3.setText("not conflict serializable!");
                                b = true;
                                break;
                            }
                        }
                    }
            }
            if(b == true){
                break;
            }
            else {
                jLabel3.setText("conflict serializable");
            }
        }
        }
        
        
        //##########
        //Dead Lock!
        //##########
        public void DL(String[] array1) {
            int functions = array1.length-1;
            ////cheking input of array for Lock-X()->lock-X() and Lock-X()->Lock-S() and Lock-S()->Lock-X() and puting the result in a string
            for(int i=0 ; i<functions ; i++){
                if(array1[i].charAt(3) == 'L'){
                    if(array1[i].charAt(8) == 'X'){
                        char Data = array1[i].charAt(10);
                            for(int j=i+1 ; j<=functions ; j++){
                                if(array1[j].charAt(3) == 'L'){
                                    if(array1[j].charAt(8) == 'X'){
                                        if(array1[j].charAt(10) == Data && array1[i].charAt(1) != array1[j].charAt(1)){
                                            graph = graph + String.valueOf(array1[i].charAt(1));
                                            graph = graph + "->" + String.valueOf(array1[j].charAt(1)) + ",";
                                            k++;
                                        }
                                    }
                                    else if(array1[j].charAt(8) == 'S'){
                                        if(array1[j].charAt(10) == Data && array1[i].charAt(1) != array1[j].charAt(1)){
                                            graph = graph + String.valueOf(array1[i].charAt(1));
                                            graph = graph + "->" + String.valueOf(array1[j].charAt(1)) + ",";
                                            k++;
                                        }
                                    }
                                }

                            }
                    }
                    else if(array1[i].charAt(8) == 'S'){
                        char Data = array1[i].charAt(10);
                        for(int j=i+1 ; j<=functions ; j++){
                            if(array1[j].charAt(3) == 'L'){
                                if(array1[j].charAt(8) == 'X'){
                                    if(array1[j].charAt(10) == Data && array1[i].charAt(1) != array1[j].charAt(1)){
                                        graph = graph + String.valueOf(array1[i].charAt(1));
                                        graph = graph + "->" + String.valueOf(array1[j].charAt(1)) + ",";
                                        k++;
                                    }
                                }
                            }
                        }
                    }
                }
        }
        
         //spliting array from ',' and puting the reult in an array
         graphArr = graph.split(",");
         d = graphArr.length;
         b = false;
         x = 0;
        
        for(int i = 0 ; i < d ; i++){
                x = graphArr[i].charAt(0);
                int z = graphArr[i].charAt(3);
                for(int j = 0 ; j < d ; j++){
                    if(graphArr[j] != null){
                        if(graphArr[j].charAt(0) == z){
                            z = graphArr[j].charAt(3);
                            if(z == x){
                                jLabel3.setText("Dead Lock!");
                                b = true;
                                break;
                            }
                        }
                    }
                }
            if(b == true){
                break;
            } else {
                jLabel3.setText("not Dead Lock");
            }
        }
        }
        
        
        

	//########################
        //2PL
        //########################
        public  void methodName3(String[] array1) {
            //cheking if the transactions are in the Unlock-after-Lock mode or not
            int functions = array1.length-1;
            boolean b1 = false, b2 = false;
            for(int i=0 ; i<functions ; i++){
                if(array1[i].charAt(3) == 'L'){
                    if(b1 == true){
                        b2 = true;
                        break;
                    }
                    b1 = false;
                    continue;
                }
                else if(array1[i].charAt(3) == 'U'){
                    b1 = true;
                }
            }
        
            if(b2 == true){
                jLabel3.setText("not 2PL!");
            }else{
                jLabel3.setText("2PL.");
            }
        }
        
     

    /**
     * Creates new form DataBase
     */
    public DataBase() {
       
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        twoPLBtn = new javax.swing.JButton();
        Serializable = new javax.swing.JButton();
        deadLockBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        twoPLBtn.setText("2PL");
        twoPLBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoPLBtnActionPerformed(evt);
            }
        });

        Serializable.setText("Serializable");
        Serializable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SerializableActionPerformed(evt);
            }
        });

        deadLockBtn.setText("Dead Lock");
        deadLockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deadLockBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Please enter your data and select one of the buttons.");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("Enter your data...");
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Result:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deadLockBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(twoPLBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Serializable, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(twoPLBtn)
                        .addGap(24, 24, 24)
                        .addComponent(Serializable)
                        .addGap(24, 24, 24)
                        .addComponent(deadLockBtn))
                    .addComponent(jScrollPane1))
                .addGap(45, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void twoPLBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoPLBtnActionPerformed
        // TODO add your handling code here:
        String t = jTextArea1.getText();
            
            if (t.length() != 0) {
                String array[] = t.split(",");
                methodName3(array);
            }
    }//GEN-LAST:event_twoPLBtnActionPerformed

    private void SerializableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SerializableActionPerformed
        // TODO add your handling code here:
        String t = jTextArea1.getText();
            if (t.length() != 0) {
                String array[] = t.split(",");
                CS(array);
            }
    }//GEN-LAST:event_SerializableActionPerformed

    private void deadLockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deadLockBtnActionPerformed
        // TODO add your handling code here:
        String t = jTextArea1.getText();
            if (t.length() != 0) {
                
                String array[] = t.split(",");
                DL(array);
            }
    }//GEN-LAST:event_deadLockBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataBase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataBase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Serializable;
    private javax.swing.JButton deadLockBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton twoPLBtn;
    // End of variables declaration//GEN-END:variables
}
