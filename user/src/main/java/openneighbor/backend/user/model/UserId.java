package openneighbor.backend.user.model;

public abstract class UserId {

    private String userId;

    public UserId(String userId)
    {
        this.userId = userId;
    }
    public void setUserId(String input){
        this.userId = input;
    }
    public String getUserId(){
        return userId;
    }
}