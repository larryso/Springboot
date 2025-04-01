package com.larry.framework.pipeline;

public interface Process<T extends Context, E extends Enum> {
    ProcessType getProcessType();
    void startFrom(E command, T context);
    void startFrom(String command, T context);
    void start(ProcessType processType, T context);
}
