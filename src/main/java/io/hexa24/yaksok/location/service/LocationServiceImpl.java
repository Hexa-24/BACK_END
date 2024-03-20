package io.hexa24.yaksok.location.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.hexa24.yaksok.location.domain.dto.LocationReqDTO;
import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.location.repository.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {
    
    private final LocationRepository locationRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public List<Location> findLocationsByGatheringId(UUID gatheringId) {
        //return locationRepository.findByGatheringId(gatheringId);
        return null;
    }

    @Override
    public List<Location> findLocationsByGatheringIdAndMemberId(UUID gatheringId, Long memberId) {
        //return locationRepository.findByGatheringIdAndMemberId(gatheringId, memberId);
        return null;
    }
    
    @Override
    public Location findLocation(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(RuntimeException::new);
    }

    @Override
    public Location addLocation(UUID gatheringId, Long memberId, LocationReqDTO locationReqDTO) {
        Optional<Location> location = locationRepository.findByNameAndAddressAndCoordinate(locationReqDTO.getName(), locationReqDTO.getAddress().toValue(), locationReqDTO.getCoordinate().toValue());

        Location result;

        if(location.isPresent()){
            // 기존 저장된 Location 정보가 있을 경우
            log.debug("@@@@@@@@@@@@@@@@@@@ IF");
            Candidate candidate = Candidate.builder()
                    .gatheringId(gatheringId)
                    .memberId(memberId)
                    .location(location.get())
                    .build();

            Candidate save = candidateRepository.save(candidate);
            result =  save.getLocation();
        }else{
            // 기존 저장된 Location 정보가 없을 경우
            log.debug("@@@@@@@@@@@@@@@@@@@ ELSE");

            Candidate candidate = Candidate.builder()
                                        .gatheringId(gatheringId)
                                        .memberId(memberId)
                                        .build();
            Candidate candidateSaved = candidateRepository.save(candidate);

            Location location1 = Location.builder()
                                        .name(locationReqDTO.getName())
                                        .address(locationReqDTO.getAddress().toValue())
                                        .coordinate(locationReqDTO.getCoordinate().toValue())
                                        .build();
            location1.getCandidates().add(candidateSaved);

            result = locationRepository.save(location1);
        }
        return result;
    }

    @Override
    public void modifyLocation(Location location) {
        if(locationRepository.findById(location.getId()) != null){
            locationRepository.save(location);
        }else{
            throw new RuntimeException("location의 ID가 틀렸습니다.");
        }
    }

    @Override
    public void removeLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }

   

}
