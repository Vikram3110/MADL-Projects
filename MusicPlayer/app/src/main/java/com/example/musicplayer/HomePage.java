package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
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
    Cursor cursor;
    Uri uri;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ArrayList <String> mySongs = fetchSongs();
//        ArrayList<String> mySongs= new ArrayList<>();
//        for(int i=0;i<items.size();i++)
//        {
//            mySongs.add(items.get(i).replace(".mp3",""));
//        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,mySongs);
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent=new Intent(HomePage.this,PlaySong.class);
              String currentSong=listView.getItemAtPosition(position).toString();
              intent.putExtra("songList",mySongs);
              intent.putExtra("currentSong",currentSong);
              intent.putExtra("position",position);
              startActivity(intent);
            }
        });
    }
    public ArrayList<String> fetchSongs() {
        String[] audioDetailArray = { MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME};
        ArrayList<String> arrayList = new ArrayList<>();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        cursor =getContentResolver().query(uri,audioDetailArray,null,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    int audioIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                    if(cursor.getString(audioIndex).endsWith(".mp3"))
                    {
                        arrayList.add(cursor.getString(audioIndex));
                    }
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        return arrayList;
    }
}