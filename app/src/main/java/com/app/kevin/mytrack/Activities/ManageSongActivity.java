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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.kevin.mytrack.DAO.TrackerDAO;
import com.app.kevin.mytrack.Model.Tracks;
import com.app.kevin.mytrack.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ManageSongActivity extends ActionBarActivity {


    static private final int ATTACH_IMAGE=200;
    boolean editMode;
    EditText nameSong;
    EditText nameArtist;
    EditText nameGender;
    EditText nameAlbum;
    ImageView imageSongAdd,imageSong;
    Tracks song;
    int songId;
    Button button;
    byte imageInByte[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_song);

        //casteo los EditText y el Button
        nameSong= (EditText) findViewById(R.id.nameSong);
        nameArtist= (EditText) findViewById(R.id.nameArtist);
        nameAlbum= (EditText) findViewById(R.id.nameAlbum);
        nameGender= (EditText) findViewById(R.id.nameGender);
        imageSongAdd=(ImageView)findViewById(R.id.imageViewEdit);
        imageSong=(ImageView)findViewById(R.id.imageView3);
        button= (Button) findViewById(R.id.button);
        song=new Tracks();

        try{

            //rescato los puts extras que me envien desde otra activitidad
            songId=getIntent().getExtras().getInt("id");
            editMode=true;
            //extraigo la cancion que se quiere modificar de la base de datos  con el metodo
            //  <TrackerDAO.getSong(this,songId)>
            song=TrackerDAO.getSong(this,songId);
            //le seteo los datos q tiene el objeto <song> a los editTexts
            nameSong.setText(song.getName());
            nameArtist.setText(song.getArtist());
            nameAlbum.setText(song.getAlbum());
            nameGender.setText(song.getGender());
            imageSong.setImageBitmap(convertImage(song.getImage()));
            //al buton le seteo un texto
            button.setText("Modificar");

        }catch (NullPointerException e){
            editMode=false;
        }


        //le seteo el evento click al ImagenView  <imageSong>
        imageSongAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //lanzo la intencion pasandole al metodo <startActivityForResult>dos
                //parametros uno con la intencion <intent> y el otro <ATTACH_IMAGE>
                // con el identificacion de esta
                //intencion
                startActivityForResult(intent,ATTACH_IMAGE);
            }
        });

    }



    public void saveSong(View v) {

        //extraigo los datos que tienen los editText y se los seteo al objeto <song>
        song.setName(nameSong.getText().toString());
        song.setArtist(nameArtist.getText().toString());
        song.setAlbum(nameAlbum.getText().toString());
        song.setGender(nameGender.getText().toString());
        song.setImage(imageInByte);

        /*
        * aqui pregunto que si la variable <editMode> es <true> entonces comenzare el
        * proceso de modificacion(update) de esa cancion en la base de datos.
        *
        * si la variable <editMode> es <false> entonces procedere a guardar la cancion en
        * la base de datos
        * */
        if(editMode){

            //modifico la cancion en la base de datos
            if (TrackerDAO.updateSong(this,songId,song)) {
                Toast.makeText(this, "Cancion Modificada exitosamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Error al modificar la cancion", Toast.LENGTH_LONG).show();
            }

        }else {

            //guardo la cancion en la base de datos
            if(TrackerDAO.createSong(this,song)) {
                Toast.makeText(this, "Guardado exitoso", Toast.LENGTH_LONG).show();
                finish();
            }else{
                //Toast.makeText(this, "Error al guardar", Toast.LENGTH_LONG).show();
                finish();
            }

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ATTACH_IMAGE:
                if (resultCode==RESULT_OK){
                    InputStream stream = null;
                    try {
                        stream=getContentResolver().openInputStream(data.getData());
                        Bitmap bitmap= BitmapFactory.decodeStream(stream);
                        //le seteo una imagen q escogi de la galeria de imagenes
                        ((ImageView) findViewById(R.id.imageView3)).setImageBitmap(bitmap);

                        /*convierto el bitmap a byte
                        * Con <ByteArrayOutputStream> Creo un nuevo flujo de salida de
                         * matriz de bytes,con una capacidad de amortiguamiento
                        * */
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        /*
                        * */
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        imageInByte = byteArrayOutputStream.toByteArray();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }finally {
                        if(stream!=null ) {
                            try {
                                //cierro el flujo para que no haiga desbordamiento
                                stream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        }
    }


    /*este estatico metodo me convierte un code <byte> a
     <Bitmap> pasandole por parametro el byte a convertir
    * */
    public static Bitmap convertImage(byte[] imageByte){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByte);
        Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
        if(imageStream!=null) {
            try {
                //cierro el flujo para que no haiga desbordamiento
                imageStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageBitmap;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }
}
