package com.leodelmiro.estabelecimento.entrypoint.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record PaymentRequest(
        Long id,
        @JsonProperty("live_mode")
        Boolean liveMode,
        String type,
        @JsonProperty("date_created")
        OffsetDateTime createdAt,
        @JsonProperty("user_id")
        Long userId,
        @JsonProperty("api_version")
        String apiVersion,
        String action,
        Data data
) {
    public record Data(String id) {
    }
}