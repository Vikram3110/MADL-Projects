package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ArrayList < File> mySongs = fetchSongs(Environment.getStorageDirectory());
         String[] items =new String[mySongs.size()];
         for(int i=0;i<mySongs.size();i++){
                items[i]=mySongs.get(i).getName().replace(".mp3","");
         }
        ListView listView=findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(HomePage.this, PlaySong.class);
//                String currentSong = listView.getItemAtPosition(position).toString();
//                intent.putExtra("songList", mySongs);
//                intent.putExtra("currentSong", currentSong);
//                intent.putExtra("position", position);
//                startActivity(intent);
//            }
//        });
    }
    int i=0;
    public ArrayList<File> fetchSongs(File file) {

        ArrayList arrayList = new ArrayList();
        Toast.makeText(this, "Current file is "+file.toString(), Toast.LENGTH_SHORT).show();
        File[] songs = file.listFiles();
        if (songs != null) {
            for (File myFile : songs) {
                Toast.makeText(this, "Current song is "+songs.toString(), Toast.LENGTH_SHORT).show();
                if (!myFile.isHidden() && myFile.isDirectory()) {
                    Toast.makeText(this, myFile.toString(), Toast.LENGTH_SHORT).show();
                    arrayList.addAll(fetchSongs(myFile));
                }
                else{
                    if(myFile.getName().endsWith(".mp3") && !myFile.getName().startsWith(".")){
                        Toast.makeText(this, "Songs Added1", Toast.LENGTH_SHORT).show();
                        arrayList.add(myFile);
                        Toast.makeText(this, (Integer) arrayList.get(i), Toast.LENGTH_SHORT).show();
                   }
                }
            }
        }
      int n=arrayList.size();
        Toast.makeText(this,"Size is"+n, Toast.LENGTH_SHORT).show();
        for(int j=0;j<arrayList.size();j++)
            Toast.makeText(this,j, Toast.LENGTH_SHORT).show();
        return arrayList;
    }
}