package com.Elvis.HiLoCardGame.service;

import com.Elvis.HiLoCardGame.config.AppConfig;
import com.Elvis.HiLoCardGame.deck.Deck;
import com.Elvis.HiLoCardGame.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CardManager {

    @Autowired
    Random random;
    public static Card newCard;
    public static Card oldCard = new Card("Empty", 0);

    public Card getNewCard() {
        List<Card> newCardList = Deck.standardCardDeck();
        newCardList.remove(oldCard);
        newCard = newCardList.get(random.nextInt(newCardList.size()));
        return newCard;
    }

    public boolean compareCards(String value) {
        oldCard = newCard;
        newCard = getNewCard();
        return (value.equals("lower") && oldCard.getRank() > newCard.getRank()) ||
                (value.equals("equal") && oldCard.getRank() == newCard.getRank()) ||
                (value.equals("higher") && oldCard.getRank() < newCard.getRank());
    }
}
