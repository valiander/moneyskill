package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to " + Constants.SOFI_NAME + ". Taking control of your financial future";
        String repromptText = "Please say a command or say help to hear available options";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(Constants.SOFI_NAME, speechText)
                .withReprompt(repromptText)
                .build();
    }

}