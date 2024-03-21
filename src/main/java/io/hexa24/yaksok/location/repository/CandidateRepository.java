package io.hexa24.yaksok.location.repository;

import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.location.domain.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate,Long> {

    List<Candidate> findByGatheringId(UUID gatheringId);

    List<Candidate> findByGatheringIdAndMemberId(UUID gatheringId, Long memberId);
}
