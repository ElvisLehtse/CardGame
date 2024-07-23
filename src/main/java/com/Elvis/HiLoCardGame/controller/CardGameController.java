package com.Elvis.HiLoCardGame.controller;

import com.Elvis.HiLoCardGame.entity.Player;
import com.Elvis.HiLoCardGame.entity.Score;
import com.Elvis.HiLoCardGame.service.CardManager;
import com.Elvis.HiLoCardGame.model.CardAndResult;
import com.Elvis.HiLoCardGame.service.ServerReaderWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RestController
@CrossOrigin("http://localhost:3000")
public class CardGameController {

    @Autowired
    CardManager cardManager;
    @Autowired
    ServerReaderWriter serverReaderWriter;

    Instant start;
    long timeElapsed;

    @GetMapping("startGame")
    public CardAndResult startGame() {
        start = Instant.now();
        return new CardAndResult(cardManager.getNewCard(), null);
    }

    @GetMapping("endGame")
    public void endGame() {
        Instant finish = Instant.now();
        timeElapsed = Duration.between(start, finish).toSeconds();
    }

    @GetMapping("userGuess")
    public CardAndResult getUserGuess(@RequestParam(value = "value") String value) {
        boolean isUserGuessCorrect = cardManager.compareCards(value);
        return new CardAndResult(CardManager.newCard, isUserGuessCorrect);
    }

    @PostMapping("playerRegistration")
    public void playerRegistration(@RequestParam(value = "name") String name, @RequestParam(value = "score") int score) {
        serverReaderWriter.writePlayer(new Player(null, name));
        serverReaderWriter.writeScore(new Score(null, score, serverReaderWriter.findPlayerName(name), timeElapsed));
    }
}
