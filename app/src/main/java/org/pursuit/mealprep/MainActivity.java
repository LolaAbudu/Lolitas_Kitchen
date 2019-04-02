package org.pursuit.mealprep;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.pursuit.mealprep.controller.ViewPagerAdapter;
import org.pursuit.mealprep.fragments.TypeInFragment;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;

public class MainActivity extends AppCompatActivity implements TypeInFragment.OnFragmentInteractionListener, ChooseOptionItemClickListener {

    Button showRecipeButton;
    private ChooseOptionItemClickListener fInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showRecipeButton = findViewById(R.id.show_recipe_button);
        fInterface = this;

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
        showRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayActivity.class);
                v.getContext().startActivity(intent);
//                if (v instanceof ChooseOptionItemClickListener) {
//                    fInterface = (ChooseOptionItemClickListener) v;
//                } else {
//                    throw new RuntimeException(v.toString()
//                            + " must implement FragmentInterface");
//                }
//                showRecipeOnClick();
            }
        });

    }


    private void showRecipeOnClick() {
        if (fInterface != null) {
            fInterface.showRecipes();
        }
//        showRecipeButton.setOnClickListener(v -> {
////            Intent intent = new Intent(v.getContext(),DisplayActivity.class);
////            intent.putExtra("TAG", "testing");
////            v.getContext().startActivity(intent);
//                String one = "";
//                String two = "";
//                DisplayAllMealsFragment displayAllMealsFragment = DisplayAllMealsFragment.newInstance(one, two);
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.);
//        });
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
//        showRecipeOnClick();
    }
}
