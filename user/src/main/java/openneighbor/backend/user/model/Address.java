package openneighbor.backend.user.model;

public class Address{

    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public Address ()
    {
        name = "";
        streetAddress = "";
        city = "";
        state = "";
        zipCode = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String theName) {
        this.name = theName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean equals (Object right)
    {
        Address addr = (Address)right;
        return name.equals(addr.name)
                && streetAddress.equals(addr.streetAddress)
                && city.equals(addr.city)
                && state.equals(addr.state)
                && zipCode.equals(addr.zipCode);
    }

    public String toString()
    {
        return name + ": " + streetAddress + ": " 
                + city + ", " + state + " " + zipCode;
    }

}