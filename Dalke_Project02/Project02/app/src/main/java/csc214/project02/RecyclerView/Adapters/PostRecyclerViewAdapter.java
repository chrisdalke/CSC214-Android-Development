////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.project02.RecyclerView.Adapters;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import csc214.project02.Database.Collections.PostDatabaseCollection;
import csc214.project02.Database.Collections.UserDatabaseCollection;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.Post;
import csc214.project02.Model.User;
import csc214.project02.PostCreateActivity;
import csc214.project02.R;
import csc214.project02.Activities.Account.AccountViewActivity;

////////////////////////////////////////
// User Recycler ViewAdapter
////////////////////////////////////////

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.PostHolder> {

    public enum POST_FILTER_TYPE {
        Feed, Profile
    }

    private POST_FILTER_TYPE filter;
    private Fragment parent;
    private User mUser;


    private PostDatabaseCollection mPostCollection;

    public PostRecyclerViewAdapter(Fragment parent, Context context, POST_FILTER_TYPE filter, User user) {
        this.parent = parent;
        mPostCollection = new PostDatabaseCollection(context);
        this.filter = filter;
        mUser = user;
    }

    public PostHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_post, parent, false);

        PostHolder holder = new PostHolder(view);
        return holder;
    }

    public int getItemCount() {
        return getCollection().size();
    }

    public List<Post> getCollection(){
        switch (filter){
            case Feed:{
                return mPostCollection.getPostsForUserFeed(mUser);
            }
            case Profile:{
                return mPostCollection.getPostsFromUser(mUser);
            }
            default:{
                return mPostCollection.getPostsForUserFeed(mUser);
            }
        }
    }

    public void onBindViewHolder(PostHolder holder, int position) {
        Post post = getCollection().get(position);
        holder.bindPost(post);
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private Post mPost;
        private View mView;

        private TextView mPostContents;
        private Button mPostUsernameButton;
        private TextView mPostTimestamp;
        private ImageView mPostImage;

        public PostHolder(View view) {
            super(view);
            mView = view;

            mPostContents = (TextView)view.findViewById(R.id.view_post_contents);
            mPostUsernameButton = (Button)view.findViewById(R.id.view_post_username_button);
            mPostTimestamp = (TextView)view.findViewById(R.id.view_post_timestamp);
            mPostImage = (ImageView)view.findViewById(R.id.post_image_view);
        }

        public void bindPost(Post post) {
            mPost = post;
            mPostContents.setText(mPost.getPostContents());
            if (mPost.getPostUser().equals(ApplicationState.getInstance().getUser().getUsername())) {
                mPostUsernameButton.setText(mPost.getPostUser() + " (You)");
            } else {
                mPostUsernameButton.setText(mPost.getPostUser());
            }
            mPostTimestamp.setText(mPost.getPostDate());
            mPostUsernameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDatabaseCollection userDb = new UserDatabaseCollection(parent.getContext());
                    parent.getActivity().startActivity(AccountViewActivity.buildIntent(parent.getContext(),userDb.getUserByUsername(mPost.getPostUser())));
                }
            });

            if (mPost.getPostImage().equals("true")){
                Bitmap photo = PostCreateActivity.getScaledBitmap(mPost.getPostImageFilename(), 256,256);
                mPostImage.setImageBitmap(photo);
                mPostImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                mPostImage.setImageDrawable(null);
            }

        }
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////