package com.Elvis.HiLoCardGame.repository;

import com.Elvis.HiLoCardGame.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository <Score, Integer> {
    @Query("select s from Score s where s.score = (select max(s2.score) from Score s2 where s2.player.id = s.player.id) order by s.score DESC")
    List<Score> findMaxScoreForEachPlayerOrderByScoreDESC();

    @Query("select s from Score s where s.score = (select max(s2.score) from Score s2 where s2.player.id = s.player.id) order by s.playTime")
    List<Score> findMaxScoreForEachPlayerOrderByPlayTime();

    @Query("select s from Score s where s.player.id = ?1 order by s.score DESC")
    List<Score> findByPlayer_IdOrderByScoreDESC(Integer id);

    @Query("select s from Score s where s.player.id = ?1 order by s.playTime")
    List<Score> findByPlayer_IdOrderByPlayTime(Integer id);
}
