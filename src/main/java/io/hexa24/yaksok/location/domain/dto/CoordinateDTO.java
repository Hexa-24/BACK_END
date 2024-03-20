package io.hexa24.yaksok.location.domain.dto;

import io.hexa24.yaksok.location.domain.value.Coordinate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoordinateDTO {

    private double x;
    private double y;

    public CoordinateDTO(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate toValue(){
        return new Coordinate(this.getX(),this.getY());
    }
}



