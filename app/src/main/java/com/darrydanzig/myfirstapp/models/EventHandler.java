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

                    break;

                case "judge":

                    break;

                case "set_time":

                    break;

                case "penalty":

                    break;

            }
        }
    }
}
