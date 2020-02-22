////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 8
////////////////////////////////////////

package csc214.assignment08;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import csc214.assignment08.Fragments.SoundDetailFragment;
import csc214.assignment08.Fragments.SoundMasterFragment;
import csc214.assignment08.Model.Sound;
import csc214.assignment08.Model.SoundCollection;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


////////////////////////////////////////
// Account Creation Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity implements SoundMasterFragment.DetailItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        //Add the master fragment to the left of the view
        SoundMasterFragment fragment = (SoundMasterFragment)manager.findFragmentById(R.id.left_panel);
        if(fragment == null) {
            fragment = new SoundMasterFragment();
            manager.beginTransaction()
                    .add(R.id.left_panel, fragment)
                    .commit();
        }
    }

    @Override
    public void OnDetailItemClick(int itemNumber) {
        //Find sound that was clicked
        Sound pickedSound = SoundCollection.getSoundList().get(itemNumber);

        //Check if the layout is vertical or horizontal
        if(findViewById(R.id.right_panel) == null) {
            //Vertical, display toast
            Toast.makeText(getBaseContext(),"Played sound "+pickedSound.getName(),Toast.LENGTH_SHORT).show();
        } else {
            //Horizontal, update sound detail fragment
            SoundDetailFragment fragment = SoundDetailFragment.newInstance(pickedSound);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.right_panel, fragment)
                    .commit();
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
