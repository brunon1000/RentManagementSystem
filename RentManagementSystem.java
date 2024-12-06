import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RentManagementSystem {
    private PropertyOwner propertyOwner = new PropertyOwner();
    private Renter renter = new Renter();

    public RentManagementSystem() {
        createMainMenu();
    }

    private void createMainMenu() {
        JFrame frame = new JFrame("Rent Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton ownerButton = new JButton("Property Owner Menu");
        JButton renterButton = new JButton("Renter Menu");
        JButton exitButton = new JButton("Exit");

        ownerButton.addActionListener(e -> openOwnerMenu(frame));
        renterButton.addActionListener(e -> openRenterMenu(frame));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(ownerButton);
        panel.add(renterButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void openOwnerMenu(JFrame mainFrame) {
        JFrame ownerFrame = new JFrame("Property Owner Menu");
        ownerFrame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addButton = new JButton("Add Property");
        JButton modifyButton = new JButton("Modify Property");
        JButton deleteButton = new JButton("Delete Property");
        JButton viewButton = new JButton("View All Properties");
        JButton backButton = new JButton("Back to Main Menu");

        addButton.addActionListener(e -> addPropertyDialog());
        modifyButton.addActionListener(e -> modifyPropertyDialog());
        deleteButton.addActionListener(e -> deletePropertyDialog());
        viewButton.addActionListener(e -> viewPropertiesDialog());

        backButton.addActionListener(e -> {
            ownerFrame.dispose();
            mainFrame.setVisible(true);
        });

        panel.add(addButton);
        panel.add(modifyButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(backButton);

        ownerFrame.add(panel);
        ownerFrame.setVisible(true);
        mainFrame.setVisible(false);
    }

    private void addPropertyDialog() {
        JFrame addFrame = new JFrame("Add Property");
        addFrame.setSize(300, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JLabel rentLabel = new JLabel("Rent:");
        JTextField rentField = new JTextField();

        JLabel amenitiesLabel = new JLabel("Amenities:");
        JTextField amenitiesField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String location = locationField.getText();
            double rent = Double.parseDouble(rentField.getText());
            String amenities = amenitiesField.getText();

            int propertyId = propertyOwner.addProperty(location, rent, amenities);
            JOptionPane.showMessageDialog(addFrame, "Property added with ID: " + propertyId);
            addFrame.dispose();
        });

        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(rentLabel);
        panel.add(rentField);
        panel.add(amenitiesLabel);
        panel.add(amenitiesField);
        panel.add(addButton);

        addFrame.add(panel);
        addFrame.setVisible(true);
    }

    private void modifyPropertyDialog() {
        // Implementation for modifying properties
    }

    private void deletePropertyDialog() {
        // Implementation for deleting properties
    }

    private void viewPropertiesDialog() {
        List<Property> properties = propertyOwner.getProperties();
        StringBuilder message = new StringBuilder("Properties:\n");
        for (Property property : properties) {
            message.append(property).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private void openRenterMenu(JFrame mainFrame) {
        // Implementation for Renter Menu
    }

    public static void main(String[] args) {
        new RentManagementSystem();
    }
}
