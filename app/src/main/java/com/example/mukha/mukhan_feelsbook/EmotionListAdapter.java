package com.example.mukha.mukhan_feelsbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * This is a customized List adapter class to help displaying custom ListViews
 *
 */

public class EmotionListAdapter extends ArrayAdapter<Emotion> {


    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView feeling;
        TextView dateFormat;
        TextView comment;
    }

    /**
     * Default constructor for the EmotionListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public EmotionListAdapter(Context context, int resource, ArrayList<Emotion> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the emotion information
        String feeling = getItem(position).getFeeling();
        String dateFormat = getItem(position).getDate();
        String comment = getItem(position).getComment();

        //Create the emotion object with the information
        try {
            Emotion emotion = new Emotion(feeling, comment);
            Date date= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateFormat);
            emotion.setDate(date);


            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;


            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.feeling = (TextView) convertView.findViewById(R.id.emotionText);
                holder.comment = (TextView) convertView.findViewById(R.id.commentText);
                holder.dateFormat = (TextView) convertView.findViewById(R.id.dateText);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }


            Animation animation = AnimationUtils.loadAnimation(mContext,
                    (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
            result.startAnimation(animation);
            lastPosition = position;

            holder.feeling.setText(emotion.getFeeling());
            holder.comment.setText(emotion.getComment());
            holder.dateFormat.setText(emotion.getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }



        return convertView;
    }
}
