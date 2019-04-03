package org.pursuit.mealprep;

import org.pursuit.mealprep.model.Meal;

import java.util.List;

public interface ViewPagerFragmentInteractionListener {
    void toDisplayAllMealFragment(List<Meal> meals, List<String> userSelection);
}
