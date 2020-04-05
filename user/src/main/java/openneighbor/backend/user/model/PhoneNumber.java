package openneighbor.backend.user.model;

public class PhoneNumber {
    
    private String phoneNumber;

    public PhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneNumber(String input){
        this.phoneNumber = input;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

}