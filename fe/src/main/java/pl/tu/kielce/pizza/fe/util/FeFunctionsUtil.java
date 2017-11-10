package pl.tu.kielce.pizza.fe.util;

import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;

import java.util.function.Function;

public class FeFunctionsUtil {

    public static <DTO extends AuditableEntityDto> Long doSave(
            DTO dto,
            Function<DTO, DTO> saveFunction) {

        return saveFunction
                .andThen(AuditableEntityDto::getId)
                .apply(dto);

    }

    public static <DTO extends AuditableEntityDto> Long doSave(
            DTO dto,
            Function<DTO, DTO> saveFunction,
            Function<DTO, Long> getId) {

        return saveFunction
                .andThen(getId)
                .apply(dto);

    }
}
