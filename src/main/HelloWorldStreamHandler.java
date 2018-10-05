package com.amazon.ask.helloworld;

 import com.amazon.ask.Skill;
 import com.amazon.ask.Skills;
 import com.amazon.ask.SkillStreamHandler;

 import com.amazon.ask.helloworld.handlers.CancelandStopIntentHandler;
 import com.amazon.ask.helloworld.handlers.HelloWorldIntentHandler;
 import com.amazon.ask.helloworld.handlers.HelpIntentHandler;
 import com.amazon.ask.helloworld.handlers.SessionEndedRequestHandler;
 import com.amazon.ask.helloworld.handlers.LaunchRequestHandler;

  public class HelloWorldStreamHandler extends SkillStreamHandler {

      private static Skill getSkill() {
          return Skills.standard()
                  .addRequestHandlers(
                         new CancelandStopIntentHandler(),
                         new HelloWorldIntentHandler(),
                         new HelpIntentHandler(),
                         new LaunchRequestHandler(),
                         new SessionEndedRequestHandler())
                  .build();
      }

      public HelloWorldStreamHandler() {
          super(getSkill());
      }

  }