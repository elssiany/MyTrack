package com.app.kevin.mytrack.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.app.kevin.mytrack.R;
import com.app.kevin.mytrack.Fragments.FragmentListSongs;


public class MainActivity extends ActionBarActivity implements FragmentListSongs.OnFragmentInteractionListener{

    FragmentListSongs fragmentListSongs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


  if(savedInstanceState==null) {
    fragmentListSongs = new FragmentListSongs();
    FragmentManager fragmentManager = getFragmentManager();
    fragmentManager.beginTransaction().add(R.id.listSongContainer, fragmentListSongs).commit();
  }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {

        super.onResume();
        fragmentListSongs = new FragmentListSongs();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.listSongContainer,fragmentListSongs).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.insert_song) {
            Intent intent =new Intent(this,ManageSongActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
