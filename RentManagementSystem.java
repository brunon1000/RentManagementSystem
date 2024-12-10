import java.awt.*;
import java.util.List;
import javax.swing.*;

public class RentManagementSystem {
    public static void main(String[] args) {
        PropertyOwner propertyOwner = new PropertyOwner();
        Renter renter = new Renter();

        openMainMenu(propertyOwner, renter);
    }

    private static void openMainMenu(PropertyOwner propertyOwner, Renter renter) {
        JFrame mainFrame = new JFrame("Rent Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 200);

        JButton propertyOwnerButton = new JButton("Property Owner");
        JButton renterButton = new JButton("Renter");

        propertyOwnerButton.addActionListener(e -> {
            mainFrame.dispose();
            openPropertyOwnerInterface(propertyOwner);
        });

        renterButton.addActionListener(e -> {
            mainFrame.dispose();
            openRenterInterface(propertyOwner, renter);
        });

        JPanel panel = new JPanel();
        panel.add(propertyOwnerButton);
        panel.add(renterButton);

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    private static void openPropertyOwnerInterface(PropertyOwner propertyOwner) {
        JFrame frame = new JFrame("Property Owner Features");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 2));

        JButton addPropertyButton = new JButton("Add Property");
        JButton modifyPropertyButton = new JButton("Modify Property");
        JButton deletePropertyButton = new JButton("Delete Property");
        JButton viewPropertiesButton = new JButton("View Properties");
        JButton generateReportButton = new JButton("Generate Rental Report");
        JButton downloadReportButton = new JButton("Download Rental Report");
        JButton backButton = new JButton("Back");

        // Add Property
        addPropertyButton.addActionListener(e -> {
            try {
                String location = JOptionPane.showInputDialog(frame, "Enter Location:");
                if (location == null || location.trim().isEmpty()) throw new IllegalArgumentException("Location cannot be empty.");

                String rentInput = JOptionPane.showInputDialog(frame, "Enter Rent:");
                if (rentInput == null || rentInput.trim().isEmpty()) throw new IllegalArgumentException("Rent cannot be empty.");
                double rent = Double.parseDouble(rentInput);

                String amenities = JOptionPane.showInputDialog(frame, "Enter Amenities:");
                if (amenities == null || amenities.trim().isEmpty()) throw new IllegalArgumentException("Amenities cannot be empty.");

                int propertyId = propertyOwner.addProperty(location.trim(), rent, amenities.trim());
                JOptionPane.showMessageDialog(frame, "Property added with ID: " + propertyId);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        // Modify Property
        modifyPropertyButton.addActionListener(e -> {
            try {
                String idInput = JOptionPane.showInputDialog(frame, "Enter Property ID to Modify:");
                if (idInput == null || idInput.trim().isEmpty()) throw new IllegalArgumentException("Property ID cannot be empty.");
                int propertyId = Integer.parseInt(idInput);

                String location = JOptionPane.showInputDialog(frame, "Enter New Location (Leave blank to keep current):");
                String rentInput = JOptionPane.showInputDialog(frame, "Enter New Rent (Leave blank to keep current):");
                Double rent = (rentInput == null || rentInput.trim().isEmpty()) ? null : Double.parseDouble(rentInput);
                String amenities = JOptionPane.showInputDialog(frame, "Enter New Amenities (Leave blank to keep current):");

                String result = propertyOwner.modifyProperty(propertyId, location, rent, amenities);
                JOptionPane.showMessageDialog(frame, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        // Delete Property
        deletePropertyButton.addActionListener(e -> {
            try {
                String idInput = JOptionPane.showInputDialog(frame, "Enter Property ID to Delete:");
                if (idInput == null || idInput.trim().isEmpty()) throw new IllegalArgumentException("Property ID cannot be empty.");
                int propertyId = Integer.parseInt(idInput);

                String result = propertyOwner.deleteProperty(propertyId);
                JOptionPane.showMessageDialog(frame, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        // View Properties
        viewPropertiesButton.addActionListener(e -> {
            StringBuilder propertiesList = new StringBuilder();
            List<Property> properties = propertyOwner.getProperties();
            if (properties.isEmpty()) {
                propertiesList.append("No properties found.");
            } else {
                for (Property property : properties) {
                    propertiesList.append(property.toString()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(frame, propertiesList.toString(), "Properties List", JOptionPane.INFORMATION_MESSAGE);
        });

        // Generate Rental Report
        generateReportButton.addActionListener(e -> {
            String report = propertyOwner.generateRentalReport();
            JOptionPane.showMessageDialog(frame, report, "Rental Report", JOptionPane.INFORMATION_MESSAGE);
        });

        // Download Rental Report
        downloadReportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Rental Report");
            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String result = propertyOwner.downloadRentalReport(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(frame, result);
            }
        });

        // Back Button
        backButton.addActionListener(e -> {
            frame.dispose();
            openMainMenu(propertyOwner, new Renter());
        });

        frame.add(addPropertyButton);
        frame.add(modifyPropertyButton);
        frame.add(deletePropertyButton);
        frame.add(viewPropertiesButton);
        frame.add(generateReportButton);
        frame.add(downloadReportButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private static void openRenterInterface(PropertyOwner propertyOwner, Renter renter) {
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
            openMainMenu(propertyOwner, renter);
        });

        frame.add(searchPropertiesButton);
        frame.add(viewPropertyDetailsButton);
        frame.add(rentPropertyButton);
        frame.add(stopRentalButton);
        frame.add(backButton);

        frame.setVisible(true);
    }
}
