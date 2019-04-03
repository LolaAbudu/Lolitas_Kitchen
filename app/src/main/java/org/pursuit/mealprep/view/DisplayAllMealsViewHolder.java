package org.pursuit.mealprep.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.ViewPagerFragmentInteractionListener;
import org.pursuit.mealprep.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class DisplayAllMealsViewHolder extends RecyclerView.ViewHolder {
    private TextView mealNameTextView;
    private ImageView mealImageView;

    public DisplayAllMealsViewHolder(@NonNull View itemView) {
        super(itemView);
        mealNameTextView = itemView.findViewById(R.id.mealName_textview);
        mealImageView = itemView.findViewById(R.id.mealImage_imageview);
    }

    public void onBind(final Meal meal) {
//        List<Meal> mealList = new ArrayList<>();
//        mealList.add(meal);
//        Log.d("TAG", "mealListSize" + mealList.size());
//        List<String> userSelection = new ArrayList<>();
//        userSelection.contains(meal);
//
//        listener.toDisplayAllMealFragment(mealList, userSelection);
        mealNameTextView.setText(meal.getName());
        Picasso.get().load(meal.getImage()).into(mealImageView);
    }
}
//ViewPagerFragmentInteractionListener listener,