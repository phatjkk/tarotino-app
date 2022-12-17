package com.example.tarotino_app;

public class TarotCard {
    private String cardName;
    private int cardDetail;

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardDetail(int cardDetail) {
        this.cardDetail = cardDetail;
    }
    public TarotCard(String cardName, int cardDetail) {
        this.cardName = cardName;
        this.cardDetail = cardDetail;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardDetail() {
        return cardDetail;
    }
}
