package enums;

import java.awt.Color;

/**
 *Defines possible teams
 * @author Connor Lehmacher
 */
public enum Teams {
    NONE(Color.BLACK) , GREEN(Color.GREEN) , BLUE(Color.BLUE) , GOV(Color.ORANGE); 
    /**
     * The color to be attached
     */
    final private Color color;
    /**
     * Attaches color
     * @param color
     */
    Teams(Color color){
        this.color = color;
    }
    /**
     * Passes color based on which team
     * @return
     */
    public Color getColor(){
        return color;
    }
}
