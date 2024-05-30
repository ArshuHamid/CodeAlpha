import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeTracker extends JFrame {

    private ArrayList<Integer> grades;
    private JTextField gradeInput;
    private JLabel averageResult;
    private JLabel highestResult;
    private JLabel lowestResult;

    public StudentGradeTracker() {
        grades = new ArrayList<>();
        createUI();
    }

    private void createUI() {
        setTitle("Student Grade Tracker");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set background color
        getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Grade input
        JLabel gradeLabel = new JLabel("Enter grade:");
        gradeLabel.setForeground(new Color(60, 63, 65)); // Set text color
        gradeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(gradeLabel, gbc);

        gradeInput = new JTextField(10);
        gradeInput.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(gradeInput, gbc);

        JButton addButton = new JButton("Add Grade");
        addButton.setBackground(new Color(100, 149, 237));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(addButton, gbc);

        // Average result
        JLabel averageLabel = new JLabel("Average: ");
        averageLabel.setForeground(new Color(60, 63, 65));
        averageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(averageLabel, gbc);

        averageResult = new JLabel();
        averageResult.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(averageResult, gbc);

        // Highest result
        JLabel highestLabel = new JLabel("Highest: ");
        highestLabel.setForeground(new Color(60, 63, 65));
        highestLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(highestLabel, gbc);

        highestResult = new JLabel();
        highestResult.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(highestResult, gbc);

        // Lowest result
        JLabel lowestLabel = new JLabel("Lowest: ");
        lowestLabel.setForeground(new Color(60, 63, 65));
        lowestLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lowestLabel, gbc);

        lowestResult = new JLabel();
        lowestResult.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(lowestResult, gbc);

        // Calculate button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(60, 179, 113));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(calculateButton, gbc);

        // Button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGrade();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });

        setVisible(true);
    }

    private void addGrade() {
        try {
            int grade = Integer.parseInt(gradeInput.getText());
            grades.add(grade);
            gradeInput.setText("");
            JOptionPane.showMessageDialog(this, "Grade " + grade + " has been added.", "Grade Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateGrades() {
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grades entered.", "No Grades", JOptionPane.WARNING_MESSAGE);
            return;
        }
        averageResult.setText(String.valueOf(calculateAverage()));
        highestResult.setText(String.valueOf(getHighestGrade()));
        lowestResult.setText(String.valueOf(getLowestGrade()));
    }

    private double calculateAverage() {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    private int getHighestGrade() {
        int highest = grades.get(0);
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    private int getLowestGrade() {
        int lowest = grades.get(0);
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGradeTracker();
            }
        });
    }
}
