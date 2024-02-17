package io.hexa24.yaksok.location.service;

import org.springframework.stereotype.Service;

import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    
    private final LocationRepository locationRepository;

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
