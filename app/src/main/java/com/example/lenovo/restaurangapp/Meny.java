package com.example.lenovo.restaurangapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Meny extends AppCompatActivity {
    private ListView listView;
    private List<String> list = new ArrayList<String>();
    private Meny parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = this;
        setContentView(R.layout.activity_meny);
        listView = (ListView) findViewById(R.id.ListMenu);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<ResponseBody> call = api.getData("http://10.0.2.2/testi/API.php");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string();
                    Log.d("list", res);
                    try {
                        JSONArray jsArr = new JSONArray(res);
                        for (int x=0 ; x<jsArr.length() ; x++){
                            list.add(jsArr.getJSONObject(x).getString("Meny_Text").toString());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                parent,
                                android.R.layout.simple_list_item_1,
                                list );
                        listView.setAdapter(arrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        //Adapter som för över alla element från våran fyllda list till listview så att det blir synligt.


    }
}
