package com.larry.framework.pipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Stage<T extends Context, E extends Enum> {
    public abstract void execute(T context) throws Exception;
    public abstract E getCommand();
}
