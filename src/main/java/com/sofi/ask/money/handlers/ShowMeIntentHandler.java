package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.interfaces.audioplayer.PlayBehavior;
import com.amazon.ask.request.Predicates;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class ShowMeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("ShowMeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "<audio src='https://s3.amazonaws.com/sofialexasample/money2.proper.mp3' />";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("SoFi", speechText)
                .withShouldEndSession(true)
                .build();
    }

}