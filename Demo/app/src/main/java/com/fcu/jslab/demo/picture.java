package com.fcu.jslab.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class picture extends AppCompatActivity implements View.OnClickListener {

    private String uploadSeverURL = null;
    private String imagepath = null;
    private Button select_button , upload_button;
    private ImageView imageView;
    private TextView message_text;
    private ProgressDialog dialog = null;
    private int serverResponseCode = 0;
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
            dialog = ProgressDialog.show(picture.this, "", "Uploading file...", true);
            message_text.setText("uploading started.....");
            new Thread(new Runnable() {
                public void run() {
                    uploadFile(imagepath);
                }
            }).start();
        }
    }
    public int uploadFile(String sourceFileUri){
        String filename = sourceFileUri;
        HttpURLConnection connection = null;
        DataOutputStream dos = null;
        File sourceFile = new File(sourceFileUri);
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        if(!sourceFile.isFile()){
            dialog.dismiss();
            Log.e("uploadFile","Source File is not exist: " + imagepath);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    message_text.setText("Source File is not exist: " + imagepath);
                }
            });
            return 0;
        }
        else{
            try{

                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(uploadSeverURL);
                //打開httpconnection
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", filename);

                dos = new DataOutputStream(connection.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + filename + "\"" + lineEnd);
                dos.writeBytes(lineEnd);

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                buffer = new byte[bufferSize];
                bytesRead = fileInputStream.read(buffer, 0,bufferSize);
                while(bytesRead > 0){
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();
                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);
                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {
                            String msg = "File Upload Completed.\n\n See uploaded file your server. \n\n";
                            message_text.setText(msg);
                            Toast.makeText(picture.this, "File Upload Complete.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                fileInputStream.close();
                dos.flush();
                dos.close();

            }catch (MalformedURLException ex){
                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        message_text.setText("MalformedURLException Exception : check script url.");
                        Toast.makeText(picture.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            }catch (Exception e){
                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        message_text.setText("Got Exception : see logcat ");
                        Toast.makeText(picture.this, "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload file Exception", "Exception : "  + e.getMessage(), e);
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getData().getPath();

            Uri selectedImageUri = data.getData();
            imagepath = getPath(selectedImageUri);
            Bitmap bitmap= BitmapFactory.decodeFile(imagepath);
            imageView.setImageBitmap(bitmap);
            message_text.setText("Uploading file path:" +imagepath);

        }
    }
    // the problem goes here
    // can't get the path
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
