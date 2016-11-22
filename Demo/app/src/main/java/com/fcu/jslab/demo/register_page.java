package com.fcu.jslab.demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class register_page extends AppCompatActivity {

    TextView edittext_account, edittext_password;
    Button button_send;
    Person person;
    String account, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        edittext_account = (TextView)findViewById(R.id.Registered_Edittext_Account);
        edittext_password = (TextView)findViewById(R.id.Registered_Edittext_Password);
        button_send = (Button)findViewById(R.id.Registered_Button_Send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    // call AsynTask to perform network operation on separate thread
                    new HttpAsyneTask().execute("http://hmkcode.appspot.com/jsonservlet");
                    finish();
                }
                else{
                    Toast.makeText(register_page.this , "請輸入一些字吧",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //判斷account和password裡面有沒有輸入
    private boolean validate(){
        if(edittext_account.getText().toString().trim().equals(""))
            return false;
        else if(edittext_password.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }
    private class HttpAsyneTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            person = new Person();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    account = edittext_account.getText().toString();
                    password = edittext_password.getText().toString();
                }
            });
            person.setAccount(account);
            person.setPassword(password);
            return POST(urls[0], person);
        }
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }
    public static String POST(String url, Person person){
        InputStream inputStream = null;
        String result = "";
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";

            JSONObject jsonObject = new JSONObject();
            //這裡只是測試的直到時候要改掉
            jsonObject.accumulate("name",person.getName());
            jsonObject.accumulate("country",person.getCountry());
            jsonObject.accumulate("twitter",person.getTwitter());

            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            //接收回應
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
                Log.i("Send",result);
            }
            else{
                result = "Did not work!";
                Log.i("Send",result);
            }

        }catch (Exception e){
            Log.i("InputStream", e.getLocalizedMessage());
        }
        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
