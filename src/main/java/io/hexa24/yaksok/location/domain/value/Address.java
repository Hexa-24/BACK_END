package io.hexa24.yaksok.location.domain.value;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    String zipCode;
    String address1;
    String address2;
    
}
