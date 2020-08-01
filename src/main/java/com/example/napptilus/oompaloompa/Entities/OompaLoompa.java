package com.example.napptilus.oompaloompa.Entities;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Positive;


@Entity
@Table(name = "tbl_oompa_loompa")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class OompaLoompa extends RepresentationModel<OompaLoompa>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Positive(message="It can't be negative")
    private Integer age;

    private String job;


    @Positive(message="It can't be negative")
    private Float height;


    @Positive(message="It can't be negative")
    private Float weight;

    private String description;


    public OompaLoompa(Long id, String name){
        this.id = id;
        this.name = name;

    }

}
