/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petsim;
import java.util.Scanner;

class catStats {
    private double happinessLevel = 50;
    private double hungerLevel = 50;
    private double energyLevel = 50;
    private double cleanlinessLevel = 50;
    private double healthLevel = 50;
    private String catName = "Whiskers";
    
    
    // Getter and Setter
    public void setHungerLevel(double hungerLevel) {
        this.hungerLevel = hungerLevel;
        System.out.println(this.hungerLevel);
    }

    public double getHungerLevel() {
        return this.hungerLevel;
    }
    
    public void setHappinessLevel(double happinessLevel) {
        this.happinessLevel = happinessLevel;
    }
    
    public double getHappinessLevel() {
        return this.happinessLevel;
    }
    
    public void setEnergyLevel(double energyLevel) {
        this.energyLevel = energyLevel;
    }
    
    public double getEnergyLevel() {
        return this.energyLevel;
    }
    
    public void setCleanlinessLevel(double cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }
    
    public double getCleanlinessLevel() {
        return this.cleanlinessLevel;
    }
    
    public void setHealthLevel(double healthLevel) {
        this.healthLevel = healthLevel;
    }
    
    public double getHealthLevel() {
        return this.healthLevel;
        //test
    }
    
    public void setName(String newName) {
        this.catName = newName;
    }
    
    public String getName() {
        return this.catName;
    }
}

// Classes for Cat Stats
class hunger extends catStats {
    protected final double isaw = 20;
    protected final double biscuit = 10;
    protected final double spanishLatte = 25;
    protected final double iceCream = 15;
    protected final double beefSteak = 20;
    protected double foodValue = 0;
    
    
    
    
    public void addHunger(String name) {
        // Identify the food type
        identifyFood(name);
        
        double newHungerLevel = getHungerLevel() + foodValue;
        setHungerLevel(newHungerLevel);
    }
    
    public void identifyFood(String name){
        switch(name) {
            case "isaw" -> foodValue = isaw;
            case "biscuit" -> foodValue = biscuit;
            case "spanishLatte" -> foodValue = spanishLatte;
            case "iceCream" -> foodValue = iceCream;
            case "beefSteak" -> foodValue = beefSteak;
        }
    }
    
}

class happiness extends catStats {
    public void addHappiness(double plusHappiness) {
        double newHappinessLevel = getHungerLevel() + plusHappiness;
        setHappinessLevel(newHappinessLevel);
    }
}

class energy extends catStats {
    
    
    public void addEnergy(double plusEnergy) {
        double newEnergyLevel = getEnergyLevel() + plusEnergy;
        setEnergyLevel(newEnergyLevel);
    }
}

class cleanliness extends catStats {
    public void addCleanliness(double plusCleanliness) {
        double newCleanlinessLevel = getCleanlinessLevel() + plusCleanliness;
        setCleanlinessLevel(newCleanlinessLevel);
    }
}

class health extends catStats {
    public void addHealth(double plusHealth) {
        double newHealthLevel = getHealthLevel() + plusHealth;
        setHealthLevel(newHealthLevel);
    }
}

public class PetSim {
    public static void main(String[] args) {
        // This is only a tester for updating the food stat
        /*String input = "";
        hunger obj = new hunger();
        
        Scanner read = new Scanner(System.in);
        System.out.println("Enter String: ");
        input = read.nextLine();
        
        hunger.addHunger(input);
        */
        
        NewJFrame canvas = new NewJFrame();
        
        canvas.setVisible(true);
        
    }
}
