package com.example.farid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray = {"Surah Fatiha","Surah Ikhlas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Quran-Bangla-English");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.surahname, mobileArray);

        ListView listView = (ListView) findViewById(R.id.SurahlistView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String surahName=mobileArray[position];
                Intent i = new Intent(MainActivity.this,ShowSurahDetails.class);
                i.putExtra("epuzzle",mobileArray[position]);
                startActivity(i);



            }
        });

    }
}
