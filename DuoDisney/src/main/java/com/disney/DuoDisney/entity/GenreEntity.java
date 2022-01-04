package com.disney.DuoDisney.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//@author aduo
@Table(name = "genres")
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
@Entity

public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String image;

    @ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<MovieEntity> movies = new ArrayList<>();

    // Soft Delete:
    private boolean deleted = Boolean.FALSE;
}
