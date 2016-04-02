package com.darrydanzig.myfirstapp.models;

/**
 * Created by darry on 01/04/16.
 */
public class FullCompetition {

    public Competition slam;
    public Round[] rounds;
    public Event[] events;
    public EventHandler eventHandler;

    private boolean processEventsHasBeenCalled;

    public void processEvents() {

        if (processEventsHasBeenCalled)
            return;
        else
            processEventsHasBeenCalled = true;

        eventHandler = new EventHandler();
        eventHandler.setCompetition(slam);

        int numberOfEvents = events.length;
        for (int i=0; i<numberOfEvents; i++) {
            eventHandler.processEvent(events[i]);
        }
    }
}
