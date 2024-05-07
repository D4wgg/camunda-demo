package ru.liga.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public abstract class AbstractDelegateService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            log.warn("Delegate {} was called for process {}",
                    getClass().getSimpleName(),
                    delegateExecution.getProcessInstanceId());
            run(delegateExecution);
        } catch (Exception exception) {
            log.error("Error occurred", exception);
            executeHandling(exception);
        }
    }

    public void executeHandling(Exception exception) {
        throw new BpmnError("PROCESS_ERROR", exception);
    }

    public abstract void run(DelegateExecution delegateExecution);
}
