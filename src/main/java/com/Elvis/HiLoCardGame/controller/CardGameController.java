package com.Elvis.HiLoCardGame.controller;

import com.Elvis.HiLoCardGame.entity.Player;
import com.Elvis.HiLoCardGame.entity.Score;
import com.Elvis.HiLoCardGame.model.NameAndScore;
import com.Elvis.HiLoCardGame.service.CardManager;
import com.Elvis.HiLoCardGame.model.CardAndResult;
import com.Elvis.HiLoCardGame.service.ServerReaderWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


/**
 * This class handles get and post requests sent from the front-end
 */
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
    public void playerRegistration(@RequestBody NameAndScore nameAndScore) {
        Player currentPlayer = serverReaderWriter.writePlayer(new Player(null, nameAndScore.getName()));
        serverReaderWriter.writeScore(new Score(null, nameAndScore.getScore(), currentPlayer, timeElapsed));
    }

    @GetMapping("getResultList")
    public List<Score> getResultList(@RequestParam(value = "filter") String filter) {
        if (filter.equals("score")) {
            return serverReaderWriter.sortByScore();
        } else if (filter.equals("gametime")) {
            return serverReaderWriter.sortByGameTime();
        }
        return null;
    }

    @GetMapping("getPlayerResults")
    public List<Score> getPlayerResults(@RequestParam(value = "player") int id, @RequestParam(value = "filter") String filter) {
        if (filter.equals("score")) {
            return serverReaderWriter.findByPlayerOrderedByScore(id);
        } else if (filter.equals("gametime")) {
            return serverReaderWriter.findByPlayerOrderedByPlayTime(id);
        }
        return null;
    }
}
