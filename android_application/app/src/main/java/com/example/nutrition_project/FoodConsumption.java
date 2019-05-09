package com.example.nutrition_project;
import java.util.Date;

public class FoodConsumption {
    private int quantity;
    private Date date;
    private Food food;
    public enum MealType {
        Breakfast, Lunch, Dinner
    }
    private MealType mealType;

    public FoodConsumption(int quantity,String type,Food food){
        this.quantity=quantity;
        this.food=food;
        if(type.equals("Breakfast")) mealType= MealType.Breakfast;
        else if(type.equals("Lunch"))mealType=MealType.Lunch;
        else mealType=MealType.Dinner;
        date=new Date();
    }
    public int getQuantity(){
        return quantity;
    }
    public Date getDate(){
        return date;
    }
    public MealType getMealType(){
        return mealType;
    }

    public Food getFood() {
        return food;
    }
}
