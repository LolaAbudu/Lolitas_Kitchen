package org.pursuit.mealprep.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.pursuit.mealprep.R;
import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.controller.ViewPagerAdapter;

import java.util.Objects;

public class ViewPagerFragment extends Fragment {

    private FragmentInteractionListener vpListener;

    public ViewPagerFragment() { }

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        Toolbar toolBar = view.findViewById(R.id.toolbar);

        if (toolBar != null) {
            ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolBar);
        }
        this.setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            vpListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        vpListener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Type In"));
        tabLayout.addTab(tabLayout.newTab().setText("Choose Options"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = view.findViewById(R.id.view_pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.projects_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        Log.d("TAG", "menu has been called");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about_me) {
            vpListener.toAboutMe();
        } else if (id == R.id.main_github) {
            Intent mainGitHub = new Intent(Intent.ACTION_VIEW);
            mainGitHub.setData(Uri.parse("https://github.com/LolaAbudu?tab=repositories"));
            startActivity(mainGitHub);
            Toast.makeText(getContext(), "Lola's GitHub Account", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.meal_prep_github) {
            Intent mealPrepGitHub = new Intent(Intent.ACTION_VIEW);
            mealPrepGitHub.setData(Uri.parse("https://github.com/LolaAbudu/Meal_Prep"));
            startActivity(mealPrepGitHub);
            Toast.makeText(getContext(), "Meal-Prep-GitHub", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.linkedIn) {
            Intent linkedIn = new Intent(Intent.ACTION_VIEW);
            linkedIn.setData(Uri.parse("https://www.linkedin.com/in/omolola-abudu/"));
            startActivity(linkedIn);
            Toast.makeText(getContext(), "Lola's LinkedIn", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
