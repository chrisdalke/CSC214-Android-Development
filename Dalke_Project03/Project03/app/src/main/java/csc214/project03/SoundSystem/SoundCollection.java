////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.SoundSystem;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import java.util.ArrayList;

////////////////////////////////////////
// Sound Collection Object
////////////////////////////////////////

public class SoundCollection {

    private static ArrayList<Sound> soundList;

    public static ArrayList<Sound> getSoundList(){
        if (soundList == null){
            initSoundList();
        }
        return soundList;
    }

    public static int numSounds(){
        return getSoundList().size();
    }

    private static void initSoundList(){
        soundList = new ArrayList<>();
    }

    public static void addSound(Sound sound){
        if (soundList == null){
            initSoundList();
        }
        soundList.add(sound);
    }

    public static void clear(){
        if (soundList == null){
            initSoundList();
        } else {
            soundList.clear();
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////

