////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Database;

////////////////////////////////////////
// Database Schema
////////////////////////////////////////

public class TwiddlerDbSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "twiddler_database.db";

    public static final class UserTable {
        public static final String NAME = "users";

        public static final class Cols {
            public static final String EMAIL = "email";
            public static final String HASHED_PASSWORD = "hashedPassword";
            public static final String USERNAME =  "username";
            public static final String FULL_NAME =  "fullName";
            public static final String HOME_LOCATION =  "homeLocation";
            public static final String BIO =  "bio";
            public static final String PHOTO_FILE =  "photoFile";
        }
    }

    public static final class PostTable {
        public static final String NAME = "posts";

        public static final class Cols {
            public static final String POST_ID =  "id";
            public static final String POST_USER =  "postUser";
            public static final String POST_DATE = "postDate";
            public static final String POST_CONTENTS = "postContents";
            public static final String POST_IMAGE = "postImage";
            public static final String POST_IMAGE_FILENAME = "postImageFilename";
        }
    }

    public static final class FavoriteTable {
        public static final String NAME = "favorites";

        public static final class Cols {
            public static final String FOLLOWING_USER = "followingUser";
            public static final String FOLLOWER_USER = "followerUser";
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////