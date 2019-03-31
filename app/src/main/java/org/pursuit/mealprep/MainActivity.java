package org.pursuit.mealprep;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.pursuit.mealprep.fragments.ChooseOptionFragment;
import org.pursuit.mealprep.fragments.TypeInFragment;
import org.pursuit.mealprep.controller.ViewPagerAdapter;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;

public class MainActivity extends AppCompatActivity implements TypeInFragment.OnFragmentInteractionListener, ChooseOptionItemClickListener {

    Button showRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showRecipeButton = findViewById(R.id.show_recipe_button);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Type In"));
        tabLayout.addTab(tabLayout.newTab().setText("Choose Options"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.view_pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        showRecipeOnClick();
    }

    private void showRecipeOnClick() {
        showRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, );

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onIngredientClick(View v, int position) {
        //this is where you add what you want to do with the ingredients that have been selected
        //possibly add them to the query search
    }

    @Override
    public void showRecipes() {
        showRecipeOnClick();
    }
}
