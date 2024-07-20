package com.Elvis.HiLoCardGame.controller;

import com.Elvis.HiLoCardGame.CardManager;
import com.Elvis.HiLoCardGame.model.CardAndResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class CardGameController {

    @Autowired
    CardManager cardManager;

    @GetMapping("startGame")
    public CardAndResult startGame() {
        return new CardAndResult(cardManager.getNewCard(), null);
    }

    @GetMapping("userGuess")
    public CardAndResult getUserGuess(@RequestParam(value = "value") String value) {
        boolean isUserGuessCorrect = cardManager.compareCards(value);
        return new CardAndResult(CardManager.newCard, isUserGuessCorrect);
    }
}
