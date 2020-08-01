package com.example.napptilus.oompaloompa.Repository;

import com.example.napptilus.oompaloompa.Entities.OompaLoompa;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface OompaLoompaRepository  extends PagingAndSortingRepository<OompaLoompa, Long> {

    Optional<OompaLoompa> findByName(String name);

    Page<OompaLoompa> findAll(Pageable page);



}
