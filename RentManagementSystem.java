import javax.swing.*;

public class RentManagementSystem {
    private PropertyOwner propertyOwner;
    private Renter renter;
    private static RentManagementSystem instance;

    private RentManagementSystem() {
        this.propertyOwner = new PropertyOwner();
        this.renter = new Renter();   
    }

    public static RentManagementSystem getInstance() {
        if (instance == null) {
            instance = new RentManagementSystem();
        }
        return instance;
    }

    public void openMainMenu() {
        JFrame mainFrame = new JFrame("Rent Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 200);

        JButton propertyOwnerButton = new JButton("Property Owner");
        JButton renterButton = new JButton("Renter");

        propertyOwnerButton.addActionListener(e -> openPropertyOwnerMenu(mainFrame));
        renterButton.addActionListener(e -> openRenterMenu(mainFrame));

        JPanel panel = new JPanel();
        panel.add(propertyOwnerButton);
        panel.add(renterButton);

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    private void openPropertyOwnerMenu(JFrame mainFrame){
        mainFrame.dispose();
        PropertyOwnerMenu propertyOwnerMenu = new PropertyOwnerMenu(propertyOwner);
        propertyOwnerMenu.show();
    }

    private void openRenterMenu(JFrame mainFrame){
        mainFrame.dispose();
        RenterMenu renterMenu = new RenterMenu(propertyOwner, renter);
        renterMenu.show();
    }
}
