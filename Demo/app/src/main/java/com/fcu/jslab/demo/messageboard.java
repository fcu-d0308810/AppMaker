package com.fcu.jslab.demo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Administrator on 2016/9/18.
 */

public class messageboard extends Fragment implements View.OnClickListener{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.messageboard, container, false);

        Button addmessage_button = (Button) view.findViewById(R.id.Addessage_Button);
        addmessage_button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Addessage_Button){
            Intent intent = new Intent();
            intent.setClass(getActivity() , messageboard_add.class);
            startActivity(intent);
        }
    }
}
