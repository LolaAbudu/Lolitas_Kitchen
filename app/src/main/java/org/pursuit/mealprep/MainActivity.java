package org.pursuit.mealprep;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.pursuit.mealprep.fragments.AboutMeFragment;
import org.pursuit.mealprep.fragments.DisplayAllMealsFragment;
import org.pursuit.mealprep.fragments.SelectedMealFragment;
import org.pursuit.mealprep.fragments.SplashFragment;
import org.pursuit.mealprep.fragments.ViewPagerFragment;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.NutritionFacts;
import org.pursuit.mealprep.model.Time;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SplashFragment splashFragment = SplashFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, splashFragment)
                .commit();
    }

    @Override
    public void toViewPagerFragment() {
        ViewPagerFragment viewPagerFragment = ViewPagerFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, viewPagerFragment)
                .commit();
    }

    @Override
    public void toDisplayAllMealFragment(List<Meal> meals,
                                         List<String> userSelection) {
        DisplayAllMealsFragment displayAllMealsFragment = DisplayAllMealsFragment.newInstance(meals, userSelection);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, displayAllMealsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void toSelectedMealFragment(String name,
                                       String image,
                                       String description,
                                       List<String> ingredients,
                                       List<String> direction,
                                       NutritionFacts nutritionalFacts,
                                       Time cookTime) {
        //TODO add to above later
//         ,ArrayList<NutritionFacts> nutritionalFacts,
//        ArrayList<Time> cookTime
        SelectedMealFragment selectedMealFragment = SelectedMealFragment.newInstance(name, image, description, ingredients, direction, nutritionalFacts, cookTime);
        //TODO add late ---, nutritionalFacts, cookTime
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, selectedMealFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void toAboutMe() {
        AboutMeFragment aboutMeFragment = AboutMeFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, aboutMeFragment)
                .addToBackStack(null)
                .commit();
    }
}

