package io.hexa24.yaksok.location.domain.entity;

import io.hexa24.yaksok.location.domain.value.Address;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Candidate> candidates = new ArrayList<>();

    private String name;

    @Embedded
    private Address address;

    @Embedded
    private Coordinate coordinate;

}
