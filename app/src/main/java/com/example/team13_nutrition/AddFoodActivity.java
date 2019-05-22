package com.example.team13_nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.team13_nutrition.ui.main.Tab1;

import java.util.ArrayList;

public class AddFoodActivity extends AppCompatActivity {

    SearchView searchView;
    ListView foodListView;
    Spinner mealType;
    Button confirmFoodButton;
    ListViewItemAdapter foodAdapter;
    ArrayList<ListViewItemClass> foodList;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_consumption_layout);
        user = getIntent().getStringExtra("params");

        searchView = findViewById(R.id.foodSearchView);
        foodListView = findViewById(R.id.foodListView);
        mealType = findViewById(R.id.mealType);
        confirmFoodButton = findViewById(R.id.foodConfirmButton);

        loadFoods();
        foodAdapter = new ListViewItemAdapter(this, foodList);
        foodListView.setAdapter(foodAdapter);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.diary_item_list_header_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealType.setAdapter(spinnerAdapter);

        confirmFoodButton.setOnClickListener(v -> applyExercises());

    }

    private void loadFoods() {

        foodList = new ArrayList<>();

        for (String key : MakeMap.foodMap.keySet()) {
            ListViewItemClass added = new ListViewItemClass(key, false, 1);
            foodList.add(added);
        }

    }

    private void applyExercises(){

        foodAdapter.notifyDataSetChanged();
        boolean hasChosen = false;

        for(ListViewItemClass item : foodList){
            //System.out.println(item.getName() + " + " + item.getQuantity() + " + " + item.isChecked());
            if(item.isChecked()){
                hasChosen = true;
                Food food = MakeMap.foodMap.get(item.getName());
                String type = mealType.getSelectedItem().toString();
                FoodConsumption consumption = new FoodConsumption(item.getQuantity(), type, food);
                Tab1.foodConsumptions.add(consumption);
                Intent it = new Intent(AddFoodActivity.this, MainActivity.class);
                it.putExtra("params", user);
                startActivity(it);
                //System.out.println("Added: " + performance.getExercise().getName());
            }
        }

        if(hasChosen){

        }
        else{
            Toast.makeText(this, "No foods have been selected!", Toast.LENGTH_LONG).show();
        }

    }

}
