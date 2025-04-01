package com.larry.framework.pipeline;

public class PipelineException extends Exception{
    public PipelineException(Throwable cause) {
        super(cause);
    }

    public PipelineException(String message) {
        super(message);
    }

    public PipelineException() {
        super();
    }

    public PipelineException(String message, Throwable cause){
        super(message, cause);
    }
}
