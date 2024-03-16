package io.hexa24.yaksok.location.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    
    private final LocationRepository locationRepository;

    
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
    public Location addLocation(Location location) {
        return locationRepository.save(location);
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
