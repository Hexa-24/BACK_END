package io.hexa24.yaksok.location.repository;

import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.location.domain.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate,Long> {

}
