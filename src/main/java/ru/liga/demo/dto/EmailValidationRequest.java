package ru.liga.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailValidationRequest(@NotBlank String email) {
}
