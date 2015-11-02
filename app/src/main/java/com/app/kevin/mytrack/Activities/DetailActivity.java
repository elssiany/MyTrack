package com.app.kevin.mytrack.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.kevin.mytrack.DAO.TrackerDAO;
import com.app.kevin.mytrack.Model.Tracks;
import com.app.kevin.mytrack.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DetailActivity extends ActionBarActivity {



    int idSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //rescato los puts extras que me envien desde otra activitidad en este caso el id de la
       //cancion
        idSong=getIntent().getExtras().getInt("id");
        Tracks song =new Tracks();
        song= TrackerDAO.getSong(this,idSong);
        //le seteo los datos q tiene el objeto <song> a los TextViews
        ((TextView)findViewById(R.id.textViewSong)).setText(song.getName());
        ((TextView)findViewById(R.id.textViewArtist)).setText(song.getArtist());
        ((TextView)findViewById(R.id.textViewAlbum)).setText(song.getAlbum());
        ((TextView)findViewById(R.id.textViewGender)).setText(song.getGender());
        //le seteo una imagen q tengo el en recurso <drawable>
        ((ImageView) findViewById(R.id.imageView2)).setImageBitmap(ManageSongActivity.convertImage(song.getImage()));

    }




    public void editSong(View v){

        Intent intent = new Intent(this,ManageSongActivity.class);
        intent.putExtra("id",idSong);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
