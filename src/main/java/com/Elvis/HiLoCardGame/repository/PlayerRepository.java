package com.Elvis.HiLoCardGame.repository;

import com.Elvis.HiLoCardGame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("select p from Player p where p.name = ?1")
    Player findByName(String name);

}
