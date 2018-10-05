package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
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
//        return input.getResponseBuilder()
//                .withSpeech("Ok.")
//                .withSimpleCard("SendMoney", "Send money to other SoFi members")
//                .withReprompt("How much would you like to send?")
//                .build();
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot personSlot = slots.get(PERSON_SLOT);
        Slot amountSlot = slots.get(AMOUNT_SLOT);

        String speechText, repromptText;
        boolean isAskResponse = false;

        if (personSlot != null && amountSlot != null) {
            String person = personSlot.getValue();
            String amount = amountSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PERSON_KEY, (Object)person));
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(AMOUNT_KEY, (Object)amount));

            speechText = "Ok, I just sent " + amount + " to " + person + ".";
            repromptText = null;
        } else if (personSlot != null) {
            String person = personSlot.getValue();
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PERSON_KEY, (Object)person));

            speechText = "Alright";
            repromptText = "How much would you like to send to " + person + "?";
            isAskResponse = true;
        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = "I'm not sure I understand. Please try again by telling me who you want to send money to";
            repromptText = speechText;
            isAskResponse = true;
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("SoFi", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();
    }
}
