package org.pursuit.mealprep.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.fragments.Ingredient;
import org.pursuit.mealprep.fragments.IngredientSelectedListener;
import org.pursuit.mealprep.view.ChooseOptionViewHolder;

import java.util.List;

public class ChooseOptionAdapter extends RecyclerView.Adapter<ChooseOptionViewHolder> {

    private List<Ingredient> ingredientsList;
    private IngredientSelectedListener listener;

    //use to save the state of the checked Textbox
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();

    public ChooseOptionAdapter(IngredientSelectedListener listener, List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChooseOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ChooseOptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_option_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseOptionViewHolder chooseOptionViewHolder, int position) {
        chooseOptionViewHolder.onBind(listener, ingredientsList.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    //USE FOR BOTH CHOOSE OPTION AND TYPE IN
    public void setData(List<Ingredient> ingredients) {
        this.ingredientsList = ingredients;
        notifyDataSetChanged();
    }
}
