package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.model.dialog.DelegateDirective;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
import com.sofi.ask.money.constants.Constants;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SendMoneyIntentHandler implements RequestHandler {

    public static final String PERSON_SLOT = "person";
    public static final String PERSON_KEY = "sendMoneyPerson";
    public static final String AMOUNT_SLOT = "amount";
    public static final String AMOUNT_KEY = "sendMoneyAmount";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("SendMoneyIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        if (((IntentRequest)input.getRequest()).getDialogState() != DialogState.COMPLETED) {
            return input.getResponseBuilder()
                    .addDelegateDirective(null)
                    .build();
        }
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot personSlot = slots.get(PERSON_SLOT);
        Slot amountSlot = slots.get(AMOUNT_SLOT);

        Map<String, Object> attribs = input.getAttributesManager().getSessionAttributes();

        String person = null;
        if (attribs.get(PERSON_KEY) != null) {
            person = attribs.get(PERSON_KEY).toString();
        }
        String amount = null;
        if (attribs.get(AMOUNT_KEY) != null) {
            amount = attribs.get(AMOUNT_KEY).toString();
        }

        if (personSlot != null && personSlot.getValue() != null) {
            person = personSlot.getValue();
            attribs.put(PERSON_KEY, person);
        }

        if (amountSlot != null && amountSlot.getValue() != null) {
            amount = amountSlot.getValue();
            attribs.put(AMOUNT_KEY, amount);
        }

        System.out.println("person is " + person);
        System.out.println("amount is " + amount);

        input.getAttributesManager().setSessionAttributes(attribs);

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if (person == null) {
            String sampleAmount = "money";
            if (amount != null) {
                sampleAmount = amount + " dollars";
            }
            String speechText = "Who would you like to send " + sampleAmount + " to?";
            responseBuilder
                    .withSpeech(speechText)
                    .withReprompt(speechText)
                    .withShouldEndSession(false);
        } else if (amount == null) {
            String samplePerson = "?";
            if (person != null) {
                samplePerson = " to " + person;
            }
            String speechText = "How much would you like to send" + samplePerson + "?";
            responseBuilder
                    .withSpeech(speechText)
                    .withReprompt(speechText)
                    .withShouldEndSession(false);
        } else {
            input.getAttributesManager().setSessionAttributes(Collections.emptyMap());
            String speechText = "Ok, I just sent " + amount + " dollars to " + person + ".";
            responseBuilder.withSpeech(speechText)
                    .withShouldEndSession(true);
        }

        return responseBuilder.build();
    }
}
