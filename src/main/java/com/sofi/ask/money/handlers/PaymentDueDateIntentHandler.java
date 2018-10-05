package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Map;
import java.util.Optional;

public class PaymentDueDateIntentHandler implements RequestHandler {

    public static final String PRODUCT_SLOT = "product";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("PaymentDueDateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        System.out.println(((IntentRequest)input.getRequest()).getDialogState());
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot productSlot = slots.get(PRODUCT_SLOT);

        String product = null;
        if (productSlot != null && productSlot.getValue() != null) {
            product = productSlot.getValue();
        }

        System.out.println("product is " + product);

        if (product == null) {
            return input.getResponseBuilder()
                    .addDelegateDirective(null)
                    .build();
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if (product == null) {
            String speechText = "Which loan would you like to check your due date for?";
            responseBuilder
                    .withSpeech(speechText)
                    .withReprompt(speechText)
                    .withShouldEndSession(false);
        } else {
            String speechText = "Your next due date for your " + product + " loan is on October 6th.";
            responseBuilder.withSpeech(speechText)
                    .withShouldEndSession(true);
        }

        return responseBuilder.build();
    }
}
