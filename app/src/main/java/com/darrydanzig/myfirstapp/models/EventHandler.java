package com.darrydanzig.myfirstapp.models;

import java.util.HashMap;

/**
 * Created by darry on 01/04/16.
 */
public class EventHandler {

    private Competition competition;
    private HashMap<Integer, Event> unprocessedEvents = new HashMap<Integer, Event>();
    private int eventNumber;

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public void processEvent(Event event) {

        if (event.competitionId != competition.id)
            return;

        else if (eventNumber + 1 != event.eventNumber) {

            if (event.eventNumber > eventNumber + 1)
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
