package com.larry.framework.runAs;

public class RunAsBeanImpl implements RunAsBean{
    @Override
    public <T> T execute(RunAsAction<T> action) {
        //SecurityContextHolder.getContext().setAuthentication(thecAuth);
        T result = action.execute();
        //SecurityContextHolder.getContext().setAuthentication(userAuth);
        return result;
    }
}
