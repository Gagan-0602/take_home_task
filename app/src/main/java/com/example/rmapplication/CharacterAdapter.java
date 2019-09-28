package com.example.rmapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CharacterAdapter  extends ArrayAdapter<CharacterMC> implements View.OnClickListener {
    Context context;
    ArrayList<CharacterMC> clist;
    String cha="";
    int type = 0;




    private static class ViewHolder {
        TextView character_name;

    }



    public CharacterAdapter(Context context, ArrayList<CharacterMC> clist) {
        super(context, R.layout.character_row, clist);
        //    this.context = context;
        //    this.clist = clist;

    }
    @Override
    public void onClick(View view) {
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CharacterMC modelClass1 = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        // final View resu

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.character_row, parent, false);
            viewHolder.character_name = (TextView) convertView.findViewById(R.id.character_name);

            //    result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CharacterAdapter.ViewHolder) convertView.getTag();
        }

        Log.e(TAG, "Response getcharacters: " + modelClass1.name);

        viewHolder.character_name.setText(modelClass1.name);
        return convertView;    }
}


