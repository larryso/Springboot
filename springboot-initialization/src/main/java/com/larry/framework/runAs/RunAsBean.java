package com.larry.framework.runAs;

/**
 * RunAsBean allows to run a process under a specific user account.
 */
public interface RunAsBean {
    <T> T execute(RunAsAction<T> action);
}
