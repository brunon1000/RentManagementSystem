import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RenterMenu {
    private PropertyOwner propertyOwner;
    private Renter renter;
    private JFrame frame;
    private JButton searchPropertiesButton;
    private JButton viewPropertyDetailsButton;
    private JButton rentPropertyButton;
    private JButton stopRentalButton;
    private JButton backButton;


    public RenterMenu(PropertyOwner propertyOwner, Renter renter) {
        this.propertyOwner = propertyOwner;
        this.renter = renter;
        frame = new JFrame("Renter Features");
        searchPropertiesButton = new JButton("Search Properties");
        viewPropertyDetailsButton = new JButton("View Property Details");
        rentPropertyButton = new JButton("Rent Property");
        stopRentalButton = new JButton("Stop Rental");
        backButton = new JButton("Back");
    }

    public void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 2));

        searchPropertiesButton.addActionListener(e -> searchProperties());
        viewPropertyDetailsButton.addActionListener(e -> viewPropertyDetails());
        rentPropertyButton.addActionListener(e -> rentProperty());
        stopRentalButton.addActionListener(e -> stopRental());
        backButton.addActionListener(e -> backToMainMenu());

        frame.add(searchPropertiesButton);
        frame.add(viewPropertyDetailsButton);
        frame.add(rentPropertyButton);
        frame.add(stopRentalButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void searchProperties(){
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
    }

    private void viewPropertyDetails(){
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
    }

    private void rentProperty(){
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
    }

    private void stopRental(){
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
    }

    private void backToMainMenu(){
        frame.dispose();
        RentManagementSystem.getInstance().openMainMenu();
    }
}
