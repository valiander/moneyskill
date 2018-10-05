package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
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
        String speechText = "<audio src=\"http://www.moviewavs.com/php/sounds/?id=gog&media=MP3S&type=Movies&movie=Jerry_Maguire&quote=money2.txt&file=money2.mp3\" />";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("SoFi", speechText)
                .build();
    }

}