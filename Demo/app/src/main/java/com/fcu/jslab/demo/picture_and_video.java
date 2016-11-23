package com.fcu.jslab.demo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/18.
 */

public class picture_and_video extends Fragment{
    Button picture_button, video_button;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.picture_and_video, container, false);
        picture_button = (Button) view.findViewById(R.id.Picture_Button);
        video_button = (Button) view.findViewById(R.id.Video_Button);
        picture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),picture.class);
                startActivity(intent);
            }
        });
        video_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),picture.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
