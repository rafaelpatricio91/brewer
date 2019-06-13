package com.rafa.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafa.brewer.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long> 
{}
