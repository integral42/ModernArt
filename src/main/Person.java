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
	//----------------Fields------------------//
    /** One of 4 teams in enums.Teams */
    Teams team;
    /** A positive integer between 1 and 10 */
    double health;
    /** Can be used to generate a size, attack and health */
    double level;
    
    //--------------Constructors----------------//
    /** Person with some location, size, color, health and team (for customization) */
    public Person(Vector position, double size, Color color, Teams team, double health, Window location){
        super(position, size, color, location);
        this.team = team;
        this.health = health;
        
        //Makes Person initially move
        final double initialSpeed = 0.000025;
        velocity = Vector.createFromPolar(initialSpeed, Math.random() * Math.PI * 2);
        location.addToPeople(this);
        //Arbitrary Amount
        level = 3;
    }   
    /** Person with some location, level and team (Normal Use) */
    public Person(Vector position, double level, Teams team, Window location){
        this(position, (level + 9), team.getColor(), team, (level + 10), location);
        this.level = level;
    }
    
    //-------------Methods------------//
    /** Moves the player in random directions */
    public void wander(){
        if(Math.random() < 0.00009){
            velocity = Vector.createFromPolar(velocity.norm(), Math.random() * Math.PI * 2);
        }
    }
 }
