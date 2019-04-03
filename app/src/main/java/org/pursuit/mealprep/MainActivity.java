package org.pursuit.mealprep;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.pursuit.mealprep.fragments.DisplayAllMealsFragment;
import org.pursuit.mealprep.fragments.ViewPagerFragment;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ChooseOptionItemClickListener, ViewPagerFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPagerFragment viewPagerFragment = ViewPagerFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, viewPagerFragment).commit();
    }

//    @Override
//    public void toDisplayAllMealsFragment() {
//        DisplayAllMealsFragment displayAllMealsFragment = DisplayAllMealsFragment.newInstance();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_container, displayAllMealsFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    @Override
    public void toDisplayAllMealFragment(List<Meal> meals, List<String> userSelection) {
        DisplayAllMealsFragment displayAllMealsFragment = DisplayAllMealsFragment.newInstance(meals, userSelection);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, displayAllMealsFragment)
                .addToBackStack(null)
                .commit();
    }
}

