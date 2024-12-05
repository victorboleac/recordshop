package com.northcoders.recordshop.repository;

import com.northcoders.recordshop.model.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityManagerRepository extends CrudRepository <Availability, Long> {
}
