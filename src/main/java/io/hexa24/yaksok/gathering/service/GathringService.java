package io.hexa24.yaksok.gathering.service;

import java.net.URI;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GathringService {
    
        private final GatheringRepository gatheringRepository;

        @SuppressWarnings("@PathVariable doesn't accept Null value")
        public Gathering findGathering(UUID id) {
            return gatheringRepository.findById(id).orElseThrow(RuntimeException::new);
        }

        public Gathering addGathering(Gathering gathering) {
            return gatheringRepository.save(gathering);
        }

        public void modifyGathering(Gathering gathering) {

            if(gatheringRepository.findById(gathering.getId()) != null){
                gatheringRepository.save(gathering);
            }else{
                throw new RuntimeException("gathring의 ID가 틀렸습니다.")
            }
            
        }
        
        

}
