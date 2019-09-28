package com.example.rmapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharactersActivity extends AppCompatActivity {
    Context context;
    private String TAG = MainActivity.class.getSimpleName();
    //   private ProgressDialog progressDialog;
    private ListView c_listView;
    private ArrayList<CharacterMC> cList;
    public String URL = "";
    String episode_id = "";
    String cha = "";
    String jsonstr = "";
    List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_list);
        context = this;
        cList = new ArrayList<CharacterMC>();
        c_listView = (ListView) findViewById(R.id.characterlist);

        String loginToken = getIntent().getStringExtra("chr");
        System.out.println(loginToken.substring(1, loginToken.length() - 1));

     /*   c_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CharactersActivity.this, Character_info.class);
                intent.putExtra("data", cList.get(position));
                startActivity(intent);

            }
        });*/

        episode_id = getIntent().getStringExtra("id");
        Log.d(TAG, "simar: " + episode_id);
        Log.d(TAG, "simar: " + loginToken.substring(1, loginToken.length() - 1));
        cha = loginToken.substring(1, loginToken.length() - 1);
        final String[] elements = cha.split(",");

        items = Arrays.asList(cha.split("\\s*,\\s*"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, elements);
        c_listView.setAdapter(arrayAdapter);
        c_listView.setOnItemClickListener(new AdapterView


                .OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                URL = elements[position];
                URL = URL.substring(1, URL.length() - 1);
                URL = URL.replace("\\/", "/");


                new getCharacters().execute();
            }
        });

        //   c_listView.setAdapter(new CharacterAdapter(CharactersActivity.this, cList));
        //


    }


    public class getCharacters extends AsyncTask<Void, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CharactersActivity.this);
            progressDialog.setMessage("please wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {

            HttpHandler handler = new HttpHandler();


            jsonstr = handler.makeServiceCall(URL);
            Log.e(TAG, "url==: " + URL);
            Log.e(TAG, "Response from url: simar " + jsonstr);





       /*     if (jsonstr != null) {
                try {
                    JSONObject object = new JSONObject(jsonstr);
                    Log.e(TAG, "jobj: simar " + object);

                  //  JSONArray result = jsonObject.getJSONArray();
                  //  Log.e(TAG, "jsonres: " + result);

                   // final int numberOfItemsInResp = result.length();
                   //
                   //     JSONObject object = result.getJSONObject(i);
                        Log.e(TAG, "Respone   id---: " + object.getString("id"));
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String status = object.getString("status");
                        String species = object.getString("species");
                        String type = object.getString("type");
                        String gender = object.getString("gender");
                        String origin = object.getString("origin");
                        String location = object.getString("location");
                        String image = object.getString("image");
                        String episode = object.getString("episode");
                        String url = object.getString("url");
                        String created = object.getString("created");
                        Log.e(TAG, "name: " + name);
                        //     Log.e("img", profile_img + ">>");
                        CharacterMC characterMC = new CharacterMC(id, name, status, species, type, gender, origin, location, image, episode, url, created);
//
                        cList.add(characterMC);
                        Log.e(TAG, "clist===: " + cList);


                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }*/

            return jsonstr;
        }


        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing())
                progressDialog.dismiss();


             //{"id":12,"name":"Alexander","status":"Dead","species":"Human","type":"","gender":"Male","origin":{"name":"Earth (C-137)","url":"https://rickandmortyapi.com/api/location/1"},"location":{"name":"Anatomy Park","url":"https://rickandmortyapi.com/api/location/5"},"image":"https://rickandmortyapi.com/api/character/avatar/12.jpeg","episode":["https://rickandmortyapi.com/api/episode/3"],"url":"https://rickandmortyapi.com/api/character/12","created":"2017-11-04T20:32:33.144Z"}/n
            if (s != null) {
                try {

                    JSONObject object = new JSONObject(s);



                        Log.e(TAG, "Respone   id---: " + object.getString("id"));
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String status = object.getString("status");
                        String species = object.getString("species");
                        String type = object.getString("type");
                        String gender = object.getString("gender");
                        String origin = object.getString("origin");
                        String location = object.getString("location");
                        String image = object.getString("image");
                        String episode = object.getString("episode");
                        String url = object.getString("url");
                        String created = object.getString("created");
                        Log.e(TAG, "name: " + name);
                        //     Log.e("img", profile_img + ">>");
                    CharacterMC characterMC = new CharacterMC(id, name, status, species, type, gender, origin, location, image, episode, url, created);
//

                    Intent intent= new Intent(CharactersActivity.this,Character_info.class);
                    intent.putExtra("data",characterMC);
                    startActivity(intent);


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


