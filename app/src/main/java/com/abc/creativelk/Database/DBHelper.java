package com.abc.creativelk.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Creativelk.db";

    public DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ARTIST_ENTRIES , SQL_CREATE_PHOTO_DETAILS_ENTRIES;

        SQL_CREATE_ARTIST_ENTRIES = "CREATE TABLE " + ArtistMaster.ArtistDetails.TABLE_NAME + " (" +
                ArtistMaster.ArtistDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME + " TEXT ) " ;

        SQL_CREATE_PHOTO_DETAILS_ENTRIES = "CREATE TABLE " + ArtistMaster.PhotographDetails.TABLE_NAME + " (" +
                ArtistMaster.PhotographDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_ARTIST_ID + " INTEGER , " +
                ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_NAME + " TEXT , " +
                ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_CATEGORY + " TEXT , " +
                ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_IMAGE + " BLOB , " +
                " FOREIGN KEY("+ ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_ARTIST_ID +") REFERENCES " +
                ArtistMaster.ArtistDetails.TABLE_NAME + " ( " + ArtistMaster.PhotographDetails._ID+"))";


        db.execSQL(SQL_CREATE_ARTIST_ENTRIES);

        db.execSQL(SQL_CREATE_PHOTO_DETAILS_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addArtist(String ArtistName){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME,ArtistName);
        long result = db.insert(ArtistMaster.ArtistDetails.TABLE_NAME, null, contentValues);

        if(result == -1)
           return false;
        else
            return true;


    }

    public boolean addPhotos(String ArtistName,String PhotoName,String PhotoCategory,byte[] image){

        long result = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db_w = this.getWritableDatabase();

        String[] projection = {ArtistMaster.ArtistDetails._ID, ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME};
        String selection = ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME + " = ?";
        String[] selectionArgs = {ArtistName};
       // String sortOrder = ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME + " DESC";

        Cursor cursor = db.query(
                ArtistMaster.ArtistDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);

        if(cursor.getCount() > 0){

            while(cursor.moveToNext()){

                ContentValues contentValues1 = new ContentValues();

                String ArtistID = cursor.getString(0);

                contentValues1.put(ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_NAME,PhotoName);
                contentValues1.put(ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_CATEGORY,PhotoCategory);
                contentValues1.put(ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_IMAGE,image);
                contentValues1.put(ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_ARTIST_ID,ArtistID);


                result = db_w.insert(ArtistMaster.PhotographDetails.TABLE_NAME, null, contentValues1);

            }
        }

        if(result == -1)
            return false;
        else
            return true;


    }



    public boolean deleteDetails(String TableName,String ColumnName,String name){

        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = { name };
        long deletedRows = db.delete(TableName,ColumnName+ " = ? ", selectionArgs);

        if(deletedRows == -1)
            return false;
        else
            return true;

    }

    public Cursor loadArtist(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + ArtistMaster.ArtistDetails.TABLE_NAME,null);
        return cursor;

    }

    public Cursor loadPhotos(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + ArtistMaster.PhotographDetails.TABLE_NAME,null);
        return cursor;

    }

    public Cursor searchPhotoGraph(String ArtistName){


       // SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();

      /* String[] projection = {ArtistMaster.ArtistDetails._ID};
        String selection = ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME + " = ?";
        String[] selectionArgs = {ArtistName};


        Cursor cursor = db.query(
                ArtistMaster.ArtistDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);




                //String ArtistID = cursor.getString(0);

                String[] projection_photo = {" * "};
                String selection_photo = ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_ARTIST_ID + " = ?";
                String[] selectionArgs_photo = {cursor.getString(0)};



        Cursor cursor_photo = db.query(
                       ArtistMaster.PhotographDetails.TABLE_NAME,   // The table to query
                       projection_photo,             // The array of columns to return (pass null to get all)
                       selection_photo,              // The columns for the WHERE clause
                       selectionArgs_photo,          // The values for the WHERE clause
                       null,                   // don't group the rows
                       null,                   // don't filter by row groups
                       null);


            return cursor_photo;

*/

       Cursor result = db.rawQuery(" SELECT * FROM "+ ArtistMaster.PhotographDetails.TABLE_NAME,null);
       return result;



    }



}
