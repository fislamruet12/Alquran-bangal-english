package com.example.farid.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowSurahDetails extends AppCompatActivity {

    BaseAdapter quranAdb;

    private ClipboardManager myClipboard;
    private ClipData myClip;


    ArrayList<DividePart> Ayalist =new ArrayList<>();
    ListView surahlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_surah_details);
        setTitle("Quran-Bangla-English");

        Intent intent = getIntent();
        String Suraname = intent.getExtras().getString("epuzzle").toString();

        TextView name= (TextView) findViewById(R.id.Surahnaming);
        name.setText(Suraname);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

       // Toast.makeText(getBaseContext(), Suraname+" ", Toast.LENGTH_SHORT).show();
        surahlistView= (ListView) findViewById(R.id.SurahdetailslistView);

        readFile(Suraname);
        LoadsurahWithBangla();
    }

    private void readFile(String name)
    {
        String a="",b="",e="";

        try {
            InputStream is =getAssets().open(name+".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
              int i=1;
            while ((line = reader.readLine()) != null) {
                if (i == 1) {
                    a = line;

                } else if (i == 2) {
                    b = line;
                } else {
                    e = line;
                    DividePart dividePart = new DividePart(a, b, e);
                    Ayalist.add(dividePart);
                    i = 0;
                }
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void LoadsurahWithBangla()
    {
      //  Toast.makeText(getBaseContext(), "Load from Bangla", Toast.LENGTH_SHORT).show();

        quranAdb=new BaseAdapter() {



            LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public int getCount() {
                return Ayalist.size() ;
            }

            @Override
            public Object getItem(int position) {
                return Ayalist.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if(view==null)
                {
                    view=inflater.inflate(R.layout.surahdetails,null);
                }


                TextView name=(TextView) view.findViewById(R.id.labela);
                TextView defaul=(TextView) view.findViewById(R.id.labeld);

                name.setText((position+1)+") "+Ayalist.get(position).getArabic().toString());
                defaul.setText(Ayalist.get(position).getBangla().toString());
                return view;
            }
        };

        surahlistView.setAdapter(quranAdb);
        surahlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myClip=ClipData.newPlainText("text",Ayalist.get(position).getArabic().toString()+"\n\n"+Ayalist.get(position).getBangla().toString());
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getBaseContext(), "copied To the ClipBoard", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.Bangla:
                // do your code
                 LoadsurahWithBangla();

                return true;
            case R.id.English:
                // do your code
                LoadsurahWithEnglish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LoadsurahWithEnglish()
    {
        quranAdb=new BaseAdapter() {



            LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public int getCount() {
                return Ayalist.size() ;
            }

            @Override
            public Object getItem(int position) {
                return Ayalist.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if(view==null)
                {
                    view=inflater.inflate(R.layout.surahdetails,null);
                }


                TextView name=(TextView) view.findViewById(R.id.labela);
                TextView defaul=(TextView) view.findViewById(R.id.labeld);

                name.setText((position+1)+") "+Ayalist.get(position).getArabic().toString());
                defaul.setText(Ayalist.get(position).getEnglish().toString());
                return view;
            }
        };

        surahlistView.setAdapter(quranAdb);
        surahlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myClip=ClipData.newPlainText("text",Ayalist.get(position).getArabic().toString()+"\n\n"+Ayalist.get(position).getEnglish().toString());
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getBaseContext(), "copied To the ClipBoard", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



    }

}
