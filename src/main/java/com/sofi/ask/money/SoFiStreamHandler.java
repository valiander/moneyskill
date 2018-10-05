package com.sofi.ask.money;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.sofi.ask.money.handlers.CancelAndStopIntentHandler;
import com.sofi.ask.money.handlers.CheckMyBalanceIntentHandler;
import com.sofi.ask.money.handlers.HelloWorldIntentHandler;
import com.sofi.ask.money.handlers.HelpIntentHandler;
import com.sofi.ask.money.handlers.SendMoneyIntentHandler;
import com.sofi.ask.money.handlers.LaunchRequestHandler;
import com.sofi.ask.money.handlers.SessionEndedRequestHandler;
import com.sofi.ask.money.handlers.GenericExceptionHandler;

public class SoFiStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntentHandler(),
                        new CheckMyBalanceIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new SendMoneyIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .addExceptionHandler(new GenericExceptionHandler())
                .withSkillId("amzn1.ask.skill.7c6da0be-ba5f-466a-8299-a05269083126") // marc's
                // .withSkillId("amzn1.ask.skill.2cd59bee-988a-45b4-89bd-a6725bfb280c") // josh's
                .build();
    }

    public SoFiStreamHandler() {
        super(getSkill());
    }

}