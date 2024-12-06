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

    public boolean rentProperty(Property property) {
        if (property.isAvailable()) {
            property.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean stopRental(Property property) {
        if (!property.isAvailable()) {
            property.setAvailable(true);
            return true;
        }
        return false;
    }
}
