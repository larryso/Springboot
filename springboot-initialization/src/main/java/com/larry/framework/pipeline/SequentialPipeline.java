package com.larry.framework.pipeline;

import com.larry.framework.pipeline.service.ProcessPipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Slf4j
@Component
public abstract class SequentialPipeline< T extends Context, E extends Enum> implements Pipeline<T, E> {
    private List<Stage<T,E>> stages = new LinkedList();
    @Autowired
    private ProcessPipelineService processPipelineService;

    @Override
    public void addStage(Stage<T, E> stage) {
        stages.add(stage);
    }

    @Override
    public void start(E command, T context) {
        try{
            int index = getStartingPoint(command);
            for(int i = index; i< stages.size(); i++){
                Stage<T, E> stage = stages.get(i);
                try{
                    E startCommand = stage.getCommand();
                    //processPipelineService
                }catch (Exception e){
                    log.error("Common exception when execute Command {}");
                }
            }
        }finally {
            log.info("Finalized");
        }
    }
    private int getStartingPoint(E command) {
        int index = 0;
        for (Stage<T, E> stage : stages) {
            if (command.equals(stage.getCommand())) {
                index = stages.indexOf(stage);
                break;
            }
        }
        return index;
    }
}
