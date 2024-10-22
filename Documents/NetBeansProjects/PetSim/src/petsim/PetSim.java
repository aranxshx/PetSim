/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petsim;

/**
 *
 * @author HP
 */
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
    }
}

// Classes for Cat Stats
class hunger extends catStats {
    public void addHunger(double foodType) {
        double newHungerLevel = getHungerLevel() + foodType;
        setHungerLevel(newHungerLevel);
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
        frame canvas = new frame();
        
        canvas.setVisible(true);
    }
}
