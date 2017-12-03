package pl.tu.kielce.pizza.be.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.common.jpa.Address;
import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.be.order.model.jpa.DeliveryUserData;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;
import pl.tu.kielce.pizza.common.order.dto.DeliveryUserDataDto;

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
//                .phoneNumber(entity.getPhoneNumber())
//                .email(entity.getEmail())
//                .name(entity.getName())
//                .lastName(entity.getLastName())
                .build();
    }

    public Address addressDtoToEntity(AddressDto dto) {

        return Address
                .builder()
                .city(dto.getCity())
                .houseNumber(dto.getHouseNumber())
                .street(dto.getStreet())
                .flatNumber(dto.getFlatNumber())
//                .phoneNumber(dto.getPhoneNumber())
//                .email(dto.getEmail())
//                .name(dto.getName())
//                .lastName(dto.getLastName())
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

    public DeliveryUserDataDto mapDeliveryUserDataToDto(DeliveryUserData deliveryUserData) {

        return DeliveryUserDataDto
                .builder()
                .id(deliveryUserData.getDeliveryUserDataId())
                .name(deliveryUserData.getName())
                .lastName(deliveryUserData.getLastName())
                .phoneNumber(deliveryUserData.getPhoneNumber())
                .email(deliveryUserData.getEmail())
                .build();

    }

    public DeliveryUserData mapDeliveryUserDataToEntity(DeliveryUserDataDto deliveryUserDataDto) {
        return DeliveryUserData
                .builder()
                .deliveryUserDataId(deliveryUserDataDto.getId())
                .name(deliveryUserDataDto.getName())
                .lastName(deliveryUserDataDto.getLastName())
                .phoneNumber(deliveryUserDataDto.getPhoneNumber())
                .email(deliveryUserDataDto.getEmail())
                .build();

    }
}
