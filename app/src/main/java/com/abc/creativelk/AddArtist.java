package com.abc.creativelk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.creativelk.Database.DBHelper;

public class AddArtist extends AppCompatActivity {

    Button btnAdd;
    EditText txtArtistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        txtArtistName = (EditText) findViewById(R.id.etArtistNameAddArtist);
        btnAdd = (Button)  findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper mydb = new DBHelper(AddArtist.this);

                boolean isInserted = mydb.addArtist(txtArtistName.getText().toString());
                if(isInserted == true)
                    Toast.makeText(AddArtist.this,"Added!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddArtist.this,"Fail!",Toast.LENGTH_LONG).show();

            }
        });
    }
}
