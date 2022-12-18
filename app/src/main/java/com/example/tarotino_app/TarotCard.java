package com.example.tarotino_app;

public class TarotCard {
    private String cardName;
    private String cardImageURL;
    private String cardDetail;
    private String cardJSON;

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardDetail(String cardDetail) {
        this.cardDetail = cardDetail;
    }
    public TarotCard(String cardName, String cardDetail,String cardImageURL,String cardJSON) {
        this.cardName = cardName;
        this.cardDetail = cardDetail;
        this.cardImageURL = cardImageURL;
        this.cardJSON = cardJSON;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDetail() {
        return cardDetail;
    }

    public String getCardImageURL() {
        return cardImageURL;
    }

    public String getCardJSON() {
        return cardJSON;
    }

    public void setCardImageURL(String cardImageURL) {
        this.cardImageURL = cardImageURL;
    }
}
