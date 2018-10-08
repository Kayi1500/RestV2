package com.example.lenovo.restaurangapp;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenovo on 2018-10-07.
 */


public class ReadJSON extends Meny{

    //Läser in JSON-filen från filen "Assets" och returnerar hela texten som en string.
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Items.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
