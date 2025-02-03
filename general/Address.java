package general;

final public class Address {
    private String addressLine1;
	private String addressLine2;
	private String city;
	private int zipCode;
	private String state;
	private String country;


    public Address(String adln1, String adln2, String city, int zipCode, String state, String country) {
        this.addressLine1 = adln1;
        this.addressLine2 = adln2;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
    }
    public String toString() {return addressLine1+", "+addressLine2+", "+city+" City, "+zipCode+" "+state+", "+country;}

	// Setter Methods
    public void setAddressLine1(String addressLine) {addressLine1 = addressLine;}
    public void setAddressLine2(String addressLine) {addressLine2 = addressLine;}
    public void setCity(String theCity) {city = theCity;}
    public void setState(String theState) {state = theState;}
    public void setZipCode(int zip) {zipCode = zip;}
    public void setCountry(String theCountry) {country = theCountry;}

    // Getter Methods
    public String getAddressLine1() {return addressLine1;}
    public String getAddressLine2() {return addressLine2;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public int getZipCode() {return zipCode;}
    public String getCountry() {return country;}
}