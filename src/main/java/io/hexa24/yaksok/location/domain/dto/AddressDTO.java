package io.hexa24.yaksok.location.domain.dto;

import io.hexa24.yaksok.location.domain.value.Address;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    String zipcode;
    String address1;
    String address2;

    public Address toValue() {
        return Address.builder()
                .zipcode(this.zipcode)
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }
}