package io.hexa24.yaksok.gathering.repository;

import org.springframework.stereotype.Repository;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface GatheringRepository extends CrudRepository<Gathering, UUID>{
    
}
