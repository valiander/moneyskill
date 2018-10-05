package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class HelloWorldIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("PresentationIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "That presentation was <say-as interpret-as=\"expletive\">ask</say-as>ing <emphasis level=\"strong\">amazing</emphasis>.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(Constants.SOFI_NAME, speechText)
                .build();
    }

}