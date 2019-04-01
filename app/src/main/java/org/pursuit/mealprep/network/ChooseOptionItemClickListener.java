package org.pursuit.mealprep.network;

import android.view.View;

public interface ChooseOptionItemClickListener {
    void onIngredientClick(View v, int position);

    void showRecipes();
}
