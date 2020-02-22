package csc214.project02.Database.Wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import csc214.project02.Database.TwiddlerDbSchema;
import csc214.project02.Model.Favorite;
import csc214.project02.Model.Post;

public class PostCursorWrapper extends CursorWrapper {
    public PostCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Post getPost() {

        String id = getString(getColumnIndex(TwiddlerDbSchema.PostTable.Cols.POST_ID));
        String postUser = getString(getColumnIndex(TwiddlerDbSchema.PostTable.Cols.POST_USER));
        String postDate = getString(getColumnIndex(TwiddlerDbSchema.PostTable.Cols.POST_DATE));
        String postContents = getString(getColumnIndex(TwiddlerDbSchema.PostTable.Cols.POST_CONTENTS));
        String postImage = getString(getColumnIndex(TwiddlerDbSchema.PostTable.Cols.POST_IMAGE));
        String postImageFilename = getString(getColumnIndex(TwiddlerDbSchema.PostTable.Cols.POST_IMAGE_FILENAME));

        Post returnPost = new Post();
        returnPost.setId(id);
        returnPost.setPostUser(postUser);
        returnPost.setPostDate(postDate);
        returnPost.setPostContents(postContents);
        returnPost.setPostImage(postImage);
        returnPost.setPostImageFilename(postImageFilename);

        return returnPost;
    }
}

