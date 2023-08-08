package com.larry.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
