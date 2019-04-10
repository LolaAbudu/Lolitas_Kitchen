package org.pursuit.mealprep.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.R;
import org.pursuit.mealprep.model.Meal;

import java.util.ArrayList;

public class DisplayAllMealsViewHolder extends RecyclerView.ViewHolder {
    private TextView mealNameTextView;
    private ImageView mealImageView;

    private ArrayList<Meal> listOfMeals = new ArrayList<>();

    public DisplayAllMealsViewHolder(@NonNull View itemView) {
        super(itemView);
        mealNameTextView = itemView.findViewById(R.id.mealName_textview);
        mealImageView = itemView.findViewById(R.id.mealImage_imageview);
    }

    public void onBind(final Meal meal, FragmentInteractionListener vpListener) {
        mealNameTextView.setText(meal.getName());
        Picasso.get().load(meal.getImage()).into(mealImageView);

        listOfMeals.add(meal);
        itemView.setOnClickListener(v -> {
            vpListener.toSelectedMealFragment(meal.getName(),
                    meal.getImage(),
                    meal.getDescription(),
                    meal.getIngredients(),
                    meal.getDirections(),
                    meal.getNutritionFacts(),
                    meal.getTime()
            );
        });
    }
}
