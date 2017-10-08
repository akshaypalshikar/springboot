package org.aks.demo.springboot.data.repository;

import org.aks.demo.springboot.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}