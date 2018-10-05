package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.sofi.ask.money.constants.Constants;

import java.util.Optional;

public class ProductInfoIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("ProductInfoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        intentRequest.getDialogState();
        Intent intent = intentRequest.getIntent();

        String speechText = Constants.SOFI_NAME + " offers personal loans, student loans, mortgages, and an online only bank that isn't actually a bank";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(Constants.SOFI_NAME, speechText)
                .build();
    }
}