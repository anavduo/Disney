package com.disney.DuoDisney.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

//@author aduo
@Table(name = "movie")
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
@Entity

public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    @Column(unique = true)
    private String title;
    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate creationDate;
    @Nullable
    private Integer rating; // from 1 up to 5

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(
            name = "movie_chars",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "char_id"))
    private List<CharacterEntity> characters = new ArrayList<>();

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE},
        fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres = new ArrayList<>();

  // Soft Delete:
    private boolean deleted = Boolean.FALSE;
}
