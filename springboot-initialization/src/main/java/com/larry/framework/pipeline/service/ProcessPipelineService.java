package com.larry.framework.pipeline.service;

import com.larry.framework.pipeline.Context;
import com.larry.framework.pipeline.ProcessType;
import com.larry.framework.pipeline.persistence.domain.ProcessPipeline;
import com.larry.framework.pipeline.persistence.refdata.ProcessPipelineStatus;

import java.util.Optional;

public interface ProcessPipelineService {
    <T extends Context> T loadContext(T context);

    <T extends Context> void updateContext(T context);

    <E extends Enum, T extends Context> void updateCommand(E command, T context);

    void failed(Long processId, String exceptionMessage);

    void updateStatus(Long processId, ProcessPipelineStatus status);

    <T extends Context> ProcessPipeline register(ProcessType processType, T context);

    <T extends Context> void start(T context);

    <T extends Context> void finish(T context);

    Optional<ProcessPipeline> getForRecovery();

    boolean recover(Long processId);
}
