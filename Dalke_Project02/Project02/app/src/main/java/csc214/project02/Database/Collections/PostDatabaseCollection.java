package csc214.project02.Database.Collections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import csc214.project02.Database.TwiddlerDbHelper;
import csc214.project02.Database.TwiddlerDbSchema;
import csc214.project02.Database.Wrappers.FavoriteCursorWrapper;
import csc214.project02.Database.Wrappers.PostCursorWrapper;
import csc214.project02.Database.Wrappers.UserCursorWrapper;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.Post;
import csc214.project02.Model.User;

public class PostDatabaseCollection {

    private final Context mContext;
    private final SQLiteDatabase mDatabase;

    public PostDatabaseCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TwiddlerDbHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Post post) {
        ContentValues values = new ContentValues();

        values.put(TwiddlerDbSchema.PostTable.Cols.POST_CONTENTS, post.getPostContents());
        values.put(TwiddlerDbSchema.PostTable.Cols.POST_DATE, post.getPostDate());
        values.put(TwiddlerDbSchema.PostTable.Cols.POST_IMAGE, post.getPostImage());
        values.put(TwiddlerDbSchema.PostTable.Cols.POST_IMAGE_FILENAME, post.getPostImageFilename());
        values.put(TwiddlerDbSchema.PostTable.Cols.POST_USER, post.getPostUser());
        values.put(TwiddlerDbSchema.PostTable.Cols.POST_ID, post.getId());
        return values;
    }

    public void addPost(Post instance){
        instance.setId(Integer.toString((getNumPosts() + 1)));
        mDatabase.insert(TwiddlerDbSchema.PostTable.NAME,null,getContentValues(instance));
    }

    public void updatePost(Post instance){
        mDatabase.update(TwiddlerDbSchema.PostTable.NAME,getContentValues(instance),"id=?",new String[]{instance.getId()});
    }

    public void removePost(Post instance){
        mDatabase.delete(TwiddlerDbSchema.PostTable.NAME,"id=?",new String[]{instance.getId()});
    }

    public int getNumPosts(){
        Cursor cursor= mDatabase.rawQuery("SELECT COUNT (*) FROM " + TwiddlerDbSchema.PostTable.NAME,null);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        cursor.close();
        return count;
    }

    public void removeAll(){
        mDatabase.delete(TwiddlerDbSchema.PostTable.NAME,null,null);
    }

    public ArrayList<Post> getPostsForUserFeed(User feedUser){
        ArrayList<Post> posts = new ArrayList<>();
        for (User user : (new FavoriteDatabaseCollection(mContext)).getFeedUsers(feedUser)){
            posts.addAll(getPostsFromUser(user));
        }

        //Order posts by date
        sortPosts(posts);

        return posts;
    }

    public ArrayList<Post> getPostsFromUser(User user){
        ArrayList<Post> posts = new ArrayList<>();

        Cursor cursor = mDatabase.query(TwiddlerDbSchema.PostTable.NAME,  null, "postUser=?", new String[]{user.getUsername()}, null,null,null);
        PostCursorWrapper wrapper = new PostCursorWrapper(cursor);
        PostDatabaseCollection postDatabaseCollection = new PostDatabaseCollection(mContext);

        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                posts.add(wrapper.getPost());
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }

        sortPosts(posts);

        return posts;
    }

    public void sortPosts(ArrayList<Post> listToSort){

        Collections.sort(listToSort, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                try {
                    return -Integer.compare(Integer.parseInt(o1.getId()),Integer.parseInt(o2.getId()));
                } catch (Exception e){
                    e.printStackTrace();
                    return 0;
                }
            }
        });

    }
}
