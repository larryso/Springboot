package com.larry.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.larry.dto.enumeration.DocumentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {
    @JsonIgnore
    private Long documentPK;
    @JsonProperty("eboid")
    private String EBOID;
    private String fileName;
    private Set<AdditionalAttributeDTO> additionalAttributes;
    private DocumentStatus documentStatus;
    private Boolean deleted;
    private Date uploadDate;
    private StorageDTO storage;

}
