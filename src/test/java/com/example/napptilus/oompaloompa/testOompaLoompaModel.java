package com.example.napptilus.oompaloompa;

import com.example.napptilus.oompaloompa.Entities.OompaLoompa;
import com.example.napptilus.oompaloompa.Repository.OompaLoompaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class testOompaLoompaModel {





    @Autowired
    OompaLoompaRepository  oompaLoompaRepository;




    @Test
    public void testCreateOompaLoompa(){

        Optional<OompaLoompa> ompaOpt = oompaLoompaRepository.findByName("OMPA");
        Assertions.assertTrue(!ompaOpt.isPresent());

        OompaLoompa ompa = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA")
                .weight(60.0f)
                .build();
        oompaLoompaRepository.save(ompa);
        ompaOpt = oompaLoompaRepository.findByName("OMPA");
        Assertions.assertTrue(ompaOpt.isPresent());
        OompaLoompa savedOompa = ompaOpt.get();
        assertThat(ompa).isEqualToComparingFieldByField(savedOompa);

    }

    @Test
    public void testUpdateOompaLoompa(){
        OompaLoompa ompa = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA")
                .weight(60.0f)
                .build();
        oompaLoompaRepository.save(ompa);
        Optional<OompaLoompa> ompaOpt = oompaLoompaRepository.findByName("OMPA");
        Assertions.assertTrue(ompaOpt.isPresent());
        OompaLoompa oompa = ompaOpt.get();
        oompa.setName("Hello");
        oompa.setAge(50);
        oompa.setWeight(2.2f);
        oompa.setHeight(5.5f);
        oompa.setDescription("Description");
        oompaLoompaRepository.save(oompa);
        OompaLoompa oompaUpd = oompaLoompaRepository.findByName("Hello").get();
        Assertions.assertEquals(oompa.getName(),oompaUpd.getName());
        Assertions.assertEquals(oompa.getAge(),oompaUpd.getAge());
        Assertions.assertEquals(oompa.getWeight(),oompaUpd.getWeight());
        Assertions.assertEquals(oompa.getHeight(),oompaUpd.getHeight());
        Assertions.assertEquals(oompa.getDescription(),oompaUpd.getDescription());
    }


    @Test
    public void testListAllOompaLoompa(){
        OompaLoompa ompa = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA1")
                .weight(60.0f)
                .build();
        OompaLoompa ompa2 = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA2")
                .weight(60.0f)
                .build();

        oompaLoompaRepository.save(ompa);
        oompaLoompaRepository.save(ompa2);
        Pageable paging = PageRequest.of(1, 10, Sort.by("id"));
        Page<OompaLoompa> loompas= oompaLoompaRepository.findAll(paging);
        Assertions.assertEquals(2,loompas.getTotalElements());
    }


    @Test
    public void testListAllCustomOompaLoompa(){
        Pageable paging = PageRequest.of(1, 10, Sort.by("name"));
        OompaLoompa ompa = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA1")
                .weight(60.0f)
                .build();
        OompaLoompa ompa2 = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA2")
                .weight(60.0f)
                .build();

        oompaLoompaRepository.save(ompa);
        oompaLoompaRepository.save(ompa2);

        Page<OompaLoompa> loompas = oompaLoompaRepository.findAll(paging);
        Assertions.assertEquals(loompas.getTotalElements(),2);
    }

}
