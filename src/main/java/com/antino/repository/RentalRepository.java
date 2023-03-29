package com.antino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antino.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
