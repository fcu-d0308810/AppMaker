package com.fcu.jslab.demo;

import android.content.Entity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.StringEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class login_page extends AppCompatActivity{

    private Button login_button, registered_button;
    private EditText username_edittext, password_edittext;
    private String URL = "https://tamama.com.tw/mobile/login";
    private String strResult;
    String GetUsername, GetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //綁定ID
        login_button = (Button)findViewById(R.id.Login_Button);
        registered_button = (Button)findViewById(R.id.Registered_Button);
        username_edittext = (EditText)findViewById(R.id.Username_EditText);
        password_edittext = (EditText)findViewById(R.id.Password_EditText);

        //設定login_button跳轉頁面
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //拿使用者輸入的資料
                GetUsername = username_edittext.getText().toString();
                GetPassword = password_edittext.getText().toString();
                SendDataToServer(GetUsername, GetPassword);
            }
        });
        //設定註冊的跳轉頁面
        registered_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_page.this, register_page.class);
                startActivity(intent);
            }
        });
    }
    public void SendDataToServer(final String username, final String password){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... strings) {
                String QuickUsername = username;
                String QuickPassword = password;
                String msg = "username="+QuickUsername+"&password="+QuickPassword;
                String result="";
                //InputStream inputStream = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username",QuickUsername));
                nameValuePairs.add(new BasicNameValuePair("password",QuickPassword));
                try{

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(URL);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                    Log.d("fuck","OK1");
                    HttpResponse response = httpClient.execute(httpPost);
                    Log.d("fuck","OK2");
                    //Entity TamamaEntity = response.getEntity();
                    String tamamaCall = response.getEntity().toString();
                    //Log.i("Tamama:", );
                    //System.out.println(response.getEntity().getContent().toString());

                    if(tamamaCall != null){
                        //處理伺服器回傳token
                        InputStream inputStream1 = response.getEntity().getContent();
                        result = ChangeInputStreamToData(inputStream1);
                        //Toast.makeText(getBaseContext() ,result, Toast.LENGTH_SHORT).show();
                        Log.d("response:",result);
                        Intent intent = new Intent(login_page.this, Mainpage.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getBaseContext(),"There is something wrong",Toast.LENGTH_SHORT).show();
                    }
                    

                }catch (ClientProtocolException e){

                }catch (IOException e){

                }
                return "Data Submit Successfully";
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(username, password);
    }
    public String ChangeInputStreamToData(InputStream inputStream){
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            while((line = reader.readLine()) != null){
                total.append(line);
            }
        }catch(IOException e){
            Toast.makeText(this,  "Change: IOException", Toast.LENGTH_SHORT);
        }catch(Exception e){
            Toast.makeText(this,  "Change: Exception", Toast.LENGTH_SHORT);
        }
        return total.toString();
    }
}
