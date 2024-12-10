import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PropertyOwnerMenu {
    private PropertyOwner propertyOwner;

    public PropertyOwnerMenu(PropertyOwner propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public void show() {
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

                String locationInput = JOptionPane.showInputDialog(frame, "Enter New Location (Leave blank to keep current):");
                String location = (locationInput == null || locationInput.trim().isEmpty()) ? null : locationInput;

                String rentInput = JOptionPane.showInputDialog(frame, "Enter New Rent (Leave blank to keep current):");
                Double rent = (rentInput == null || rentInput.trim().isEmpty()) ? null : Double.parseDouble(rentInput);

                String amenitiesInput = JOptionPane.showInputDialog(frame, "Enter New Amenities (Leave blank to keep current):");
                String amenities = (amenitiesInput == null || amenitiesInput.trim().isEmpty()) ? null : amenitiesInput;

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
            RentManagementSystem.getInstance().openMainMenu();
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
}
