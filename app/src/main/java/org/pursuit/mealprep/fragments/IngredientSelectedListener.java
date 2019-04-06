package org.pursuit.mealprep.fragments;


public interface IngredientSelectedListener {
    void addItem(String ingredient);
    void remove(String ingredient);
}
