package com.app.kevin.mytrack.DAO;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by s205e15 on 23/04/2015.
 */
public class TracksSqlHelper extends SQLiteOpenHelper {

    private final static int version = 1;
    private final static String dataBaseName = "trackerDB";

    public final static String createSongs = "CREATE TABLE songs" + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR(100)," +
            "artist VARCHAR(100)," +
            "gender VARCHAR(100)," +
            "album VARCHAR(100),"+
            "image BLOB"+ ")";

    public TracksSqlHelper(Context context) {
        super(context, dataBaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSongs);

/*

        db.execSQL("INSERT INTO songs ( name, artist, gender, album,image)"
                + "VALUES ('Eres Mi Vida','Kevin Roldan','Regueton','El Amor','image1')");
        db.execSQL("INSERT INTO songs (name, artist, gender, album,image)"
                + "VALUES ('Mi Princesa','Kevin ','Regueton','La Vida Es Un Ratico','image1')");

        db.execSQL("INSERT INTO songs (name, artist, gender, album,image)"
                + "VALUES ('Aprovechalo','Wisin y Yandel','Regueton','El Momento','image2')");
        db.execSQL("INSERT INTO songs (name, artist, gender, album,image)"
                + "VALUES ('Dimelo','Wisin y Yandel','Regueton','Los Vaqueros','image3')");
*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
