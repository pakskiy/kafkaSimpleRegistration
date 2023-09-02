package com.pakskiy.kafkasimpleregistration.rest;

import com.pakskiy.kafkasimpleregistration.dto.RegisterRequestDto;
import com.pakskiy.kafkasimpleregistration.dto.RegisterResponseDto;
import com.pakskiy.kafkasimpleregistration.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class RegistrationRestControllerV1 {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto request) {
        return ResponseEntity.ok(registrationService.register(request));
    }
}
