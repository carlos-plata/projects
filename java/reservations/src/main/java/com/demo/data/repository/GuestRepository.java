package com.demo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.data.entity.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<com.demo.data.entity.Guest, Long> {

}