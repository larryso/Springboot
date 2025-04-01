package com.larry.framework.pipeline;

public interface Pipeline< T extends Context, E extends Enum>{
    void addStage(Stage<T, E> stage);
    void start(E command, T context);
}
