public class Property {
    private int propertyId;
    private String location;
    private double rent;
    private String amenities;
    private boolean isAvailable;

    public Property(int propertyId, String location, double rent, String amenities) {
        this.propertyId = propertyId;
        this.location = location;
        this.rent = rent;
        this.amenities = amenities;
        this.isAvailable = true;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public String getLocation() {
        return location;
    }

    public double getRent() {
        return rent;
    }

    public String getAmenities() {
        return amenities;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void updateDetails(String location, Double rent, String amenities) {
        if (location != null) this.location = location;
        if (rent != null) this.rent = rent;
        if (amenities != null) this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Property ID: " + propertyId + ", Location: " + location + ", Rent: $" + rent +
                ", Amenities: " + amenities + ", Available: " + isAvailable;
    }
}
