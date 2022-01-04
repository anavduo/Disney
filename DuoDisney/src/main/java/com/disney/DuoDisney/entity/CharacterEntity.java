package com.disney.DuoDisney.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//@author aduo
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?") //This query provide the soft delete, as an update over icon
@Where(clause = "deleted=false")
@Getter
@Setter
@Entity

public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    @Column(nullable = false)
    private String name;
    private Integer age;
    private Double weight;
    private String story;
    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>();
    private boolean alta;

}
