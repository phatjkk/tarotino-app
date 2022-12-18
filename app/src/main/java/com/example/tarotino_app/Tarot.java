package com.example.tarotino_app;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Tarot {
    List<String> allCard = new ArrayList<String>();
    public Tarot(ContextWrapper context){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("tarot_data.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                this.allCard.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

    }
    public String getCardByPosition(int position){
        return allCard.get(position);
    }
    public String getRandomCard(){
        Random rand = new Random();
        String randomOneCardInList = this.allCard.get(rand.nextInt(this.allCard.size()));
        return randomOneCardInList;
    }
    public ArrayList<TarotCard> getAllCardsObj(){
        ArrayList<TarotCard> result =  new ArrayList<TarotCard>();;
        for (String cardStrData:allCard) {
            JSONObject cardJSONDataObj = JSON_Parse(cardStrData);
            try {
                String cardName = cardJSONDataObj.getString("name");
                String cardImageUrl = cardJSONDataObj.getString("image_url").substring(1);
                String detailStr = "Lá bài đứng";
                if(cardName.contains("ngược")) {
                    detailStr = "Lá bài ngược";
                }
                TarotCard cardObj = new TarotCard(cardName,detailStr,cardImageUrl,cardStrData);
                result.add(cardObj);
            }catch (JSONException e){

            }
        }
        return result;
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
