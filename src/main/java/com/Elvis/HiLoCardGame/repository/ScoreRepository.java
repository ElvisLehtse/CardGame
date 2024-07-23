package com.Elvis.HiLoCardGame.repository;

import com.Elvis.HiLoCardGame.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository <Score, Integer> {
}
