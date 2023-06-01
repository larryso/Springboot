package com.larry.audit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuditAction {
    UPLOAD_DOCUMENT("New Document uploaded"),
    DEL_DOCUMENT("Delete Document");

    private final String text;
}
