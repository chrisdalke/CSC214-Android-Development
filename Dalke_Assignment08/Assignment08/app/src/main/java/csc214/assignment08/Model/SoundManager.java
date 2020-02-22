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
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import java.io.IOException;

////////////////////////////////////////
// Sound Manager System
////////////////////////////////////////

public class SoundManager {
    private static final String SOUNDS_FOLDER = "sounds";

    private AssetManager mAssets;
    private SoundPool mSoundPool;

    public SoundManager(Context context) {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        SoundCollection.clear();

        try {
            String[] soundNames = mAssets.list(SOUNDS_FOLDER);
            for(String filename : soundNames) {
                String path = SOUNDS_FOLDER + "/" + filename;
                if (filename.endsWith(".m4a")) {
                    Sound sound = new Sound(path, filename);
                    SoundCollection.addSound(sound);
                    try {
                        AssetFileDescriptor afd = mAssets.openFd(sound.getPath());
                        int soundId = mSoundPool.load(afd, 1);
                        sound.setId(soundId);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        //Could not load sound files.
                    }
                }
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
            //Could not load sound files.
        }
    }

    public void play(Sound sound) {
        Integer id = sound.getId();
        if(id != null) {
            mSoundPool.play(
                    id,   // sound id
                    1.0f, // left volume
                    1.0f, // right volume
                    1,    // priority (ignored)
                    0,    // loop counter, 0 for no loop
                    1.0f  // playback rate
            );
        }
    }

    public void release() {
        mSoundPool.release();
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////