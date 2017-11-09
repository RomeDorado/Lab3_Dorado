package com.dorado.lab3_dorado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etMessage;
    EditText etFilename;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = (EditText) findViewById(R.id.et_data);
        etFilename = (EditText) findViewById(R.id.et_filename);
        preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);

    }

    public void sharedPref(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", etMessage.getText().toString());
        editor.commit();

        Toast.makeText(this, "Successfully written to shared preferences", Toast.LENGTH_SHORT).show();
    }

    public void internalStorage(View view){
        FileOutputStream fos = null;

        String data = etMessage.getText().toString();
        String file = etFilename.getText().toString();


        try{

            fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(data.getBytes());


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Successfully written to internal storage", Toast.LENGTH_SHORT).show();

    }

    public void internalCache(View view ){

        String data = etMessage.getText().toString();
        String filename = etFilename.getText().toString();

        File folder = getCacheDir();
        File file = new File(folder, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to internal cache", Toast.LENGTH_SHORT).show();

    }

    public void externalCache(View view){

        String data = etMessage.getText().toString();
        String filename = etFilename.getText().toString();

        File folder = getExternalCacheDir();
        File file = new File(folder, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to external cache", Toast.LENGTH_SHORT).show();
    }

    public void externalStorage(View view){

        String data = etMessage.getText().toString();
        String filename = etFilename.getText().toString();

        File folder = getExternalFilesDir("temp");
        File file = new File(folder, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to external storage", Toast.LENGTH_SHORT).show();
    }

    public void externalPublic(View view){

        String data = etMessage.getText().toString();
        String filename = etFilename.getText().toString();

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to external public storage", Toast.LENGTH_SHORT).show();

    }

    public void nextPage(View view){
        Intent intent = new Intent(this, ReadingPage.class);

        startActivity(intent);
    }




}

