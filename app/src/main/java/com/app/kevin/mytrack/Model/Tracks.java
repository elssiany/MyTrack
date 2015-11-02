package com.app.kevin.mytrack.Model;

/**
 * Created by kevin on 26/04/2015.
 */
public class Tracks {

    private int id;
    private String name;


    private byte[] image;
    private String artist;
    private String gender;
    private String album;

    public Tracks(){

    }

    public Tracks(int id, String name, String artist, String album, String gender,byte[] image) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.gender = gender;
        this.image = image;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
