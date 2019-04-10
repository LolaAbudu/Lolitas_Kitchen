package org.pursuit.mealprep.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.R;
import org.pursuit.mealprep.controller.ChooseOptionAdapter;
import org.pursuit.mealprep.model.Ingredient;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.MealList;
import org.pursuit.mealprep.network.MealServices;
import org.pursuit.mealprep.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ChooseOptionFragment extends Fragment implements IngredientSelectedListener {
    private FragmentInteractionListener vpListener;
    private CompositeDisposable compositeDisposable;
    private RecyclerView ingredientsRecyclerView;

    private ArrayList<String> userSelections = new ArrayList<>();
    private ArrayList<Meal> listOfMeals = new ArrayList<>();
    private List<Ingredient> uniqueIngredientList;
    private Set<String> ingredients = new TreeSet<>();

    private Button showRecipeButton;

    public ChooseOptionFragment() { }

    public static ChooseOptionFragment newInstance() {
        ChooseOptionFragment fragment = new ChooseOptionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_option, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredientsRecyclerView = view.findViewById(R.id.ingredients_recycler_view);
        showRecipeButton = view.findViewById(R.id.show_recipe_button);

        Retrofit retrofit = RetrofitSingleton.getInstance();
        MealServices mealServices = retrofit.create(MealServices.class);
        Single<MealList> mealCall = mealServices.getMealList();
        compositeDisposable.add(mealCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(MealList::getMeals) // equivalent to mealList -> mealList.getMeals()
                .subscribe(meals -> {

                            listOfMeals.clear();

                            addsAllUniqueIngredients(meals);

                            listOfMeals.addAll(meals);

                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            ingredientsRecyclerView.setLayoutManager(gridLayoutManager);

                            transformingSetOfIngredientsIntoAList();

                            ChooseOptionAdapter adapter = new ChooseOptionAdapter(this, uniqueIngredientList);
                            ingredientsRecyclerView.setAdapter(adapter);

                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable)
                ));
        sendingUserToDisplayAllMealFragment();
    }

    private void transformingSetOfIngredientsIntoAList() {
        uniqueIngredientList = new ArrayList<>();
        for (String name : ingredients) {
            uniqueIngredientList.add(new Ingredient(name));
        }
    }

    private void sendingUserToDisplayAllMealFragment() {
        showRecipeButton.setOnClickListener(v -> {
            if (vpListener != null) {
                vpListener.toDisplayAllMealFragment(listOfMeals, userSelections);
            }
        });
    }

    public void addsAllUniqueIngredients(List<Meal> meals) {
        for (Meal meal1 : meals) {
            ingredients.addAll(meal1.getKeywords());
        }
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
    public void onResume() {
        super.onResume();
        userSelections.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        vpListener = null;
    }

    @Override
    public void addItem(Ingredient ingredient) {
        userSelections.add(ingredient.name);
        Toast.makeText(getContext(), ingredient + " has been selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeItem(Ingredient ingredient) {
        userSelections.remove(ingredient.name);
        Toast.makeText(getContext(), ingredient + " has been un-selected", Toast.LENGTH_SHORT).show();
    }
}
