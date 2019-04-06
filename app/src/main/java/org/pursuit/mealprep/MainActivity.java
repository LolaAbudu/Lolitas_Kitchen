package org.pursuit.mealprep;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.pursuit.mealprep.fragments.AboutMeFragment;
import org.pursuit.mealprep.fragments.DisplayAllMealsFragment;
import org.pursuit.mealprep.fragments.SelectedMealFragment;
import org.pursuit.mealprep.fragments.ViewPagerFragment;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.NutritionFacts;
import org.pursuit.mealprep.model.Time;
//import org.pursuit.mealprep.network.ChooseOptionItemClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPagerFragmentInteractionListener {

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
                                       List<String> direction
    ) {
        //TODO add to above later
        // ,ArrayList<NutritionFacts> nutritionalFacts,
        // ArrayList<Time> cookTime
        SelectedMealFragment selectedMealFragment = SelectedMealFragment.newInstance(name, image, description, ingredients, direction);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.projects_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.main_github) {
//            Intent mainGitHub = new Intent(Intent.ACTION_VIEW);
//            mainGitHub.setData(Uri.parse("https://github.com/LolaAbudu?tab=repositories"));
//            startActivity(mainGitHub);
//            Toast.makeText(getApplicationContext(), "Lola's GitHub Account", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.meal_prep_github) {
//            Intent mealPrepGitHub = new Intent(Intent.ACTION_VIEW);
//            mealPrepGitHub.setData(Uri.parse("https://github.com/LolaAbudu/Meal_Prep"));
//            startActivity(mealPrepGitHub);
//            Toast.makeText(getApplicationContext(), "Meal-Prep-GitHub", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.linkedIn) {
//            Intent linkedIn = new Intent(Intent.ACTION_VIEW);
//            linkedIn.setData(Uri.parse("https://www.linkedin.com/in/omolola-abudu/"));
//            startActivity(linkedIn);
//            Toast.makeText(getApplicationContext(), "Lola's LinkedIn", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

