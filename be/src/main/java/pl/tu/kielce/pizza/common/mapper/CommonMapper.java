package pl.tu.kielce.pizza.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.model.jpa.Address;

@Component
@RequiredArgsConstructor
public class CommonMapper {

    public AddressDto addressEntityToDto(Address entity) {

        return AddressDto
                .builder()
                .city(entity.getCity())
                .houseNumber(entity.getHouseNumber())
                .street(entity.getStreet())
                .flatNumber(entity.getFlatNumber())
                .build();
    }

    public Address addressDtoToEntity(AddressDto dto) {

        return Address
                .builder()
                .city(dto.getCity())
                .houseNumber(dto.getHouseNumber())
                .street(dto.getStreet())
                .flatNumber(dto.getFlatNumber())
                .build();
    }
}
