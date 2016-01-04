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
    double[] plan = new double[2];
    /**
     * Can be used to generate a size, attack and health
     */
    double level;
    boolean idea = true;
    
    /**
     * Person with some location, size, color, health and team (for customization) 
     */
    public Person(double x, double y, double size, Color color, Teams team, double health, Window p_1){
        super(x, y, size, color, p_1);
        this.team = team;
        this.health = health;
        
        location.addToPeople(this);
        //Arbitrary Amount
        level = 3;
    }   
    /**
     * Person with some location, level and team (Normal Use)
     */
    public Person(double x, double y, double level, Teams team, Window p_1){
        this(x, y, (level + 9), team.getColor(), team, (level + 10), p_1);
    }
    /**
     * Moves the player in random directions
     */
    public void wander(){
    	//TODO make this system more efficient
    	if(idea){
            velocity = convertToComponents(1.7, Math.random() * Math.PI * 2);
            idea = false;
    	}
        if(Math.random() < 0.004){
            velocity = convertToComponents(pythagorate(velocity), Math.random() * Math.PI * 2);
        }
    }
 }
