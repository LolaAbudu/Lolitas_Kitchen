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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.R;
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

public class TypeInFragment extends Fragment implements IngredientSelectedListener {

    private FragmentInteractionListener vpListener;
    private CompositeDisposable compositeDisposable;

    private Set<String> ingredients = new TreeSet<>();
    private ArrayList<String> userSelections = new ArrayList<>();
    private ArrayList<Meal> listOfMeals = new ArrayList<>();
    private List<Ingredient> uniqueIngredientList;

    private ListView userSelectionsListView;
    private EditText userIngredientsEditText;

    private ArrayAdapter<String> adapter;
    private List<String> newUserSelectionsList;

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
//        ingredientsRecyclerView = view.findViewById(R.id.ingredients_recycler_view);
        Button addButton = view.findViewById(R.id.type_in_add_button);
        Button showRecipeButton = view.findViewById(R.id.show_recipe_button);
        userSelectionsListView = view.findViewById(R.id.type_in_ingredients_listview);
        userIngredientsEditText = view.findViewById(R.id.type_in_edit_text);

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

                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable)
                ));

        addButton.setOnClickListener(v -> {
            newUserSelectionsList = new ArrayList<>();
            String userInput = userIngredientsEditText.getText().toString();

            if (newUserSelectionsList.contains(userInput)) {
                Toast.makeText(getContext(), "Ingredient has already been added", Toast.LENGTH_LONG).show();
            } else if (userInput == null || userInput.trim().equals("")) {
                Toast.makeText(getContext(), "Ingredient field is empty", Toast.LENGTH_LONG).show();
            } else {
                newUserSelectionsList.add(userInput.trim());
                adapter = new ArrayAdapter<>(getContext(), R.layout.type_in_itemview, R.id.type_in_user_input, newUserSelectionsList);
                adapter.notifyDataSetChanged();
                userSelectionsListView.setAdapter(adapter);

                userIngredientsEditText.setText("");
            }
        });

//        showRecipeButton.setOnClickListener(v -> {
//            if (vpListener != null) {
//                vpListener.toDisplayAllMealFragment(listOfMeals, newUserSelectionsList);
//            }
//        });
//        sendingUserToDisplayAllMealFragment();
    }

    private void transformingSetOfIngredientsIntoAList() {
        uniqueIngredientList = new ArrayList<>();
        for (String name : ingredients) {
            uniqueIngredientList.add(new Ingredient(name));
        }
    }

//    private void sendingUserToDisplayAllMealFragment() {
//        showRecipeButton.setOnClickListener(v -> {
//            if (vpListener != null) {
//                vpListener.toDisplayAllMealFragment(listOfMeals, newUserSelectionsList);
//            }
//        });
//    }

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

    @Override
    public void addItem(Ingredient ingredient) {
        userSelections.add(ingredient.name);
//        Toast.makeText(getContext(), ingredient + " has been add", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeItem(Ingredient ingredient) {
        //left empty
    }

//    @Override
//    public boolean onQueryTextSubmit(String s) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String s) {
//        //1- add every item inside of userSelections list into my newUserSelectionsList --might be redundant
//        //2- on Button click, if ingredients.getName.toLowerCase.contains(s)
//        //   then add that to my newUserSelectionsList
//        //3- setText to the newUserSelectionsList
//        List<Ingredient> newUserSelectionsList = new ArrayList<>();
//        for (Ingredient ingredients : uniqueIngredientList) {
////            newUserSelectionsList.add(ingredients);
//            addButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(ingredients.name.toLowerCase().contains(s.toLowerCase())){
//                        newUserSelectionsList.add(ingredients);
//                        userSelectionsTextView.setText(newUserSelectionsList.toString());
//                    }
//                }
//            });
//        }
//        return false;
//    }
}
