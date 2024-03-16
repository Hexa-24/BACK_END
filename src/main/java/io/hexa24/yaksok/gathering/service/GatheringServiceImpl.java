package io.hexa24.yaksok.gathering.service;

import java.util.UUID;

import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {
    
        private final GatheringRepository gatheringRepository;
        
        @Override
        public GatheringRespDTO findGathering(UUID id) {
            Gathering gathering = gatheringRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            // Entity(Gathering)를 DTO(GatheringReqDTO) 로 변환 후 반환
            return GatheringRespDTO.toRespDTO(gathering);
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
