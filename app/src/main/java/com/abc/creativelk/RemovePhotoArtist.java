package com.abc.creativelk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.creativelk.Database.ArtistMaster;
import com.abc.creativelk.Database.DBHelper;

import java.util.ArrayList;

public class RemovePhotoArtist extends AppCompatActivity {

    Button btnRemoveArtist,btnRemovePhoto;
    Spinner spArtists,spPhotos;

    String PhotoName,ArtistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_photo_artist);

        btnRemoveArtist = (Button)findViewById(R.id.btnRemoveArtist);
        btnRemovePhoto = (Button)findViewById(R.id.btnRemovePhoto);

        spArtists = (Spinner)findViewById(R.id.spArtistNameRemove);
        spPhotos = (Spinner)findViewById(R.id.spPhotoCategoryRemove);



        final DBHelper mydb = new DBHelper(RemovePhotoArtist.this);

        final ArrayList<String> PhotoList = new ArrayList<>();
        final ArrayList<String> ArtistsList = new ArrayList<>();


        Cursor getPhotoData = mydb.loadPhotos();
        Cursor getArtistsData = mydb.loadArtist();

        if(getPhotoData.getCount() > 0){
            while(getPhotoData.moveToNext()){
                PhotoList.add(getPhotoData.getString(2));
                ArrayAdapter<String> imgAdapterList = new ArrayAdapter<>(RemovePhotoArtist.this,R.layout.support_simple_spinner_dropdown_item,PhotoList);
                spPhotos.setAdapter(imgAdapterList);
            }

            PhotoName = spPhotos.getSelectedItem().toString();
        }

        if(getArtistsData.getCount() > 0){
            while(getArtistsData.moveToNext()){
                ArtistsList.add(getArtistsData.getString(1));
                ArrayAdapter<String> artistsAdapterList = new ArrayAdapter<>(RemovePhotoArtist.this,R.layout.support_simple_spinner_dropdown_item,ArtistsList);
                spArtists.setAdapter(artistsAdapterList);
            }

            ArtistName = spArtists.getSelectedItem().toString();
        }








        btnRemovePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = mydb.deleteDetails(ArtistMaster.PhotographDetails.TABLE_NAME,ArtistMaster.PhotographDetails.COLUMN_NAME_PHOTO_NAME,PhotoName);

                if(isDeleted == true)
                    Toast.makeText(RemovePhotoArtist.this,"Successfullt Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RemovePhotoArtist.this,"Fail to Delete!",Toast.LENGTH_SHORT).show();

            }
        });

        btnRemoveArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = mydb.deleteDetails(ArtistMaster.ArtistDetails.TABLE_NAME,ArtistMaster.ArtistDetails.COLUMN_NAME_ARTISTNAME,ArtistName);

                if(isDeleted == true)
                    Toast.makeText(RemovePhotoArtist.this,"Successfullt Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RemovePhotoArtist.this,"Fail to Delete!",Toast.LENGTH_SHORT).show();

            }
        });



    }
}
