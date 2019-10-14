package com.abc.creativelk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnActivityAddArtist,btnActivityAddPhoto,btnActivityDeleteArPhoto,btnViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivityAddArtist = (Button) findViewById(R.id.btnAddArtist);
        btnActivityAddPhoto = (Button) findViewById(R.id.btnAddPhoto);
        btnActivityDeleteArPhoto = (Button) findViewById(R.id.btnDeleteArPhoto);
        btnViewPhoto = (Button) findViewById(R.id.btnViewPhotos);

        btnActivityAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddArtist.class);
                startActivity(intent);
            }
        });


        btnActivityAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddPhotoGraph.class);
                startActivity(intent);
            }
        });

        btnActivityDeleteArPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RemovePhotoArtist.class);
                startActivity(intent);
            }
        });

        btnViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewPhotos.class);
                startActivity(intent);
            }
        });
    }
}
