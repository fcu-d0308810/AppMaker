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

public class messageto extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.messageto, container, false);
        Button select_button = (Button) view.findViewById(R.id.Messageto_Selectbutton);
        Button upload_button = (Button) view.findViewById(R.id.Messageto_Uploadbutton);
        ImageView imageView = (ImageView) view.findViewById(R.id.Messageto_ImageView);
        TextView message_text = (TextView) view.findViewById(R.id.Messageto_MessageText);
        
        return view;
    }
}
