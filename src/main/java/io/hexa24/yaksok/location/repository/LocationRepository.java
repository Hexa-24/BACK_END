package io.hexa24.yaksok.location.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.hexa24.yaksok.location.domain.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location,Long> {

    public List<Location> findByGatheringId(UUID gatheringId);
    public List<Location> findByGatheringIdAndMemberId(UUID gatheringId, Long memberId);

}
