import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PropertyOwner {
    private List<Property> properties;

    public PropertyOwner() {
        properties = new ArrayList<>();
    }

    public int addProperty(String location, double rent, String amenities) {
        int propertyId = properties.size() + 1;
        Property newProperty = new Property(propertyId, location, rent, amenities);
        properties.add(newProperty);
        return propertyId;
    }

    public String modifyProperty(int propertyId, String location, Double rent, String amenities) {
        for (Property property : properties) {
            if (property.getPropertyId() == propertyId) {
                property.updateDetails(location, rent, amenities);
                return "Property details updated successfully.";
            }
        }
        return "Error: Property with ID " + propertyId + " not found.";
    }

    public String deleteProperty(int propertyId) {
        boolean removed = properties.removeIf(property -> property.getPropertyId() == propertyId);
        return removed ? "Property deleted successfully." : "Error: Property with ID " + propertyId + " not found.";
    }

    public List<Property> getProperties() {
        return properties;
    }

    public String generateRentalReport() {
        StringBuilder report = new StringBuilder();
        double totalIncome = 0;

        report.append("Property ID,Location,Rent,Available\n");
        for (Property property : properties) {
            if (!property.isAvailable()) {
                report.append(property.getPropertyId()).append(",")
                      .append(property.getLocation()).append(",")
                      .append(property.getRent()).append(",")
                      .append("Rented").append("\n");
                totalIncome += property.getRent();
            }
        }

        report.append("\nTotal Rental Income: $").append(totalIncome);
        return report.toString();
    }

    public String downloadRentalReport(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Property ID,Location,Rent,Available\n");
            for (Property property : properties) {
                if (!property.isAvailable()) {
                    writer.write(property.getPropertyId() + "," +
                            property.getLocation() + "," +
                            property.getRent() + "," +
                            "Rented" + "\n");
                }
            }
            return "Rental report saved to " + filePath;
        } catch (IOException e) {
            return "Error saving rental report: " + e.getMessage();
        }
    }
}
