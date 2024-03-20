package io.hexa24.yaksok.location.domain.value;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    String zipcode;
    String address1;
    String address2;

}
