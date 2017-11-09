package com.dorado.lab3_dorado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadingPage extends AppCompatActivity {
    TextView tvData;
    EditText etFilename;
    FileInputStream fis;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_page);
        tvData = (TextView) findViewById(R.id.tv_data);
        etFilename = (EditText) findViewById(R.id.et_filename);
        preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);
    }

    public void showShared(View view){
        String data = preferences.getString("data", "");
        tvData.setText(data);
    }

    public void showInternalStorage(View view){
        String filename = etFilename.getText().toString();

        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput(filename);
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvData.setText(buffer.toString());


    }

    public void showInternalCache(View view){
        String filename = etFilename.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = new FileInputStream(new File(getCacheDir(), filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvData.setText(buffer.toString());

    }

    public void showExternalCache(View view){
        String filename = etFilename.getText().toString();

        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = new FileInputStream(new File(getExternalCacheDir(), filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvData.setText(buffer.toString());

    }

    public void showExternalStorage(View view){
        String filename = etFilename.getText().toString();

        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = new FileInputStream(new File(getExternalFilesDir("temp"), filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvData.setText(buffer.toString());

    }

    public void showExternalPublic(View view){
        String filename = etFilename.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvData.setText(buffer.toString());
    }

    public void backPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
