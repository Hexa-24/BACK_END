package io.hexa24.yaksok.location.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.hexa24.yaksok.location.domain.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location,Long> {}
