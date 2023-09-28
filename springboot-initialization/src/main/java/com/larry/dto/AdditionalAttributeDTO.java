package com.larry.dto;

import com.larry.dto.enumeration.AttributeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
public class AdditionalAttributeDTO {
    @Schema(nullable=true)
    private String name;
    @Schema(nullable=true)
    private String value;
    @Schema(nullable=true)
    private AttributeType type;
}
