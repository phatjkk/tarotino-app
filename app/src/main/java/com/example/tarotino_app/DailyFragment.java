package com.example.tarotino_app;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class DailyFragment extends Fragment {
    Context context;
    public DailyFragment(){
        // require a empty public constructor
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        Button btnGetCard = (Button) view.findViewById(R.id.view_card_btn);

        Tarot tarot = new Tarot(getActivity());
        String cardJSONDataString = tarot.getRandomCard();

        JSONObject cardJSONDataObj = JSON_Parse(cardJSONDataString);
        try {
            String cardName = cardJSONDataObj.getString("name");
            TextView textViewCardName = (TextView) view.findViewById(R.id.card_name_daily);
            textViewCardName.setText(cardName);

            String cardImageUrl = cardJSONDataObj.getString("image_url").substring(1);
            ImageView i;
            Bitmap bm = getBitmapFromAsset(cardImageUrl);
            i = (ImageView)view.findViewById(R.id.img_card);
            i.setImageBitmap(bm);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnGetCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(getActivity(), ViewCardActivity.class);
                intent.putExtra("CardJSON", cardJSONDataString);
                startActivity(intent);
            }
        });
        return view;
    }
    private Bitmap getBitmapFromAsset(String strName) throws IOException
    {
        AssetManager assetManager = getActivity().getAssets();
        InputStream istr = assetManager.open(strName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

}