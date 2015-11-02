package com.app.kevin.mytrack.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.kevin.mytrack.Activities.DetailActivity;
import com.app.kevin.mytrack.Activities.MainActivity;
import com.app.kevin.mytrack.Activities.ManageSongActivity;
import com.app.kevin.mytrack.DAO.TrackerDAO;
import com.app.kevin.mytrack.Model.Tracks;
import com.app.kevin.mytrack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 26/04/2015.
 */
public class Adapter extends ArrayAdapter<Tracks> {

    private  List<Tracks> songs = new ArrayList<>();

    public Adapter(Context context, int resource, List<Tracks> objects) {
        super(context, resource, objects);
        songs=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //return super.getView(position, convertView, parent);
        final Tracks song = songs.get(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_song,parent,false);
        }

        ((TextView)convertView.findViewById(R.id.nameSong)).setText(song.getName());
        ((TextView)convertView.findViewById(R.id.nameArtist)).setText(song.getArtist());
        ((TextView)convertView.findViewById(R.id.nameGender)).setText(song.getGender());
        ((TextView)convertView.findViewById(R.id.nameAlbum)).setText(song.getAlbum());
        //((ImageView)convertView.findViewById(R.id.imageView)).setImageResource(getContext()
                //.getResources().getIdentifier(song.getImage(), "drawable", getContext()
                        //.getApplicationInfo().packageName));

        ((ImageView)convertView.findViewById(R.id.imageView)).setImageBitmap(ManageSongActivity.convertImage(song.getImage()));

        ((Button)convertView.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TrackerDAO.deleteSong(getContext(), song.getId())) {
                    Toast.makeText(getContext(), "Eliminado Exitoso", Toast.LENGTH_LONG).show();
                    //    remove(song);
                    Intent i = new Intent(getContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    getContext().startActivity(i);
                } else {
                    Toast.makeText(getContext(), "No Se Pudo Eliminar", Toast.LENGTH_LONG).show();
                }

            }
        });
   convertView.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

      Intent intent = new Intent(getContext(), DetailActivity.class);
      intent.putExtra("id",song.getId());
      getContext().startActivity(intent);

       }
   });

       return convertView;



    }

}
