package com.Elvis.HiLoCardGame.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
public class Score {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotNull
    private int score;
    @ManyToOne
    @NotNull
    private Player player;
    @Column
    @NotNull
    private long playTime;
}
