package org.pursuit.mealprep.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.ViewPagerFragmentInteractionListener;
import org.pursuit.mealprep.controller.ChooseOptionAdapter;
import org.pursuit.mealprep.controller.DisplayAllMealsAdapter;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.MealList;
import org.pursuit.mealprep.network.MealServices;
import org.pursuit.mealprep.network.RetrofitSingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.support.constraint.Constraints.TAG;

public class DisplayAllMealsFragment extends Fragment implements ViewPagerFragmentInteractionListener {

    private static final String ARG_PARAM1 = "meals";
    private static final String ARG_PARAM2 = "userSelection";
    private static final String ARG_PARAM3 = "selectedMealList";

    //    private Meal meals;
    private List<Meal> mealList;
    private List<String> userSelection;
    private List<Meal> selectedMealList;

    private RecyclerView allMealsRecyclerView;
    private ArrayList<String> ingredients = new ArrayList<>();

    private CompositeDisposable compositeDisposable;


    public DisplayAllMealsFragment() {
        // Required empty public constructor
    }

    public static DisplayAllMealsFragment newInstance(List<Meal> meals, List<String> userSelection) {
        DisplayAllMealsFragment fragment = new DisplayAllMealsFragment();
        Bundle args = new Bundle();

        args.putSerializable(ARG_PARAM1, (Serializable) meals);
        args.putSerializable(ARG_PARAM2, (Serializable) userSelection);
//        args.putSerializable(ARG_PARAM3, (Serializable) selectedMealList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mealList = (List<Meal>) getArguments().getSerializable(ARG_PARAM1);
            userSelection = (List<String>) getArguments().getSerializable(ARG_PARAM2);
//            selectedMealList = (List<Meal>) getArguments().getSerializable(ARG_PARAM3);
        }
//        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_all_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allMealsRecyclerView = view.findViewById(R.id.display_all_meal_recyclerview);
        //do i need to make another call? how do i get the list of meals from ChooseOptionFragment class and the userSelection from same class

//        Retrofit retrofit = RetrofitSingleton.getInstance();
//        MealServices mealServices = retrofit.create(MealServices.class);
//        Single<MealList> mealCall = mealServices.getMealList();
//        compositeDisposable.add(mealCall.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(MealList::getMeals) // equivalent to mealList -> mealList.getMeals()
//                .subscribe(meals -> {
////                            List<Meal> mealList = meal.getMeals();
////                            Log.d("TAG", "onResponse" + meal.getMeals().get(0).getKeywords());
//                            for (Meal meal1 : meals) {
//                                //.contains userSelection List from ChooseOptionFragment
////                                if(meal1.getKeywords().contains()){
////                                mealList.add(meal1);
////                                }
//                            }
//
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                            DisplayAllMealsAdapter adapter = new DisplayAllMealsAdapter(mealList);
//                            allMealsRecyclerView.setAdapter(adapter);
//                            allMealsRecyclerView.setLayoutManager(linearLayoutManager);
//
//                        },
//                        throwable -> Log.d("TAG", "onFailure" + throwable)
//                ));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DisplayAllMealsAdapter adapter = new DisplayAllMealsAdapter(mealList);
        allMealsRecyclerView.setAdapter(adapter);
        allMealsRecyclerView.setLayoutManager(linearLayoutManager);
    }


    //grab list of meals, remove everything thats not the userselection then return the list (make its own method)
    //if the Meal contain the keyword that contains the userSelection, add that meal into my new List
    //   then add the Meal
//    public List<Meal> returnSelectedMeals(List<Meal> mealList, List<String> userSelection) {
//        this.mealList = mealList;
//        this.userSelection = userSelection;
//
//        for (int i = 0; i < mealList.size(); i++) {
//            List<Meal> mealsContainingUserSelection = new ArrayList<>();
//            if(mealList.get(i).getKeywords().contains(userSelection)){
//                mealsContainingUserSelection.add(mealList.get(i));
//                Log.d("TAG", "selectedMeals" + selectedMealList);
//            }
//        }
//        return selectedMealList;
//    }

    @Override
    public void toDisplayAllMealFragment(List<Meal> meals, List<String> userSelection) {
        List<Meal> mealsContainingUserSelection = new ArrayList<>();
        for (int i = 0; i < mealList.size(); i++) {
            if(mealList.get(i).getKeywords().contains(userSelection)){
                mealsContainingUserSelection.add(mealList.get(i));
                Log.d("TAG", "selectedMeals" + selectedMealList);
            }
        }
//        return mealsContainingUserSelection;
    }
}
