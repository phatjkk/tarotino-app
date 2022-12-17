package com.example.tarotino_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ViewCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        Bundle extras = getIntent().getExtras();
        String cardJSONDataString = extras.getString("CardJSON");

        ExtendedFloatingActionButton myFab = (ExtendedFloatingActionButton) findViewById(R.id.extended_fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });



        JSONObject cardJSONDataObj = JSON_Parse(cardJSONDataString);
        try {
            String cardName = cardJSONDataObj.getString("name");
            TextView textViewCardName = (TextView)findViewById(R.id.card_name);
            textViewCardName.setText(cardName);

            String cardImageUrl = cardJSONDataObj.getString("image_url").substring(1);
            ImageView i;
            Bitmap bm = getBitmapFromAsset(cardImageUrl);
            i = (ImageView)findViewById(R.id.card_img);
            i.setImageBitmap(bm);

            String cardTitle = cardJSONDataObj.getString("title_main");
            TextView textViewCardTitle = (TextView)findViewById(R.id.title_main);
            textViewCardTitle.setText(cardTitle);

            String cardTitleSecondary = cardJSONDataObj.getString("title_secondary");
            TextView textViewCardTitleSecondary = (TextView)findViewById(R.id.title_secondary);
            textViewCardTitleSecondary.setText(cardTitleSecondary);

            String cardTitleT = cardJSONDataObj.getString("title_love");
            TextView textViewCardTitleT = (TextView)findViewById(R.id.title_love);
            textViewCardTitleT.setText(cardTitleT);

             cardTitleT = cardJSONDataObj.getString("title_work");
             textViewCardTitleT = (TextView)findViewById(R.id.title_work);
            textViewCardTitleT.setText(cardTitleT);

             cardTitleT = cardJSONDataObj.getString("title_money");
             textViewCardTitleT = (TextView)findViewById(R.id.title_money);
            textViewCardTitleT.setText(cardTitleT);

             cardTitleT = cardJSONDataObj.getString("title_heath");
             textViewCardTitleT = (TextView)findViewById(R.id.title_heath);
            textViewCardTitleT.setText(cardTitleT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Bitmap getBitmapFromAsset(String strName) throws IOException
    {
        AssetManager assetManager = getAssets();
        InputStream istr = assetManager.open(strName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public JSONObject JSON_Parse(String s){
        try {
            JSONObject cardObject = new JSONObject(s);
            return cardObject;
        }
        catch (Exception e){
            return null;
        }
    }
}