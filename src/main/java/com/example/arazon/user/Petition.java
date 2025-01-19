package com.example.arazon.user;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Petition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name="description",length = 10000)
    private String description;

    @Column(name="date",length = 560)
    private String date;

    @Column(name="vote_quantity")
    private int voteQuantity;
}
