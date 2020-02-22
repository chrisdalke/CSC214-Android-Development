package csc214.project02.Model;

/**
 * Created by chrisdalke on 4/8/17.
 */

public class Favorite {
    public String followingUser; //who is being followed
    public String followerUser; //who is the follower

    public String getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(String followingUser) {
        this.followingUser = followingUser;
    }

    public String getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(String followerUser) {
        this.followerUser = followerUser;
    }
}
