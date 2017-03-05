package com.neonidapps.fruityworld2.modelos;

/**
 * Created by Neonidas on 05/03/2017.
 */

public class Fruit {

    private String name;
    private String desc;
    private int units;
    private int imageResource;
    private int iconResourse;

    public final static int MAX_UNITS = 10;
    public final static int RESET_UNITS_VALUE = 0;


    public Fruit(String name, String desc, int units, int imageResource, int iconResourse) {
        this.name = name;
        this.desc = desc;
        this.units = units;
        this.imageResource = imageResource;
        this.iconResourse = iconResourse;
    }

    public void addUnits(int units) {
        if (this.units + units <= MAX_UNITS)
            this.units += units;
    }

    public void resetUnits() {
        this.units = RESET_UNITS_VALUE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getIconResourse() {
        return iconResourse;
    }

    public void setIconResourse(int iconResourse) {
        this.iconResourse = iconResourse;
    }
}
