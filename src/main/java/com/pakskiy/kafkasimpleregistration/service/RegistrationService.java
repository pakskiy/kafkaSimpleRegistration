package com.pakskiy.kafkasimpleregistration.service;

import com.pakskiy.kafkasimpleregistration.dto.RegisterRequestDto;
import com.pakskiy.kafkasimpleregistration.dto.RegisterResponseDto;

public interface RegistrationService {
    RegisterResponseDto register(RegisterRequestDto registerRequestDto);
}
