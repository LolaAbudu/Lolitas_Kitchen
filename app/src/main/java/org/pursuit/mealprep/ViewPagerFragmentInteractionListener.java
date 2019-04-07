package org.pursuit.mealprep;

import org.pursuit.mealprep.model.Meal;

import java.util.List;

public interface ViewPagerFragmentInteractionListener {
    void toDisplayAllMealFragment(List<Meal> meals,
                                  List<String> userSelection);

    void toSelectedMealFragment(String name,
                                String image,
                                String description,
                                List<String> ingredients,
                                List<String> direction);

    void toAboutMe();

    void toViewPagerFragment();

    //add later
    //  ,List<NutritionFacts> nutritionalFacts,
    //  List<Time> cookTime
}

//List<Meal> meals