package io.hexa24.yaksok.location.domain.value;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.data.geo.Point;

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

}
