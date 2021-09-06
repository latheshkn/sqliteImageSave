package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MY_COMPANY.DB";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "USERS";
    static final String USER_ID = "_ID";
    static final String USER_IMAGE = "user_image";
    static final String IMAGE = "image";
    private ByteArrayOutputStream byteArrayOutputStream;
    byte[] imageInbyte;
    private static final String CREATE_DB_QUERY = " CREATE TABLE " + TABLE_NAME + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_IMAGE + " TEXT NOT NULL," + IMAGE + " BLOB" + ");";
    private Context context;
    Cursor cursor;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insert(ModelClass objectModelClass) {
        SQLiteDatabase database = getReadableDatabase();
        Bitmap imageToStore = objectModelClass.getImage();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInbyte = byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_IMAGE, objectModelClass.getImageName());
        contentValues.put(DatabaseHelper.IMAGE, imageInbyte);
//        if data stored successfully it will return id of the data or else it will return -1
        long checkInserted = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

        if (checkInserted != -1) {
            Toast.makeText(context, String.valueOf(checkInserted), Toast.LENGTH_SHORT).show();
            database.close();
        } else {
            Toast.makeText(context, String.valueOf(checkInserted), Toast.LENGTH_SHORT).show();

        }
    }

    public ArrayList<ModelClass> getImages() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<ModelClass> image = new ArrayList<>();

        cursor = database.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.getCount() != 0) {
            Toast.makeText(context, String.valueOf(cursor.getCount()), Toast.LENGTH_SHORT).show();
//            cursor.moveToFirst();
//            while (cursor.moveToNext()) {
//                String nameOfImage = cursor.getString(1);
//                byte[] imagebyte = cursor.getBlob(2);
//
//                Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);
//                image.add(new ModelClass("nameOfImage", null));
//                Log.d("checkthelist",cursor.getString(0));
//            }
            if (cursor.moveToFirst()) {
                do {
                    image.add(new ModelClass("nameOfImage", null));

                } while (cursor.moveToNext());
            }

            return image;
        } else {
            Toast.makeText(context, "No value", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public Cursor getAllIMages() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<ModelClass> image = new ArrayList<>();

        cursor = database.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }
}
