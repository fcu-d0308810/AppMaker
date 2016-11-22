package com.fcu.jslab.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class picture extends AppCompatActivity implements View.OnClickListener {

    private String uploadSeverURL = null;
    private String imagepath = null;
    private Button select_button , upload_button;
    private ImageView imageView;
    private TextView message_text;
    private ProgressDialog dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        select_button = (Button) findViewById(R.id.Messageto_Selectbutton);
        upload_button = (Button) findViewById(R.id.Messageto_Uploadbutton);
        imageView = (ImageView) findViewById(R.id.Messageto_ImageView);
        message_text = (TextView) findViewById(R.id.Messageto_MessageText);
        select_button.setOnClickListener(this);
        upload_button.setOnClickListener(this);
        uploadSeverURL = "https://Tamama.com.tw";
    }

    @Override
    public void onClick(View view) {
        if (view == select_button){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete action using..."),1);
        }
        else if(view == upload_button){
            //dialog = ProgressDialog.show();
        }
    }

}
