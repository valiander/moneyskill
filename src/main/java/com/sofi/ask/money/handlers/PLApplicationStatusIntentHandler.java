package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;

import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;
import static com.sofi.ask.money.constants.Constants.SOFI_NAME;

public class PLApplicationStatusIntentHandler implements RequestHandler {
    public static final String PLAPP_KEY = "plapp";
    public static final String PLAPP_SLOT = "plapp_slot";

        @Override
        public boolean canHandle(HandlerInput input) {
            return input.matches(intentName("PLApplicationStatus"));
        }

        @Override
        public Optional<Response> handle(HandlerInput input) {
            Request request = input.getRequestEnvelope().getRequest();
            IntentRequest intentRequest = (IntentRequest) request;
            Intent intent = intentRequest.getIntent();
            Map<String, Slot> slots = intent.getSlots();

            // Get the color slot from the list of slots.
            Slot plAppId = slots.get(PLAPP_SLOT);

            String speechText = "";

            boolean isAskResponse = false;

            // Check for application id and create output to user.
            if (plAppId != null) {
                // Store the user's app id in the Session and create response.
                String appIdValue = plAppId.getValue();
                input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PLAPP_KEY, appIdValue));

                speechText =
                    String.format("Your Personal Loan application %s is Ready for Signature. Please log in to your account to sign your documents so you can receive your funds", appIdValue);

            } else {
                // Render an error since we don't know what the users pl account number is.
                speechText = "I'm not sure what account you are looking for, please try again";
                isAskResponse = true;
            }

            ResponseBuilder responseBuilder = input.getResponseBuilder();

            responseBuilder.withSimpleCard(SOFI_NAME, speechText)
                           .withSpeech(speechText)
                           .withShouldEndSession(true);

            if (isAskResponse) {
                responseBuilder.withShouldEndSession(false)
                               .withReprompt(speechText);
            }

            return responseBuilder.build();
        }

    }