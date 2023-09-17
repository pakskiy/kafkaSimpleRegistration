package com.pakskiy.kafkasimpleregistration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pakskiy.kafkasimpleregistration.controller.KafkaProducer;
import com.pakskiy.kafkasimpleregistration.dto.RegisterRequestDto;
import com.pakskiy.kafkasimpleregistration.dto.RegisterResponseDto;
import com.pakskiy.kafkasimpleregistration.exception.RegistrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final ObjectMapper objectMapper;
    private final KafkaProducer producer;
    @Value("${spring.kafka.producer.topic.name}")
    private String outboundTopic;

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {
        String sessionId = UUID.randomUUID().toString();
        sendEmail(request);
        try {
            return RegisterResponseDto.builder()
                    .sessionId(sessionId)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .build();
        } catch (Exception e) {
            log.error("Error saving user");
            throw new RegistrationException("Error saving user");
        }
    }

    private void sendEmail(RegisterRequestDto request) {
        try {
            String message = objectMapper.writeValueAsString(request);
            CompletableFuture<SendResult<String, String>> listenableFuture = producer.sendMessage(outboundTopic, "IN_KEY", String.format(message, 0));

            listenableFuture.thenAcceptAsync(result -> {
                log.info(String.format("INSERT MESSAGE: \ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d", result.getRecordMetadata().topic(),
                        result.getRecordMetadata().offset(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().serializedValueSize()));
            });
        } catch (Exception e) {
            log.error(e.toString(), "message: " + request.getEmail());
            throw new RegistrationException("Error sending email");
        }
    }
}
