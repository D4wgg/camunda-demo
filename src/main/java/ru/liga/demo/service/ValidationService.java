package ru.liga.demo.service;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Named
@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService extends AbstractDelegateService {

    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";

    @Override
    public void run(DelegateExecution delegateExecution) {
        var inputEmail = (String) delegateExecution.getVariable("email_validation_request");
        var isEmailAddressValid = Pattern.compile(EMAIL_REGEX)
                .matcher(inputEmail)
                .matches();

        log.warn("mail {} {} valid", inputEmail, isEmailAddressValid ? "IS" : "NOT");
        delegateExecution.setVariable("result", isEmailAddressValid);
    }
}
