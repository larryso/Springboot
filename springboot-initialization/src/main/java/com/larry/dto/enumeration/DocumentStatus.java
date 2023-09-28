package com.larry.dto.enumeration;

public enum DocumentStatus {
    DOCUMENT_PENDING(1L),
    DOCUMENT_COMPLETED(2L),
    DOCUMENT_ERROR(5L),
    DRAFT(10L),
    SCANNED(12L);

    private final Long id;

    DocumentStatus(Long id){
        this.id = id;
    }

    public static DocumentStatus getById(Long id){
        DocumentStatus[] values = values();
        for(DocumentStatus status:values){
            if(status.id == id){
                return status;
            }
        }
        return null;
    }
}
