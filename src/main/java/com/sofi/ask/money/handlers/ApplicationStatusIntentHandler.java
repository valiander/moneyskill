package com.sofi.ask.money.handlers;

import com.sofi.ask.money.constants.Constants;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class ApplicationStatusIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("ApplicationStatusIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest)request;
        intentRequest.getDialogState();

        StringBuilder speechText = new StringBuilder();
        speechText.append("You have two applications. ");
        speechText.append("Your Student Loan application is missing some documents. Please login to ")
        .append(Constants.SOFI_NAME)
        .append(" to add the missing documents. ")
        .append("Your personal loan application is in funding. You can expect your funds to be available in one to three days");
        return input.getResponseBuilder()
                    .withSpeech(speechText.toString())
                    .withSimpleCard(Constants.SOFI_NAME, speechText.toString())
                    .build();
    }
}