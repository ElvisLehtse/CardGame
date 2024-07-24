package com.Elvis.HiLoCardGame.service;

import com.Elvis.HiLoCardGame.entity.Player;
import com.Elvis.HiLoCardGame.entity.Score;
import com.Elvis.HiLoCardGame.repository.PlayerRepository;
import com.Elvis.HiLoCardGame.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerReaderWriter {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ScoreRepository scoreRepository;

    public Player writePlayer(Player player) {
        Player currentPlayer = playerRepository.findByName(player.getName());
        if (currentPlayer == null) {
            currentPlayer = playerRepository.save(player);
        }
        return currentPlayer;
    }

    public Player findPlayerName(String name) {
        return playerRepository.findByName(name);
    }

    public void writeScore(Score score) {
        scoreRepository.save(score);
    }

    public List<Score> sortByScore() {
        return scoreRepository.findMaxScoreForEachPlayerOrderByScoreDESC();
    }

    public List<Score> sortByGameTime() {
        return scoreRepository.findMaxScoreForEachPlayerOrderByPlayTime();
    }

    public List<Score> findByPlayer(int id) {
        return scoreRepository.findByPlayer_Id(id);
    }
}
