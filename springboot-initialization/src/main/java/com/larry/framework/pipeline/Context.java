package com.larry.framework.pipeline;

public interface Context {
    Long getProcessId();
    void setProcessId(Long processId);
    Error getError();
    void setError(Error error);
}
