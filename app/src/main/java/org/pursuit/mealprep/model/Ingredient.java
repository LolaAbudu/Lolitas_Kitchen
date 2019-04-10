package org.pursuit.mealprep.model;

import androidx.annotation.NonNull;

public class Ingredient {
    public final String name;
    public boolean isChecked;

    public Ingredient(String name) {
        this.name = name;
        this.isChecked = false;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
