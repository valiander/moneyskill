package com.sofi.ask.money.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;


public class PLApplicationStatusIntentHandler implements RequestHandler {
    public static final String COLOR_KEY = "color";
    public static final String COLOR_SLOT = "color_slot";

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
            Slot favoriteColorSlot = slots.get(COLOR_SLOT);

            String speechText, repromptText;
            boolean isAskResponse = false;

            // Check for favorite color and create output to user.
            if (favoriteColorSlot != null) {
                // Store the user's favorite color in the Session and create response.
                String favoriteColor = favoriteColorSlot.getValue();
                input.getAttributesManager().setSessionAttributes(Collections.singletonMap(COLOR_KEY, favoriteColor));

                speechText =
                    String.format("I now know that your favorite color is %s. You can ask me your "
                                  + "favorite color by saying, what's my favorite color?", favoriteColor);
                repromptText =
                    "You can ask me your favorite color by saying, what's my favorite color?";

            } else {
                // Render an error since we don't know what the users favorite color is.
                speechText = "I'm not sure what your favorite color is, please try again";
                repromptText =
                    "I'm not sure what your favorite color is. You can tell me your favorite "
                    + "color by saying, my color is red";
                isAskResponse = true;
            }

            ResponseBuilder responseBuilder = input.getResponseBuilder();

            responseBuilder.withSimpleCard("ColorSession", speechText)
                           .withSpeech(speechText)
                           .withShouldEndSession(false);

            if (isAskResponse) {
                responseBuilder.withShouldEndSession(false)
                               .withReprompt(repromptText);
            }

            return responseBuilder.build();
        }

    }