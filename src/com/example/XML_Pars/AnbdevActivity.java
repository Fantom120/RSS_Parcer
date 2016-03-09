package com.example.XML_Pars;

import android.app.Activity;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.net.URL;
import java.util.List;


public class AnbdevActivity extends Activity {
    ListView listView;
    private NewParser parser;
    private List<PostItem> messages;
    List<String> titles;
    ArrAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        parser = new NewParser();
        messages = parser.parse();


        listView = (ListView) findViewById(R.id.list);
       adapter = new ArrAdapter(this,messages);
       listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                URL Url = messages.get(position).getLink();
                Log.d("my", String.valueOf(Url));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(Url))));
            }
        });

    }






}
