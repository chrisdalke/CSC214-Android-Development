package csc214.project02.Model;

import csc214.project02.Database.Collections.FavoriteDatabaseCollection;
import csc214.project02.Database.Collections.UserDatabaseCollection;

public class ApplicationState {

    private static ApplicationState instance = null;
    protected ApplicationState() {
        // Exists only to defeat instantiation.
    }
    public static ApplicationState getInstance() {
        if(instance == null) {
            instance = new ApplicationState();
        }
        return instance;
    }

    private boolean loggedIn;
    private User user;

    public void logout(){
        loggedIn = false;
        user = null;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
