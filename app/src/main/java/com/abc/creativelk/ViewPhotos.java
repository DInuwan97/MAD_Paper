package com.abc.creativelk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.creativelk.Adapters.ImageAdapter;
import com.abc.creativelk.Database.DBHelper;

import java.util.ArrayList;

public class ViewPhotos extends AppCompatActivity {

    Spinner spArtistName;

    Button btnSearch;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);

        DBHelper mydb = new DBHelper(ViewPhotos.this);

        spArtistName = (Spinner) findViewById(R.id.spArtistNameSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        gridView = (GridView) findViewById(R.id.gridView);

        final ArrayList<String> ArtistsList = new ArrayList<>();
        Cursor data = mydb.loadArtist();

        if(data.getCount() == 0)
            Toast.makeText(ViewPhotos.this,"User are Empty",Toast.LENGTH_SHORT).show();
        else{
            while (data.moveToNext()){

                ArtistsList.add(data.getString(1));

                ArrayAdapter artistAdapter = new ArrayAdapter(ViewPhotos.this,R.layout.support_simple_spinner_dropdown_item,ArtistsList);
                spArtistName.setAdapter(artistAdapter);
            }
        }


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper mydb = new DBHelper(ViewPhotos.this);
                final ArrayList imageList = new ArrayList<>();

                Cursor data =  mydb.searchPhotoGraph(spArtistName.getSelectedItem().toString());

                if(data.getCount() == 0)
                    Toast.makeText(ViewPhotos.this,"Photos are Empty",Toast.LENGTH_SHORT).show();
                else{
                    while(data.moveToNext()){
                        imageList.add(data.getBlob(4));
                        ImageAdapter imgAdapter = new ImageAdapter(ViewPhotos.this,R.layout.gridviewimages,imageList);
                        gridView.setAdapter(imgAdapter);

                    }
                }


            }
        });
    }
}
