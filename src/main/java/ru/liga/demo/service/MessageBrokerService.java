package ru.liga.demo.service;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;
import ru.liga.demo.kafka.KafkaProducer;

@Named
@Service
@RequiredArgsConstructor
public class MessageBrokerService extends AbstractDelegateService {

    private final KafkaProducer kafkaProducer;

    @Override
    public void run(DelegateExecution delegateExecution) {
        var status = (String) delegateExecution.getVariable("status");

        kafkaProducer.send("camunda-demo", status);
    }
}
