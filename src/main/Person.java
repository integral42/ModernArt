package main;

import enums.Teams;

import java.awt.Color;

//import java.util.Random;

/**
 * Moving Person
 * Has Health, Attack, Level, and Team
 * @author Connor Lehmacher
 */
public class Person extends MyRectangle {
    /**
    * One of 4 teams in enums.Teams
    */
    Teams team;
    /**
     * A positive integer between 1 and 10
     */
    double health;
    /**
     * Can be used to generate a size, attack and health
     */
    double level;
    /**
     * Makes Person initially move
     */
    boolean idea = true;
    
    /**
     * Person with some location, size, color, health and team (for customization) 
     */
    public Person(Vector location, double size, Color color, Teams team, double health, Window place){
        super(location, size, color, place);
        this.team = team;
        this.health = health;
        
        place.addToPeople(this);
        //Arbitrary Amount
        level = 3;
    }   
    /**
     * Person with some location, level and team (Normal Use)
     */
    public Person(Vector location, double level, Teams team, Window p_1){
        this(location, (level + 9), team.getColor(), team, (level + 10), p_1);
    }
    /**
     * Moves the player in random directions
     */
    public void wander(){
    	//TODO put this in the constructor
    	if(idea){
            velocity = Vector.createFromPolar(1.7, Math.random() * Math.PI * 2);
            idea = false;
    	}
    	//Random Direction
        if(Math.random() < 0.004){
            velocity = Vector.createFromPolar(velocity.norm(), Math.random() * Math.PI * 2);
        }
    }
 }
