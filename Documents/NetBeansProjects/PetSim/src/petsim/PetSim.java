/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petsim;

class catStats {
    private double happinessLevel = 50;
    private double hungerLevel = 50;
    private double energyLevel = 40;
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
    protected final double isaw = -20;
    protected final double biscuit = -10;
    protected final double spanishLatte = -25;
    protected final double iceCream = -15;
    protected final double beefSteak = -20;
    protected double foodValue = 0;
    
    
    
    
    public void addHunger(String name) {
        // Identify the food type
        identifyFood(name);
        
        double newHungerLevel = getHungerLevel() + foodValue;
        setHungerLevel(newHungerLevel); 
        System.out.println("Hunger: " + getHungerLevel());
    }
    
    public void identifyFood(String name){
        switch(name) {
            case "isaw":
                foodValue = isaw;
                break;
            case "biscuit":
                foodValue = biscuit;
                break;
            case "spanishLatte":
                foodValue = spanishLatte;
                break;
            case "iceCream":
                foodValue = iceCream;
                break;
            case "beefSteak":
                foodValue = beefSteak;
                break;
            default:
                System.out.println("No food found");
                break;
        }
    }
    
}

class happiness extends hunger {
    public void addHappiness(double plusHappiness) {
        double newHappinessLevel = getHappinessLevel() + plusHappiness;
        setHappinessLevel(newHappinessLevel);
        System.out.println("Happiness: " + getHappinessLevel());
    }
}

class energy extends happiness {
    public void addEnergy(double plusEnergy) {
        double newEnergyLevel = getEnergyLevel() + plusEnergy;
        setEnergyLevel(newEnergyLevel);
        System.out.println("Energy: " + getEnergyLevel());
    }
}

class cleanliness extends energy {
    public void addCleanliness(double plusCleanliness) {
        double newCleanlinessLevel = getCleanlinessLevel() + plusCleanliness;
        setCleanlinessLevel(newCleanlinessLevel);
        System.out.println("Cleanliness: " + getCleanlinessLevel());
    }
}

class health extends cleanliness {
    public void addHealth(double plusHealth) {
        double newHealthLevel = getHealthLevel() + plusHealth;
        setHealthLevel(newHealthLevel);
        System.out.println("Health: " + getHealthLevel());
    }
}

class cat extends health {
    // Multi-Level Inheritance
    
    public void limiter() {
        if(getEnergyLevel() >= 101) {
            setEvergyLevel(100);
        }
    }
    
    // Methods for Stats
    public boolean calculator(double base, double number) {
        double result = base - number;
        if (result <= -1) {
            return true; // It will proceed to the action
        } else {
            return false; // It will not proceed to the action
        }
    }
    
}

public class PetSim {
    public static cat obj1 = new cat();
    
    public static void main(String[] args) {
        
        NewJFrame canvas = new NewJFrame();
        canvas.setVisible(true);
        
    }
}
