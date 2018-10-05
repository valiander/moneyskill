package com.sofi.ask.money.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "You can say things like check my balance or send money";
        return input.getResponseBuilder()
                .withSimpleCard("SoFi", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}