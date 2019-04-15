package org.pursuit.mealprep.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.R;
import org.pursuit.mealprep.model.Ingredient;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.MealList;
import org.pursuit.mealprep.network.MealServices;
import org.pursuit.mealprep.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TypeInFragment extends Fragment {

    private FragmentInteractionListener vpListener;
    private CompositeDisposable compositeDisposable;
    private ArrayAdapter<String> adapter;

    private Set<String> ingredients = new TreeSet<>();
    private ArrayList<String> userSelections = new ArrayList<>();
    private ArrayList<Meal> listOfMeals = new ArrayList<>();
    private List<Ingredient> uniqueIngredientList;

    private AutoCompleteTextView ingredients_suggestion_box;
    private ListView userSelectionsListView;
    private Button showRecipeButton;


    public TypeInFragment() {
    }

    public static TypeInFragment newInstance() {
        TypeInFragment fragment = new TypeInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addButton = view.findViewById(R.id.type_in_add_button);
        showRecipeButton = view.findViewById(R.id.type_in_show_recipe_button);
        userSelectionsListView = view.findViewById(R.id.type_in_ingredients_listview);
        ingredients_suggestion_box = view.findViewById(R.id.suggestion_box);

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

                            transformingSetOfIngredientsIntoAList();

                            ArrayAdapter<Ingredient> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_dropdown_item, uniqueIngredientList);
                            ingredients_suggestion_box.setAdapter(adapter);

                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable)
                ));

        addButtonOnClickEvent(addButton);
        sendingUserToDisplayAllMealFragment();
    }

    private void addButtonOnClickEvent(Button addButton) {
        addButton.setOnClickListener(v -> {

            String userInput = ingredients_suggestion_box.getText().toString();

            for (Ingredient item : uniqueIngredientList) {
                if (!userSelections.contains(userInput)) {
                    userSelections.add(userInput);
                }
            }
            Log.d("TAG", "userSections: " + userSelections.toString());

            adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.type_in_itemview, R.id.type_in_user_input, userSelections);
            adapter.notifyDataSetChanged();
            userSelectionsListView.setAdapter(adapter);
        });
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
}
