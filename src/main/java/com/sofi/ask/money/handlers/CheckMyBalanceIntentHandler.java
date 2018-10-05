package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class CheckMyBalanceIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("CheckMyBalanceIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Your balance could be great. But probably not.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(Constants.SOFI_NAME, speechText)
                .build();
    }

}