package com.darrydanzig.myfirstapp.models;

/**
 * Created by darry on 01/04/16.
 */
public class Event {


    // The type of event
    public String event;
    public int performanceId;


    public int eventNumber;
    public int competitionId;


    /*

    event = performance

     */


    // Only for Competitions which the scores are cumulative from round to round
    public int previousPerformanceId;
    public String poetName;
    public int roundNumber;

    /*

    event = judge

    */
    public String judgeName;
    public float value;


    /*

    event = set_time

    */

    public int minutes;
    public int seconds;

    /*

    set_penalty

    */

    public float penalty;

    /*

    remove_performance

    */
    // performanceId (already defined)


}
