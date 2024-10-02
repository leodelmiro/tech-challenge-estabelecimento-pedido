package com.leodelmiro.estabelecimento.dataprovider.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QrCodeResponse(
        @JsonProperty("in_store_order_id") String inStoreOrderId,
        @JsonProperty("qr_data") String qrData
) {
}
