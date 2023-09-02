package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class PaymentState {
    @JsonProperty("id")
    Long id;
    @JsonProperty("order_id")
    Long orderId;
    @JsonProperty("live_mode")
    Boolean liveMode;
    @JsonProperty("type")
    String type;
    @JsonProperty("date_created")
    String dateCreated; //TODO: mudar tipo da data
    @JsonProperty("user_id")
    Long userId;
    @JsonProperty("api_version")
    String apiVersion;
    @JsonProperty("action")
    String action;
    @JsonProperty("data")
    List<Map<String,String>> data;

}
