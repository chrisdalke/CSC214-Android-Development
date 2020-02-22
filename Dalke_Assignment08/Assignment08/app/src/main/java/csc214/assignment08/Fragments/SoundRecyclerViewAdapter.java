////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 8
////////////////////////////////////////

package csc214.assignment08.Fragments;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import csc214.assignment08.Model.Sound;
import csc214.assignment08.Model.SoundCollection;
import csc214.assignment08.R;

////////////////////////////////////////
// Sound RecyclerView Adapter
////////////////////////////////////////

public class SoundRecyclerViewAdapter extends RecyclerView.Adapter<SoundRecyclerViewAdapter.SoundHolder> {

    private SoundMasterFragment parent;
    public SoundRecyclerViewAdapter(SoundMasterFragment parent){
        this.parent = parent;
    }
    
    public SoundHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_sound, parent, false);
    
        SoundHolder holder = new SoundHolder(view);
        return holder;
    }
    
    public int getItemCount() {
        return SoundCollection.numSounds();
    }
    
    public void onBindViewHolder(SoundHolder holder, int position) {
        Sound sound = SoundCollection.getSoundList().get(position);
        holder.bindSound(sound, position);
    }
    
    public class SoundHolder extends RecyclerView.ViewHolder {
    
        private TextView mSoundNameText;
        private Button mSoundPlayButton;
        private Sound mSound;
        private int mPosition;
        private View mView;

    
        public SoundHolder(View view) {
            super(view);
            mView = view;

            mSoundNameText = (TextView)view.findViewById(R.id.view_sound_name);
            mSoundPlayButton = (Button)view.findViewById(R.id.view_sound_button);
        }
    
        public void bindSound(Sound sound, int id) {
            mPosition = id;
            mSound = sound;
            mSoundNameText.setText(mSound.getName().split("\\.")[0]);

            mSoundPlayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Play the sound
                    parent.clickSound(mPosition);
                }
            });

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Play the sound
                    parent.clickSound(mPosition);
                }
            });
        }
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////