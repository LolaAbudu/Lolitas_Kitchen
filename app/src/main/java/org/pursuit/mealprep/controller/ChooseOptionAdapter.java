package org.pursuit.mealprep.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.fragments.IngredientSelectedListener;
import org.pursuit.mealprep.view.ChooseOptionViewHolder;

import java.util.List;

public class ChooseOptionAdapter extends RecyclerView.Adapter<ChooseOptionViewHolder> {

    private List<String> ingredientsList;
    private IngredientSelectedListener listener;

    public ChooseOptionAdapter(IngredientSelectedListener listener, List<String> ingredientsList) {
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
    public void setData(List<String> ingredients) {
        this.ingredientsList = ingredients;
        notifyDataSetChanged();
    }
}
