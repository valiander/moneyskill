package com.sofi.ask.money.handlers;

import com.sofi.ask.money.constants.Constants;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HowMuchMoneyIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("HowMuchMoneyIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "The current balance of your Money account is one thousand, two hundred and thirty-four dollars and eighteen cents";
        return input.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("SoFi", speechText)
                    .build();
    }
}
