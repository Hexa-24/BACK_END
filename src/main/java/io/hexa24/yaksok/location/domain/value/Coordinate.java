package io.hexa24.yaksok.location.domain.value;

import io.hexa24.yaksok.location.domain.dto.CoordinateDTO;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@ToString
@NoArgsConstructor
public class Coordinate {

    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CoordinateDTO toDTO() {
        return new CoordinateDTO(this.x,this.y);
    }
}
