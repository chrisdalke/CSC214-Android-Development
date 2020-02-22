package csc214.assignment06.Model;


public class Game {

    private String mName;
    private String mPublisherName;
    private String mGenre;
    private String mDescription;
    private int mReleaseYear;

    public Game(){

    }

    public Game(String name, String publisherName, String genre, String description, int releaseYear) {
        this.mName = name;
        this.mPublisherName = publisherName;
        this.mGenre = genre;
        this.mDescription = description;
        this.mReleaseYear = releaseYear;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPublisherName() {
        return mPublisherName;
    }

    public void setPublisherName(String publisherName) {
        this.mPublisherName = publisherName;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public int getReleaseYear() {
        return mReleaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.mReleaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return mName + " (" + mReleaseYear + ") - "+ mPublisherName;
    }
}
