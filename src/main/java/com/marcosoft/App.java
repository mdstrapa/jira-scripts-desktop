package com.marcosoft;
import com.marcosoft.model.OperationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    static OperationType selectedOperation;

    public static void main(String[] args) {
        // Create and set up the GUI on the event dispatch thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create the JFrame
        JFrame frame = new JFrame("Jira Scripts Desktop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new GridLayout(0,1,30,30));

        JPanel panelOperationsToSelect = new JPanel();
        panelOperationsToSelect.setLayout(new GridLayout(0,1,0,0));

        JLabel lblGreetings = new JLabel("Welcome To The Jira Scripts Desktop");
        lblGreetings.setHorizontalAlignment(JLabel.CENTER);
        JLabel lblSelectDesired = new JLabel("Select the desired operation:");
        JButton btnSelectUpdate = new JButton("Update");
        JButton btnSelectTransition = new JButton("Transition");
        JButton btnSelectDelete = new JButton("Delete");

        panelOperationsToSelect.add(lblGreetings);
        panelOperationsToSelect.add(lblSelectDesired);
        panelOperationsToSelect.add(btnSelectUpdate);
        panelOperationsToSelect.add(btnSelectTransition);
        panelOperationsToSelect.add(btnSelectDelete);


        JPanel panelRunOperation = new JPanel();
        panelRunOperation.setLayout(new BoxLayout(panelRunOperation,BoxLayout.Y_AXIS));

        JButton btnRunOperation = new JButton("Run Operation!");
        btnRunOperation.setForeground(Color.RED);
        btnRunOperation.setSize(200,200);
        btnRunOperation.setHorizontalAlignment(JButton.CENTER);
        panelRunOperation.add(btnRunOperation);

        JTextArea txtAreaIssueKeyList = new JTextArea("<paste the issue key list here>");
        txtAreaIssueKeyList.setBounds(10,30, 400,400);

        // Add an ActionListener to the button
        btnRunOperation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the label text when the button is clicked
                frame.setBackground(Color.BLUE);
                frame.setTitle("Operation Succeed!");

                String[] issueKeyArray = txtAreaIssueKeyList.getText().split("\n");
                for(String issueKey:issueKeyArray){
                    JOptionPane.showMessageDialog(frame,issueKey);
                }

            }
        });

        btnSelectUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOperation = OperationType.UPDATE;
                btnSelectUpdate.setForeground(Color.ORANGE);
                btnSelectTransition.setForeground(Color.BLACK);
                btnSelectDelete.setForeground(Color.BLACK);
            }
        });

        btnSelectTransition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOperation = OperationType.TRANSITION;
                btnSelectUpdate.setForeground(Color.BLACK);
                btnSelectTransition.setForeground(Color.BLUE);
                btnSelectDelete.setForeground(Color.BLACK);
            }
        });

        btnSelectDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOperation = OperationType.DELETE;
                btnSelectUpdate.setForeground(Color.BLACK);
                btnSelectTransition.setForeground(Color.BLACK);
                btnSelectDelete.setForeground(Color.RED);
            }
        });


        // Add components to the JFrame
        frame.add(panelOperationsToSelect);
        frame.add(txtAreaIssueKeyList);
        frame.add(panelRunOperation);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set the JFrame to be visible
        frame.setVisible(true);
    }
}
