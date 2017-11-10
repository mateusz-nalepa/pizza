package pl.tu.kielce.pizza.be.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.common.jpa.Address;
import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;

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

    public <ENTITY extends AuditableEntity, DTO extends AuditableEntityDto> DTO baseEntityToDto(ENTITY entity, DTO dto) {

        dto.setActive(entity.isActive());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());

        return dto;
    }
}
