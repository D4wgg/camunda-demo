package ru.liga.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.demo.dto.EmailValidationRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemoController {

    private final RuntimeService runtimeService;

    @PostMapping("/validate")
    public ResponseEntity<Void> validateMessage(@RequestBody @Valid EmailValidationRequest request) {
        var variables = Variables.createVariables().putValue("email_validation_request", request.email());
        runtimeService.startProcessInstanceByKey("camunda-project-process", variables);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
