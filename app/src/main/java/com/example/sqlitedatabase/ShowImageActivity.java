package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;

public class ShowImageActivity extends AppCompatActivity {
    RecyclerView rvImage;
    Adapter adapter;
    DatabaseHelper helper;
    byte[] imagebyte;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        rvImage = findViewById(R.id.rvImage);
        helper = new DatabaseHelper(this);

        helper.getImages();
        Cursor cursor = helper.getAllIMages();

        cursor.moveToFirst();
        ArrayList<ModelClass> imagelist = new ArrayList<>();
//        for (int i = 0; i < cursor.getCount(); i++) {
//
//            //iterate over the columns
//            for (int j = 0; j < cursor.getColumnNames().length; j++) {
//                if (cursor.getColumnNames().equals("user_image")){
//                  imagebyte = cursor.getBlob(j);
//                }else{
//                     name=cursor.getString(j);
//                }
//
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);
//                imagelist.add(new ModelClass(name,bitmap));
//                adapter = new Adapter(imagelist);
//                rvImage.setAdapter(adapter);
//                //append the column value to the string builder and delimit by a pipe symbol
////                stringBuilder.append(cursor.getString(j) + "|");
//            }
//            //add a new line carriage return
////            stringBuilder.append("\n");
//
//            //move to the next row
//            cursor.moveToNext();
//        }
        while(cursor.moveToNext()){
            Log.d("chekck","yes");
        }
//        Toast.makeText(this, String.valueOf(helper.getImages().size()), Toast.LENGTH_SHORT).show();
    }
}