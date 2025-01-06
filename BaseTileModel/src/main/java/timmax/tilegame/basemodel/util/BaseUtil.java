package timmax.tilegame.basemodel.util;

import timmax.tilegame.basemodel.credential.BaseEntity;
import timmax.tilegame.basemodel.dto.BaseDto;

public class BaseUtil {
    private BaseUtil() {
    }

    public static BaseDto createBaseDto(BaseEntity baseEntity) {
        return new BaseDto(baseEntity.getId());
    }

    public static boolean equals(BaseEntity baseEntity, BaseDto userDtoId) {
        return baseEntity.getId().equals(userDtoId.getId());
    }

    public static boolean equals(BaseDto userDtoId, BaseEntity baseEntity) {
        return equals(baseEntity, userDtoId);
    }
}
