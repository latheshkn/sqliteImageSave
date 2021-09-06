package com.example.sqlitedatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView ivProfile;
    TextView tvImageName;
    Button btnSave, btnMoveNext;
    DatabaseHelper databaseHelper;
    Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivProfile = findViewById(R.id.ivProfile);
        tvImageName = findViewById(R.id.tvImageName);
        btnSave = findViewById(R.id.btnSave);
        btnMoveNext = findViewById(R.id.btnMoveNext);
        databaseHelper = new DatabaseHelper(this);
        getPost();
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImage = new Intent(Intent.ACTION_PICK);
                intentImage.setType("image/*");
                startActivityForResult(intentImage, 102);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeImage();
            }
        });
        btnMoveNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 102) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            try {
                bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                ivProfile.setImageBitmap(bitmapImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "data", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeImage() {

        databaseHelper.insert(new ModelClass("image", bitmapImage));
    }

    public void getPost() {



        try {
            Api api = BaseClient.getRetrofit().create(Api.class);
            Call<ArrayList<GetPostModel>> call = api.getPost();

            call.enqueue(new Callback<ArrayList<GetPostModel>>() {
                @Override
                public void onResponse(Call<ArrayList<GetPostModel>> call, Response<ArrayList<GetPostModel>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<GetPostModel>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "onfail", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
}