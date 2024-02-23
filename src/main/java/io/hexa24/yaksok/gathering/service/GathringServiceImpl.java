package io.hexa24.yaksok.gathering.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GathringServiceImpl implements GatheringService {
    
        private final GatheringRepository gatheringRepository;
        
        @Override
        @SuppressWarnings("@PathVariable doesn't accept Null value")
        public Gathering findGathering(UUID id) {
            return gatheringRepository.findById(id).orElseThrow(RuntimeException::new);
        }
        @Override
        public Gathering addGathering(Gathering gathering) {
            return gatheringRepository.save(gathering);
        }
        @Override
        public void modifyGathering(Gathering gathering) {
            if(gatheringRepository.findById(gathering.getId()) != null){
                gatheringRepository.save(gathering);
            }else{
                throw new RuntimeException("gathring의 ID가 틀렸습니다.");
            }
        }
        @Override
        public void removeGathering(UUID id) {
            gatheringRepository.deleteById(id);
        }
        
        

}
