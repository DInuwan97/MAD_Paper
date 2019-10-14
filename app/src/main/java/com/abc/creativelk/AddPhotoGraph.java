package com.abc.creativelk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.creativelk.Database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddPhotoGraph extends AppCompatActivity {

    Button btnAddImage,btnSave;
    EditText txtPhotoName;

    Spinner spArtistName,spPhotoCategoty;

    ImageView imageView;


    private static final int CAMERA_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_graph);

        DBHelper mydb = new DBHelper(AddPhotoGraph.this);

        spPhotoCategoty = (Spinner) findViewById(R.id.spPhotoCategory);
        spArtistName = (Spinner) findViewById(R.id.spArtistName);
        txtPhotoName = (EditText)findViewById(R.id.etPhotoName);

        btnSave = (Button) findViewById(R.id.btnSend);
        btnAddImage = (Button) findViewById(R.id.btnAddImage);

        imageView = (ImageView) findViewById(R.id.imageView);


        //upload image into imageview
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });


        String[] listOfPhotoCategory = {"Landscape" , "WildLife" ,"Potrait","Wedding","Fashion","Black and White"};
        ArrayAdapter<String> photoCategoryArrayAdapter = new ArrayAdapter<>(AddPhotoGraph.this,R.layout.support_simple_spinner_dropdown_item,listOfPhotoCategory);
        spPhotoCategoty.setAdapter(photoCategoryArrayAdapter);


        final ArrayList<String> ArtistsList = new ArrayList<>();
        Cursor data = mydb.loadArtist();

        if(data.getCount() == 0)
            Toast.makeText(AddPhotoGraph.this,"User are Empty",Toast.LENGTH_SHORT).show();
        else{
            while (data.moveToNext()){

                ArtistsList.add(data.getString(1));

                ArrayAdapter artistAdapter = new ArrayAdapter(AddPhotoGraph.this,R.layout.support_simple_spinner_dropdown_item,ArtistsList);
                spArtistName.setAdapter(artistAdapter);
            }
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //initialize image into null variable
                byte[] image = null;


                //initialize image into actual variable
                Bitmap bitmapPhoto = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmapPhoto.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                image = outputStream.toByteArray();

                if(image == null)
                    Toast.makeText(AddPhotoGraph.this,"Image can not be null",Toast.LENGTH_SHORT).show();
                else{

                    DBHelper mydb = new DBHelper(AddPhotoGraph.this);

                    boolean isInserted = mydb.addPhotos(spArtistName.getSelectedItem().toString(),
                                                        txtPhotoName.getText().toString(),
                                                        spPhotoCategoty.getSelectedItem().toString(),
                                                        image);

                    if (isInserted == true)
                        Toast.makeText(AddPhotoGraph.this,"Successfully Added",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AddPhotoGraph.this,"Fail to insert",Toast.LENGTH_SHORT).show();


                }

            }
        });


    }


   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
