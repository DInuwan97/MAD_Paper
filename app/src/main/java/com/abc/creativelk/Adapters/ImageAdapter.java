package com.abc.creativelk.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.abc.creativelk.R;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter {

    public LayoutInflater layoutInflater;

    public ImageAdapter(Context context, int resource, ArrayList<byte[]> images) {
        super(context, R.layout.gridviewimages,images);
        this.layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        byte[] image = (byte[]) getItem(position);

        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.gridviewimages,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewGridView);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));

        return convertView;


        }



    }




