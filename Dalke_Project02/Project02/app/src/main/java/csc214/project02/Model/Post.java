////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Model;

////////////////////////////////////////
// Post Model Class
////////////////////////////////////////

public class Post {
    public String id;
    public String postUser;
    public String postDate;
    public String postContents;
    public String postImage;
    public String postImageFilename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostImageFilename() {
        return postImageFilename;
    }

    public void setPostImageFilename(String postImageFilename) {
        this.postImageFilename = postImageFilename;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////