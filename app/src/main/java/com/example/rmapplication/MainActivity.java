package com.example.rmapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    private String TAG = MainActivity.class.getSimpleName();
    //private ProgressDialog progressDialog;
    private ListView listView;
    private ArrayList<EpisodeN> mList;
    public static final String URL_DATA = "https://rickandmortyapi.com/api/episode/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CharactersActivity.class);
                intent.putExtra("id", mList.get(position).getEpisode());
                intent.putExtra("chr", mList.get(position).getCharacters());

                startActivity(intent);

            }
        });
        new getEpisode().execute();


    }


    public class getEpisode extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("please wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            HttpHandler handler = new HttpHandler();
            String jsonstr = handler.makeServiceCall(URL_DATA);
            Log.e(TAG, "Response from url: " + jsonstr);
            return jsonstr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            if (s != null) {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray result = jsonObject.getJSONArray("results");
                    Log.e(TAG, "Response from url: " + result);

                    final int numberOfItemsInResp = result.length();
                    for (int i = 0; i < numberOfItemsInResp; i++) {
                        JSONObject object = result.getJSONObject(i);
                        Log.e(TAG, "Response from url---: " + object.getString("id"));
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String air_date = object.getString("air_date");
                        String characters = object.getString("characters");
                        String url = object.getString("url");
                        String episode = object.getString("episode");
                        String created = object.getString("created");
                        Log.e(TAG, "Response from url: " + episode);
                        //     Log.e("img", profile_img + ">>");
                        EpisodeN episodelist = new EpisodeN(id, name, air_date, episode, characters, url, created);
//
                        mList.add(episodelist);

                    }
                    listView.setAdapter(new ListAdapter(MainActivity.this, mList));

                } catch (final JSONException e) {

                    Log.e(TAG, "JOSN PARSING ERROR" + e.getMessage());

                }
            } else {
                Toast.makeText(getApplicationContext(), "JOSN PARSING ERROR Exist", Toast.LENGTH_SHORT).show();
            }

            //  ListAdapter listAdapter = new ListAdapter(MainActivity.this, mList);


        }
    }

}
