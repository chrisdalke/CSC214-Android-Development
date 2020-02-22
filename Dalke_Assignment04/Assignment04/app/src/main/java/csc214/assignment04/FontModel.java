////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 4
////////////////////////////////////////

package csc214.assignment04;

////////////////////////////////////////
// Font Model
// Stores all state for this application
////////////////////////////////////////

public class FontModel {

    //Singleton Initialization code

    private static FontModel INSTANCE = null;
    public static FontModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FontModel();
        }
        return INSTANCE;
    }

    //Member variables

    public String mText;
    public int mTextColor;
    public int mTextSize;
    public boolean mIsBold;
    public boolean mIsItalic;
    public boolean mIsUnderline;

    //Default Settings
    public FontModel(){
        mText = "";
        mTextColor = 0;
        mTextSize = 16;
        mIsBold = false;
        mIsItalic = false;
        mIsUnderline = false;
    }

    //Getters and Setters

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public boolean isBold() {
        return mIsBold;
    }

    public void setBold(boolean mIsBold) {
        this.mIsBold = mIsBold;
    }

    public boolean isItalic() {
        return mIsItalic;
    }

    public void setItalic(boolean mIsItalic) {
        this.mIsItalic = mIsItalic;
    }

    public boolean isUnderline() {
        return mIsUnderline;
    }

    public void setUnderline(boolean mIsUnderline) {
        this.mIsUnderline = mIsUnderline;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////