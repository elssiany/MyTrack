package com.app.kevin.mytrack.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.kevin.mytrack.Model.Tracks;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by  camila on 23/04/2015.
 */
public class TrackerDAO {

    public final static String TABLE_NAME = "songs";
    public final static String ID = "id" ;
    public final static String NAME = "name";
    public final static String ARTIST = "artist";
    public final static String GENDER = "gender";
    public final static String ALBUM = "album";
    public final static String IMAGE = "image";


    public static List<Tracks> getTrackList(Context context){
        SQLiteDatabase db = new TracksSqlHelper(context).getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        ArrayList<Tracks> songs = new ArrayList<>();
        while(c.moveToNext()){
            Tracks song = new Tracks();
            song.setId(c.getInt(c.getColumnIndex(ID)));
            song.setName(c.getString(c.getColumnIndex(NAME)));
            song.setArtist(c.getString(c.getColumnIndex(ARTIST)));
            song.setGender(c.getString(c.getColumnIndex(GENDER)));
            song.setAlbum(c.getString(c.getColumnIndex(ALBUM)));
            song.setImage(c.getBlob(c.getColumnIndex(IMAGE)));
            songs.add(song);
        }
        return songs;
    }


    public static List<Tracks> getTrackListQuery(Context context){
        SQLiteDatabase db = new TracksSqlHelper(context).getWritableDatabase();
        //Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        Cursor c = db.rawQuery("SELECT * FROM songs ORDER BY id DESC", null);
        ArrayList<Tracks> songs = new ArrayList<>();
        while(c.moveToNext()){
            Tracks song = new Tracks();
            song.setId(c.getInt(c.getColumnIndex(ID)));
            song.setName(c.getString(c.getColumnIndex(NAME)));
            song.setArtist(c.getString(c.getColumnIndex(ARTIST)));
            song.setGender(c.getString(c.getColumnIndex(GENDER)));
            song.setAlbum(c.getString(c.getColumnIndex(ALBUM)));
            song.setImage(c.getBlob(c.getColumnIndex(IMAGE)));
            songs.add(song);

        }
        return songs;
    }


    public  static boolean createSong(Context context,Tracks tracks){

        boolean response=false;
        SQLiteDatabase db =new TracksSqlHelper(context).getWritableDatabase();
        if (db!=null){
            ContentValues newItem= new ContentValues();
            newItem.put("ID",tracks.getId());
            newItem.put("NAME",tracks.getName());
            newItem.put("ARTIST",tracks.getArtist());
            newItem.put("GENDER",tracks.getGender());
            newItem.put("ALBUM",tracks.getAlbum());
            newItem.put("IMAGE",tracks.getImage());

            long inserted=db.insert(TABLE_NAME,null,newItem);
            if (inserted>0)
                response = true;

        }

        return  response;

    }


    public static  boolean deleteSong(Context context,int idSong){
        SQLiteDatabase db =new TracksSqlHelper(context).getWritableDatabase();
        int deleted=0;
        if (db!=null){

            String[] where=new String[]{String.valueOf(idSong)};
            deleted=db.delete(TABLE_NAME,"id = ?",where);
        }

       return deleted>0;
    }


    public static boolean updateSong(Context context,int idSong,Tracks song){

        int  updated=0;
        SQLiteDatabase db =new TracksSqlHelper(context).getWritableDatabase();
        if (db!=null){

            ContentValues newItem= new ContentValues();
            newItem.put("ID", song.getId());
            newItem.put("NAME", song.getName());
            newItem.put("ARTIST", song.getArtist());
            newItem.put("GENDER", song.getGender());
            newItem.put("ALBUM", song.getAlbum());
            newItem.put("IMAGE",song.getImage());
            //actualizo la base de datos con los nuevos datos  de esa cancion a modificar
            updated=db.update(TABLE_NAME,newItem,"id="+idSong,null);

        }

        return updated>0;
    }


    public static Tracks getSong(Context context,int idSong){

        Tracks song=new Tracks();

        //SQLiteDatabase---->>> sirve para exponer los metodos necesarios para la interaccion con
        // una base de datos (creacion, borrado, actualizacion).
        SQLiteDatabase db = new TracksSqlHelper(context).getWritableDatabase();
       //Cursor-->> representa el resultado de una consulta. Este resultado puede contener 0
       // o mas elementos.
        Cursor c = db.rawQuery("SELECT * FROM songs WHERE id="+idSong,null);

        if(c.moveToNext()){

            song.setId(c.getInt(c.getColumnIndex(ID)));
            song.setName(c.getString(c.getColumnIndex(NAME)));
            song.setArtist(c.getString(c.getColumnIndex(ARTIST)));
            song.setGender(c.getString(c.getColumnIndex(GENDER)));
            song.setAlbum(c.getString(c.getColumnIndex(ALBUM)));
            song.setImage(c.getBlob(c.getColumnIndex(IMAGE)));

        }

     return song;

    }


}
