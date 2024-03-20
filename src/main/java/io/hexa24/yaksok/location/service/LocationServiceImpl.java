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
        //LocationReqDTO 이용해 해당 정보를 갖고 있는 Location이 있는지 확인
        Optional<Location> locationValid = locationRepository.findByNameAndAddressAndCoordinate(locationReqDTO.getName(), locationReqDTO.getAddress().toValue(), locationReqDTO.getCoordinate().toValue());

        Location result;

        if(locationValid.isPresent()){
            log.debug("// 기존 저장된 Location 정보가 있을 경우");
            // 기존 저장된 Location 정보가 있을 경우
            Candidate candidate = Candidate.builder()
                                            .gatheringId(gatheringId)
                                            .memberId(memberId)
                                            .location(locationValid.get())
                                            .build();
            Candidate candidateSaved = candidateRepository.save(candidate);
            result =  candidateSaved.getLocation();
        }else{
            log.debug(" // 기존 저장된 Location 정보가 없을 경우");
            // 기존 저장된 Location 정보가 없을 경우
            Location location = Location.builder()
                    .name(locationReqDTO.getName())
                    .address(locationReqDTO.getAddress().toValue())
                    .coordinate(locationReqDTO.getCoordinate().toValue())
                    .build();
            Location locationSaved = locationRepository.save(location);

            Candidate candidate = Candidate.builder()
                                        .gatheringId(gatheringId)
                                        .location(locationSaved)
                                        .memberId(memberId)
                                        .build();
            Candidate candidateSaved = candidateRepository.save(candidate);

            result = locationSaved;
        }
        return result;
    }

    @Override
    public void modifyLocation(Location location) {
        if(locationRepository.findById(location.getId()).isPresent()){
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
