package com.example.lenovo.restaurangapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Meny extends AppCompatActivity {
    private ListView listView;
    private List<String> list = new ArrayList<String>();
    String jsonItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meny);
        listView = (ListView) findViewById(R.id.ListMenu);
        ReadJSON obj = new ReadJSON();
        jsonItems = obj.loadJSONFromAsset(Meny.this);
        try {
            JSONArray jsArr = new JSONArray(jsonItems);
            for (int x=0 ; x<jsArr.length() ; x++){
                list.add(jsArr.getJSONObject(x).getString("Name").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Adapter som för över alla element från våran fyllda list till listview så att det blir synligt.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list );
        listView.setAdapter(arrayAdapter);

    }
}
