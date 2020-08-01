package com.example.napptilus.oompaloompa.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Positive;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortOompa  extends RepresentationModel<ShortOompa> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String job;

}
