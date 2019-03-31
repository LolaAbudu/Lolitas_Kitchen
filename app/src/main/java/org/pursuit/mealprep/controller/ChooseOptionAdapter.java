package org.pursuit.mealprep.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.model.Ingredients;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;
import org.pursuit.mealprep.view.ChooseOptionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChooseOptionAdapter extends RecyclerView.Adapter<ChooseOptionViewHolder> {
    Context c;
    List<Meal> ingredientsList;
    List<Meal> checkedIngredients = new ArrayList<>();
    Meal meal;

    public ChooseOptionAdapter(List<Meal> ingredientsList) {
        this.ingredientsList = ingredientsList;
        //this.c = c;
    }

    @NonNull
    @Override
    public ChooseOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View childview = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_option_itemview, parent, false);
        return new ChooseOptionViewHolder(childview);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseOptionViewHolder chooseOptionViewHolder, int position) {
//        List<String> individualIngredients = new ArrayList<>();
//        individualIngredients.addAll(meal.getKeywords());
//        Log.d("TAG", "allIng" + individualIngredients);

        chooseOptionViewHolder.onBind(ingredientsList.get(position));
        chooseOptionViewHolder.setListener(new ChooseOptionItemClickListener() {
            @Override
            public void onIngredientClick(View v, int position) {
                CheckBox checkBox = (CheckBox) v;

                //check if its checked or not
                if (checkBox.isChecked()) {
                    checkedIngredients.add(ingredientsList.get(position));
                } else if (!checkBox.isChecked()) {
                    checkedIngredients.remove(ingredientsList.get(position));
                }
            }

            @Override
            public void showRecipes() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }
}
