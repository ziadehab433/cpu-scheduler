package com.mycompany.projectos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private JPanel inputPanel, resultPanel, ganttChartPanel;
    private JLabel arrivalLabel, burstLabel, priorityLabel, averageLabel;
    private JTextField arrivalField, burstField, priorityField;
    private JButton addButton, startButton;
    private JTextArea ganttChartTextArea, resultTextArea;
    private List<Process> processes;

    public GUI() {
        // Set up the main frame
        setTitle("Priority Scheduling Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Initialize components
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5)); // Add padding between components

        arrivalLabel = new JLabel("Arrival Time:");
        burstLabel = new JLabel("Burst Time:");
        priorityLabel = new JLabel("Priority:");

        arrivalField = new JTextField();
        burstField = new JTextField();
        priorityField = new JTextField();

        addButton = new JButton("Add Process");
        startButton = new JButton("Start Simulation");

        ganttChartTextArea = new JTextArea(15, 60); // Increase the size of the text area
        ganttChartTextArea.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(ganttChartTextArea);

        resultTextArea = new JTextArea(10, 60); // Adjusted size of the text area
        resultTextArea.setEditable(false);
        JScrollPane scrollPane2 = new JScrollPane(resultTextArea);

        ganttChartPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the ganttChartPanel
        ganttChartPanel.add(new JLabel("Gantt Chart:"), BorderLayout.NORTH); // Align label to the top
        ganttChartPanel.add(scrollPane1, BorderLayout.CENTER); // Use CENTER for the text area

        resultPanel = new JPanel(new BorderLayout()); // Use BorderLayout for the resultPanel
        resultPanel.add(new JLabel("Results:"), BorderLayout.NORTH); // Align label to the top
        resultPanel.add(scrollPane2, BorderLayout.CENTER); // Use CENTER for the text area

        // Add components to input panel
        inputPanel.add(arrivalLabel);
        inputPanel.add(arrivalField);
        inputPanel.add(burstLabel);
        inputPanel.add(burstField);
        inputPanel.add(priorityLabel);
        inputPanel.add(priorityField);
        inputPanel.add(addButton);
        inputPanel.add(startButton);

        // Set padding for input panel
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set padding for buttons
        addButton.setMargin(new Insets(10, 10, 10, 10));
        startButton.setMargin(new Insets(10, 10, 10, 10));

        // Add input panel and result panel to the main frame
        add(inputPanel, BorderLayout.NORTH);
        add(ganttChartPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Initialize processes list
        processes = new ArrayList<>();

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!processes.isEmpty()) {
                    startSimulation();
                }
            }
        });

        // Display the frame
        setVisible(true);
    }

    private void addProcess() {
        try {
            int arrivalTime = Integer.parseInt(arrivalField.getText());
            int burstTime = Integer.parseInt(burstField.getText());
            int priority = Integer.parseInt(priorityField.getText());

            // Check if inputs are positive
            if (arrivalTime < 0 || burstTime < 0 || priority < 0) {
                JOptionPane.showMessageDialog(this, "Please enter positive values",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Process process = new Process(arrivalTime, burstTime, priority);
            processes.add(process);

            // Clear input fields
            arrivalField.setText("");
            burstField.setText("");
            priorityField.setText("");

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Invalid input format. Please enter positive numeric values.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startSimulation() {
        // Create Algorithm instance with processes
        Algorithm algorithm = new Algorithm(new ArrayList<>(processes));

        // Run the scheduling algorithm
        processes = algorithm.start();
        GanttChart gChart = algorithm.getGanttChart();
        ArrayList<GanttNode> ChartArray = gChart.getChartArray();

        // Display Gantt chart
        drawGanttChart(ChartArray);

        // Calculate and display results
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        double totalResponseTime = 0;
        StringBuilder resultText = new StringBuilder("Results:\n");
        for (Process process : processes) {
            totalWaitingTime += process.getWaitingTime();
            totalTurnaroundTime += process.getTurnaroundTime();
            totalResponseTime += process.getResponseTime();
            resultText.append("Process ID: ").append(process.getProcessId())
                    .append(", Arrival Time: ").append(process.getArrivalTime())
                    .append(", Burst Time: ").append(process.getBurstTime()) // Add burst time
                    .append(", Priority: ").append(process.getPriority()) // Add priority
                    .append(", Waiting Time: ").append(process.getWaitingTime())
                    .append(", Turnaround Time: ").append(process.getTurnaroundTime())
                    .append(", Response Time: ").append(process.getResponseTime())
                    .append("\n");
        }
        double averageWaitingTime = totalWaitingTime / (processes.size() + .00);
        double averageTurnaroundTime = totalTurnaroundTime / processes.size();
        double averageResponseTime = totalResponseTime / processes.size();
        resultText.append("\nAverage Waiting Time: ").append(new DecimalFormat("#.00").format(averageWaitingTime))
                .append(", Average Turnaround Time: ").append(new DecimalFormat("#.00").format(averageTurnaroundTime))
                .append(", Average Response Time: ").append(new DecimalFormat("#.00").format(averageResponseTime));
        resultTextArea.setText(resultText.toString());
    }


    public void drawGanttChart(ArrayList<GanttNode> ChartArray){
        ganttChartTextArea.setFont(new Font("MONOSPACED", Font.PLAIN, 14));
        StringBuilder ganttChartText = new StringBuilder("Gantt Chart:\n");

        ganttChartText.append(ChartArray.get(0).getStart() > 9 ? ChartArray.get(0).getStart(): "0" + ChartArray.get(0).getStart());
        for(GanttNode node : ChartArray) {
            int someValue = ((node.getEnd() - node.getStart()) * 3) - 1;
            ganttChartText.append(" ".repeat(Math.max(0, someValue)));
            ganttChartText.append(node.getEnd() > 9 ? node.getEnd(): "0" + node.getEnd());
        }
        for(int j = 0; j < 2; j++) {
            ganttChartText.append("\n|");
            for (GanttNode node : ChartArray) {
                int end = node.getEnd();
                ganttChartText.append(" ##".repeat(Math.max(0, end - node.getStart())));
                ganttChartText.append("|");
            }
        }
        ganttChartText.append("\n");
        for(GanttNode node : ChartArray) {
            ganttChartText.append(node.getName());
            int someValue = ((node.getEnd() - node.getStart()) * 3) - node.getName().length() + 1;
            ganttChartText.append(" ".repeat(Math.max(0, someValue)));
        }
        ganttChartTextArea.setText(ganttChartText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
