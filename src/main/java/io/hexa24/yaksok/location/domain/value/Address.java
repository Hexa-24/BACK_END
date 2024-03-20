package io.hexa24.yaksok.location.domain.value;

import io.hexa24.yaksok.location.domain.dto.AddressDTO;
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

    public AddressDTO toDTO() {
        return AddressDTO.builder()
                .zipcode(this.zipcode)
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }
}
