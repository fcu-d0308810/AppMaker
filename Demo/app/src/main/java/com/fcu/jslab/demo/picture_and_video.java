package com.fcu.jslab.demo;

import android.app.Fragment;
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

public class picture_and_video extends Fragment implements View.OnClickListener {
    private String uploadSeverURL = null;
    private Button select_button , upload_button;
    private ImageView imageView;
    private TextView message_text;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.messageto, container, false);
        select_button = (Button) view.findViewById(R.id.Messageto_Selectbutton);
        upload_button = (Button) view.findViewById(R.id.Messageto_Uploadbutton);
        imageView = (ImageView) view.findViewById(R.id.Messageto_ImageView);
        message_text = (TextView) view.findViewById(R.id.Messageto_MessageText);
        select_button.setOnClickListener(this);
        upload_button.setOnClickListener(this);
        uploadSeverURL = "https://Tamama.com.tw";
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == select_button){

        }
        else if(view == upload_button){

        }
    }
}
