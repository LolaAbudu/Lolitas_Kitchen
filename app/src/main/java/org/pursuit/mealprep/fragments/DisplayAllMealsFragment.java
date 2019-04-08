package org.pursuit.mealprep.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.controller.DisplayAllMealsAdapter;
import org.pursuit.mealprep.model.Meal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisplayAllMealsFragment extends Fragment {

    private static final String MEALS_KEY = "meals";
    private static final String USER_SELECTION_KEY = "userSelection";

    private List<Meal> mealList = new ArrayList<>();
    private List<Meal> userSelectionList = new ArrayList<>();
    private List<String> userSelection;

    private FragmentInteractionListener listener;
    private DisplayAllMealsAdapter adapter;


    public DisplayAllMealsFragment() {
    }

    public static DisplayAllMealsFragment newInstance(List<Meal> meals,
                                                      List<String> userSelection) {
        DisplayAllMealsFragment fragment = new DisplayAllMealsFragment();
        Bundle args = new Bundle();
        args.putSerializable(MEALS_KEY, (Serializable) meals);
        args.putSerializable(USER_SELECTION_KEY, (Serializable) userSelection);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mealList = (List<Meal>) getArguments().getSerializable(MEALS_KEY);
            userSelection = (List<String>) getArguments().getSerializable(USER_SELECTION_KEY);
            adapter = new DisplayAllMealsAdapter(userSelectionList, listener);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_all_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView allMealsRecyclerView = view.findViewById(R.id.display_all_meal_recyclerview);

        userSelectionList.clear();

        addUniqueMealsWithSelectedIngredients();

        adapter.setData(userSelectionList);
        allMealsRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        allMealsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void addUniqueMealsWithSelectedIngredients() {
        for (int i = 0; i < mealList.size(); i++) {
            for (int j = 0; j < userSelection.size(); j++) {
                if (mealList.get(i).getKeywords().contains(userSelection.get(j))) {
                    if (!userSelectionList.contains(mealList.get(i))) {
                        userSelectionList.add(mealList.get(i));
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            listener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}