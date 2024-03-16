package io.hexa24.yaksok.location.domain.value;

import jakarta.persistence.Embeddable;
import org.springframework.data.geo.Point;

@Embeddable
public class Coordinate extends Point {

    public Coordinate() {
        super(0, 0);
    }
    public Coordinate(double x, double y) {
        super(x, y);
    }

    public Coordinate(Point point) {
        super(point);
    }

}
