package model.user;


public class CurrentUser {
    private static CurrentUser currUser;

    public Long id;

    public CurrentUser() {

    }

    public static CurrentUser getInstance()
    {
        if (currUser == null)
            currUser = new CurrentUser();
        return currUser;
    }


}
