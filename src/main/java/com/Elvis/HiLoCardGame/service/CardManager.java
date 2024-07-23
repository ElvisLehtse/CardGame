package com.Elvis.HiLoCardGame.service;

import com.Elvis.HiLoCardGame.deck.Deck;
import com.Elvis.HiLoCardGame.model.Card;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CardManager {

    public static Card newCard;
    public static Card oldCard = new Card("Empty", 0);

    public Card getNewCard() {
        boolean isNewCardEqualToOld = true;
        while (isNewCardEqualToOld) {
            Random random = new Random();
            newCard = Deck.standardCardDeck().get(random.nextInt(Deck.standardCardDeck().size()));
            if (!newCard.getName().equals(oldCard.getName())) {
                isNewCardEqualToOld = false;
            }
        }
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
