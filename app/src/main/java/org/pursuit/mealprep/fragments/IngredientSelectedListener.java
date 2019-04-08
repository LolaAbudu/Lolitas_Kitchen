package org.pursuit.mealprep.fragments;

import org.pursuit.mealprep.model.Ingredient;

public interface IngredientSelectedListener {
    void addItem(Ingredient ingredient);
    void removeItem(Ingredient ingredient);
}
