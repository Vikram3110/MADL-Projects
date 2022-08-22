package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {
    TextView textView;
    ImageView previous,play,next;
    ArrayList<String> songs;
    MediaPlayer mediaPlayer;
    String textContent;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        textView=findViewById(R.id.textView);
        play=findViewById(R.id.play);
        previous=findViewById(R.id.previous);
        next=findViewById(R.id.next);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        songs=(ArrayList) bundle.getParcelableArrayList("songList");
        textContent=intent.getStringExtra("currentSong");
        position=intent.getIntExtra("position",0);
        Uri uri= Uri.parse(songs.get(position).toString());
        Toast.makeText(this, "Song "+MediaPlayer.create(PlaySong.this,uri), Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(PlaySong.this,uri);
     //   mediaPlayer.start();
    }
}