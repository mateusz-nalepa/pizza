package pl.tu.kielce.pizza.be.common.util;

import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;

import java.util.function.Function;

public class BeFunctionsUtil {
    public static  <DTO extends AuditableEntityDto, ENTITY extends AuditableEntity> DTO doRead(
            Long id,
            Function<Long, ENTITY> findFunction,
            Function<ENTITY, DTO> entityToDto) {

        return findFunction
                .andThen(entityToDto)
                .apply(id);
    }

    public static  <DTO extends AuditableEntityDto, ENTITY extends AuditableEntity> DTO doSave(
            DTO dto,
            Function<DTO, ENTITY> dtoToEntity,
            Function<ENTITY, ENTITY> saveFunction,
            Function<ENTITY, DTO> entityToDto) {
        ENTITY department = dtoToEntity.apply(dto);
        department = saveFunction.apply(department);
        return entityToDto.apply(department);
    }
}
