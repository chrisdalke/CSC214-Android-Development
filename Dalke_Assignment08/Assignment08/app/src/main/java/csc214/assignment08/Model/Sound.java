////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 8
////////////////////////////////////////

package csc214.assignment08.Model;

////////////////////////////////////////
// Sound Model
////////////////////////////////////////

public class Sound {
    private final String mPath;
    private final String mName;
    private Integer mId;

    public Sound(String path, String name) {
        mPath = path;
        mName = name;
    }

    public String getPath() {
        return mPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////