import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RenterMenu {
    private PropertyOwner propertyOwner;
    private Renter renter;

    public RenterMenu(PropertyOwner propertyOwner, Renter renter) {
        this.propertyOwner = propertyOwner;
        this.renter = renter;
    }

    public void show() {
        JFrame frame = new JFrame("Renter Features");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 2));

        JButton searchPropertiesButton = new JButton("Search Properties");
        JButton viewPropertyDetailsButton = new JButton("View Property Details");
        JButton rentPropertyButton = new JButton("Rent Property");
        JButton stopRentalButton = new JButton("Stop Rental");
        JButton backButton = new JButton("Back");

        searchPropertiesButton.addActionListener(e -> {
            try {
                String location = JOptionPane.showInputDialog(frame, "Enter Location:");
                String budgetInput = JOptionPane.showInputDialog(frame, "Enter Budget:");
                Double budget = (budgetInput == null || budgetInput.trim().isEmpty()) ? null : Double.parseDouble(budgetInput);

                List<Property> results = renter.searchProperties(propertyOwner.getProperties(), location, budget);
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No properties match your search criteria.");
                } else {
                    StringBuilder resultList = new StringBuilder("Available Properties:\n");
                    for (Property property : results) {
                        resultList.append(property.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, resultList.toString());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        viewPropertyDetailsButton.addActionListener(e -> {
            try {
                String idInput = JOptionPane.showInputDialog(frame, "Enter Property ID to View:");
                if (idInput == null || idInput.trim().isEmpty()) throw new IllegalArgumentException("Property ID cannot be empty.");
                int propertyId = Integer.parseInt(idInput);

                Property property = propertyOwner.getProperties().stream()
                        .filter(p -> p.getPropertyId() == propertyId)
                        .findFirst()
                        .orElse(null);

                if (property == null) {
                    JOptionPane.showMessageDialog(frame, "Property not found.");
                } else {
                    String details = renter.viewPropertyDetails(property);
                    JOptionPane.showMessageDialog(frame, details);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        rentPropertyButton.addActionListener(e -> {
            try {
                String idInput = JOptionPane.showInputDialog(frame, "Enter Property ID to Rent:");
                if (idInput == null || idInput.trim().isEmpty()) throw new IllegalArgumentException("Property ID cannot be empty.");
                int propertyId = Integer.parseInt(idInput);

                Property property = propertyOwner.getProperties().stream()
                        .filter(p -> p.getPropertyId() == propertyId)
                        .findFirst()
                        .orElse(null);

                if (property == null) {
                    JOptionPane.showMessageDialog(frame, "Property not found.");
                } else {
                    String result = renter.rentProperty(property);
                    JOptionPane.showMessageDialog(frame, result);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        stopRentalButton.addActionListener(e -> {
            try {
                String idInput = JOptionPane.showInputDialog(frame, "Enter Property ID to Stop Rental:");
                if (idInput == null || idInput.trim().isEmpty()) throw new IllegalArgumentException("Property ID cannot be empty.");
                int propertyId = Integer.parseInt(idInput);

                Property property = propertyOwner.getProperties().stream()
                        .filter(p -> p.getPropertyId() == propertyId)
                        .findFirst()
                        .orElse(null);

                if (property == null) {
                    JOptionPane.showMessageDialog(frame, "Property not found.");
                } else {
                    String result = renter.stopRental(property);
                    JOptionPane.showMessageDialog(frame, result);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            RentManagementSystem.getInstance().openMainMenu();
        });

        frame.add(searchPropertiesButton);
        frame.add(viewPropertyDetailsButton);
        frame.add(rentPropertyButton);
        frame.add(stopRentalButton);
        frame.add(backButton);

        frame.setVisible(true);
    }
}
