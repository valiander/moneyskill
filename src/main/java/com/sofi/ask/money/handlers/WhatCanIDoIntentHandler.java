package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class WhatCanIDoIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("WhatCanIDoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "You can ask for things like check my balance or send 10 dollars to Mike. You can also say pay 150 dollars to Verizon. If you want to know about events, you can ask about nearby events. ";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("SoFi", speechText)
                .build();
    }

}