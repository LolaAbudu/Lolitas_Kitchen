package org.pursuit.mealprep;

import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.NutritionFacts;
import org.pursuit.mealprep.model.Time;

import java.util.List;

public interface FragmentInteractionListener {
    void toDisplayAllMealFragment(List<Meal> meals,
                                  List<String> userSelection);

    void toSelectedMealFragment(String name,
                                String image,
                                String description,
                                List<String> ingredients,
                                List<String> direction,
                                NutritionFacts nutritionalFacts,
                                Time cookTime);

    void toAboutMe();

    void toViewPagerFragment();
}
