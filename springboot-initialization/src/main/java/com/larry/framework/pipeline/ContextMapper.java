package com.larry.framework.pipeline;

public interface ContextMapper {
    ProcessType getProcessType();
    String mapToJson(Context context);
    Context mapToContext(String json);
}
