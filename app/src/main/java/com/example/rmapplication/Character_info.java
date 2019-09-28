package com.example.rmapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Character_info extends AppCompatActivity {
ImageView image;
TextView textView,status,gender,species;
String data="";
String name="";
    private ListView c_listView;
    private ArrayList<CharacterMC> cList;
ProgressDialog pDialog;
    public static final String URL = "https://rickandmortyapi.com/api/character";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);
        image=(ImageView)findViewById(R.id.character_image);
        textView=(TextView)findViewById(R.id.chara_name);
        status=(TextView)findViewById(R.id.cha_status);
        gender=(TextView)findViewById(R.id.gender);
        species=(TextView)findViewById(R.id.species);

        CharacterMC characterMC = (CharacterMC) getIntent().getSerializableExtra("data");
     //  data = characterMC.getImage();
     //   imageView.setImageBitmap(data);
        new AsyncTaskLoadImage(image).execute(characterMC.getImage());

        name=characterMC.getName();

        textView.setText(name);
        status.setText(characterMC.getStatus());
        gender.setText(characterMC.getGender());
        species.setText(characterMC.getSpecies());

       // Log.d(TAG, "Response from charra: " + charsss);
    }
    public class AsyncTaskLoadImage  extends AsyncTask<String, String, Bitmap> {
        private final static String TAG = "AsyncTaskLoadImage";
        private ImageView imageView;
        public AsyncTaskLoadImage(ImageView imageView) {
            this.imageView = imageView;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                java.net.URL url = new URL(params[0]);
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}