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

    public boolean modifyProperty(int propertyId, String location, Double rent, String amenities) {
        for (Property property : properties) {
            if (property.getPropertyId() == propertyId) {
                property.updateDetails(location, rent, amenities);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProperty(int propertyId) {
        return properties.removeIf(property -> property.getPropertyId() == propertyId);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Property> getRentedProperties() {
        List<Property> rented = new ArrayList<>();
        for (Property property : properties) {
            if (!property.isAvailable()) {
                rented.add(property);
            }
        }
        return rented;
    }
}
