package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class SendMoneyIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("SendMoneyIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sorry, Marc is dead. You can't send him money.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("SendMoney", speechText)
                .build();
    }
}
