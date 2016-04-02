package com.darrydanzig.myfirstapp.models;

import java.util.HashMap;

/**
 * Created by darry on 01/04/16.
 */
public class EventHandler {

    public Competition competition;

    public HashMap<Integer, Event> unprocessedEvents;

    public int eventNumber = 0;

    public void processEvent(Event event) {

        if (event.competitionId != competition.id)
            return;

        else if (eventNumber + 1 != event.eventNumber) {
            unprocessedEvents.put(event.eventNumber, event);
            return;
        }
        else {

            eventNumber = event.eventNumber;
            switch(event.event) {

                case "new_performance":

                    competition.newPerformance(event.performanceId, event.previousPerformanceId,
                            event.poetName, event.roundNumber);
                    break;

                case "judge":

                    competition.judge(event.performanceId, event.judgeName, event.value);
                    break;

                case "set_time":

                    competition.set_time(event.performanceId, event.minutes, event.seconds);
                    break;

                case "penalty":

                    competition.penalty(event.performanceId, event.value);
                    break;

            }
        }
    }
}
