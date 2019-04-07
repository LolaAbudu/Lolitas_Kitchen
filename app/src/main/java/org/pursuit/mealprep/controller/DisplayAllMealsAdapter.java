package org.pursuit.mealprep.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.ViewPagerFragmentInteractionListener;
import org.pursuit.mealprep.fragments.Ingredient;
import org.pursuit.mealprep.fragments.IngredientSelectedListener;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.view.DisplayAllMealsViewHolder;

import java.util.List;

public class DisplayAllMealsAdapter extends RecyclerView.Adapter<DisplayAllMealsViewHolder> {

    private List<Meal> mealList;
    private ViewPagerFragmentInteractionListener viewPagerFragmentInteractionListener;

    public DisplayAllMealsAdapter( List<Meal> mealList, ViewPagerFragmentInteractionListener listener) {
        this.viewPagerFragmentInteractionListener = listener;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public DisplayAllMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new DisplayAllMealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.display_all_meal_itemview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAllMealsViewHolder displayAllMealsViewHolder, int position) {
        displayAllMealsViewHolder.onBind( mealList.get(position), viewPagerFragmentInteractionListener);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void setData(List<Meal> meals) {
        this.mealList = meals;
        notifyDataSetChanged();
    }
}
//ViewPagerFragmentInteractionListener listener,
//viewPagerFragmentInteractionListener,