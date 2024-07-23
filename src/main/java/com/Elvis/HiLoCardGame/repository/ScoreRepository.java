package com.Elvis.HiLoCardGame.repository;

import com.Elvis.HiLoCardGame.entity.Score;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository <Score, Integer> {
    @Query("select s from Score s order by s.score DESC")
    List<Score> findByOrderByScoreDesc();

    @Query("select s from Score s order by s.playTime")
    List<Score> findByOrderByPlayTimeAsc();
}
