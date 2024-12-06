import java.util.ArrayList;
import java.util.List;

public class Renter {
    public List<Property> searchProperties(List<Property> properties, String location, Double budget) {
        List<Property> results = new ArrayList<>();
        for (Property property : properties) {
            if (property.isAvailable() &&
                    (location == null || property.getLocation().equalsIgnoreCase(location)) &&
                    (budget == null || property.getRent() <= budget)) {
                results.add(property);
            }
        }
        return results;
    }

    public String viewPropertyDetails(Property property) {
        if (property == null) {
            return "Property not found.";
        }
        return property.toString();
    }

    public String rentProperty(Property property) {
        if (property.isAvailable()) {
            property.setAvailable(false);
            return "Property rented successfully.";
        }
        return "Property is not available for rent.";
    }

    public String stopRental(Property property) {
        if (!property.isAvailable()) {
            property.setAvailable(true);
            return "Rental terminated successfully.";
        }
        return "Property is already available.";
    }
}
