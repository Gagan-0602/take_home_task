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

public class ListAdapter extends ArrayAdapter<EpisodeN> implements View.OnClickListener {
    Context context;
    ArrayList<EpisodeN> Mlist;
    int type = 0;



    private static class ViewHolder {
        TextView episode;

    }

    public ListAdapter(Context context, ArrayList<EpisodeN> Mlist) {
        super(context, R.layout.episode_row, Mlist);
      //  this.context = context;
      //  this.Mlist = Mlist;

    }


    @Override
    public void onClick(View view) {
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EpisodeN modelClass = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

       // final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.episode_row, parent, false);
            viewHolder.episode = (TextView) convertView.findViewById(R.id.episode_id);

        //    result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Log.e(TAG, "Response getepisode: " + modelClass.episode);
        viewHolder.episode.setText(modelClass.episode);
      return convertView;    }
}

