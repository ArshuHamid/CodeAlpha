import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AttractiveTravelPlanner extends JFrame {
    private JTextField destinationField, dateField;
    private JCheckBox sightseeingCheckBox, diningCheckBox, shoppingCheckBox, outdoorCheckBox;
    private JTextArea itineraryArea;
    private JButton planButton;
    private JLabel statusLabel;

    public AttractiveTravelPlanner() {
        createUI();
    }

    private void createUI() {
        setTitle("Attractive Travel Itinerary Planner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel destinationLabel = new JLabel("Destination:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(destinationLabel, gbc);

        destinationField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(destinationField, gbc);

        JLabel dateLabel = new JLabel("Date:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(dateLabel, gbc);

        dateField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(dateField, gbc);

        JLabel preferencesLabel = new JLabel("Preferences:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(preferencesLabel, gbc);

        sightseeingCheckBox = new JCheckBox("Sightseeing");
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(sightseeingCheckBox, gbc);

        diningCheckBox = new JCheckBox("Dining");
        gbc.gridx = 2;
        gbc.gridy = 2;
        inputPanel.add(diningCheckBox, gbc);

        shoppingCheckBox = new JCheckBox("Shopping");
        gbc.gridx = 3;
        gbc.gridy = 2;
        inputPanel.add(shoppingCheckBox, gbc);

        outdoorCheckBox = new JCheckBox("Outdoor Activities");
        gbc.gridx = 4;
        gbc.gridy = 2;
        inputPanel.add(outdoorCheckBox, gbc);

        planButton = new JButton("Plan Trip");
        planButton.setBackground(new Color(60, 179, 113));
        planButton.setForeground(Color.WHITE);
        planButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        inputPanel.add(planButton, gbc);

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setForeground(Color.RED);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        inputPanel.add(statusLabel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        itineraryArea = new JTextArea(20, 70);
        itineraryArea.setEditable(false);
        itineraryArea.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(itineraryArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        planButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                planTrip();
            }
        });

        setVisible(true);
    }

    private void planTrip() {
        String destination = destinationField.getText();
        String date = dateField.getText();
        StringBuilder preferences = new StringBuilder();

        if (sightseeingCheckBox.isSelected()) {
            preferences.append("Sightseeing, ");
        }
        if (diningCheckBox.isSelected()) {
            preferences.append("Dining, ");
        }
        if (shoppingCheckBox.isSelected()) {
            preferences.append("Shopping, ");
        }
        if (outdoorCheckBox.isSelected()) {
            preferences.append("Outdoor Activities, ");
        }

        if (preferences.length() == 0) {
            statusLabel.setText("Please select at least one preference.");
            itineraryArea.setText("");
            return;
        }

        String itinerary = generateItinerary(destination, date, preferences.toString());
        itineraryArea.setText(itinerary);
        statusLabel.setText("");
    }

    private String generateItinerary(String destination, String date, String preferences) {
        // Dummy function to generate itinerary
        return "Your trip to " + destination + " on " + date + ":\n"
                + "Based on your preferences: " + preferences + "\n"
                + "1. Visit famous landmark X\n"
                + "2. Have lunch at popular restaurant Y\n"
                + "3. Explore historic site Z\n"
                + "4. Enjoy dinner at scenic restaurant A\n"
                + "5. Attend local cultural event B\n"
                + "6. Relax at beautiful beach C\n"
                + "7. Return to hotel";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AttractiveTravelPlanner();
            }
        });
    }
}
