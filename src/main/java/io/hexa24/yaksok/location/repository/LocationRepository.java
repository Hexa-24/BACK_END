package io.hexa24.yaksok.location.repository;

import java.util.Optional;

import io.hexa24.yaksok.location.domain.value.Address;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.hexa24.yaksok.location.domain.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location,Long> {
    Optional<Location> findByNameAndAddressAndCoordinate(String name, Address address, Coordinate coordinate);
}
