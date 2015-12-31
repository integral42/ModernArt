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
    double attack;
    double health;
    double[] plan = new double[2];
    /**
     * Can be used to generate a size, attack and health
     */
    double level;
    boolean idea = true;
    
    /**
     * Person with some location, size, color, health and team (for customization) 
     * @param x
     * @param y
     * @param size
     * @param color
     * @param team
     * @param health
     * @param attack
     * @param p_1
     */
    public Person(double x, double y, double size, Color color, Teams team, double health, double attack, Window p_1){
        super(x, y, size, color, p_1);
        this.team = team;
        this.health = health;
        
        location.addToPeople(this);
        //Arbitrary Amount
        level = 3;
    }   
    /**
     * Person with some location, level and team (Normal Use)
     * @param x
     * @param y
     * @param level
     * @param team
     * @param p_1
     */
    public Person(double x, double y, double level, Teams team, Window p_1){
        this(x, y, (level + 9), team.getColor(), team, (level + 10), attackValue(team, level), p_1);
    }
    /**
     * Moves the player in random directions
     */
    public void wander(){
    	//TODO make this system more efficient
        if(Math.random() < 0.004 || idea){
            directions = convertToComponents(1.7, Math.random() * Math.PI * 2);
            idea = false;
        }
    }
    /**
     * Only Gives attack to BLUEs GREENs and GOVs 
     * @param team
     * @param level
     * @return 
     */
    public static double attackValue(Teams team, double level){
        double attack;
        if(team == Teams.NONE) attack = 0;
        else attack = level + 1;
        return attack;
    }
 }
