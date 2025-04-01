package com.larry.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InboundLinkDTO {
    @NotNull
    private Long collectionRdrId;
    private String linkId;
    @NotEmpty
    private String linkRecipientEmail;
    @NotEmpty
    private String subject;
}
