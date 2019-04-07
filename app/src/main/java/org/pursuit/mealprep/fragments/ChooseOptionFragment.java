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

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.ViewPagerFragmentInteractionListener;
import org.pursuit.mealprep.controller.ChooseOptionAdapter;
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
    private static final String INGREDIENTS = "ingredients";
    private ViewPagerFragmentInteractionListener vpListener;

    private CompositeDisposable compositeDisposable;

    private Set<String> ingredients = new TreeSet<>();
    private ArrayList<String> userSelections = new ArrayList<>();
    private ArrayList<Meal> listOfMeals = new ArrayList<>();
    private List<Ingredient> ingredientModels;

    private RecyclerView ingredientsRecyclerView;

    private Button showRecipeButton;

    public ChooseOptionFragment() {
        // Required empty public constructor
    }

    public static ChooseOptionFragment newInstance(String ingredient) {
        ChooseOptionFragment fragment = new ChooseOptionFragment();
        Bundle args = new Bundle();
        args.putString(INGREDIENTS, ingredient);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }
//    private List<Meal> getIngredients(){
//        List<Meal> ingredients = new ArrayList<>();
//        meal = new Meal(meal.getKeywords())
//        ingredients.add();
//        return;
//    }

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
//        List<String> items = new ArrayList<>();
//        find out how to save my keywords in this list/ or should it be an array of strings rather than a list
//        String[] items = meal.getKeywords();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.choose_option_itemview, R.id.checked_text_view, items);
//        ingredientsRecyclerView.setAdapter(adapter);
//        ingredientsRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = view.toString();
//                if (ingredients.contains(selectedItem)) {
//                    ingredients.removeItem(selectedItem);
//                }else{
//                    ingredients.add(selectedItem);
//                }
//            }
//        });

//        chooseRecyclerView = view.findViewById(R.id.choose_option_recyclerview);
//        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        chooseAdapter = new ChooseOptionAdapter(new ArrayList<>());
//        chooseRecyclerView.setLayoutManager(gridLayoutManager);
//        chooseRecyclerView.setAdapter(chooseAdapter);


        //do retrofit call here
        Retrofit retrofit = RetrofitSingleton.getInstance();
        MealServices mealServices = retrofit.create(MealServices.class);
        Single<MealList> mealCall = mealServices.getMealList();
        compositeDisposable.add(mealCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(MealList::getMeals) // equivalent to mealList -> mealList.getMeals()
                .subscribe(meals -> {
//                            List<Meal> mealList = meal.getMeals();
//                            Log.d("TAG", "onResponse" + meal.getMeals().get(0).getKeywords());

                    //TODO for loop should have an if check that check if the meal.getKeyword.contains(checkedbox strings), send them to the next fragment
                            for (Meal meal1 : meals) {
                                ingredients.addAll(meal1.getKeywords());
                            }

                            listOfMeals.addAll(meals);

                            //TODO Questions, one how to save the list in alphabetical order
                            //TODO how to save checkedBox position in a RecyclerView

                            // No longer need to convert from ArrayList to Set, since
                            // we now declare the ingredients field to be a HashSet above.
                            //Set<String> uniqueIngredients = new HashSet<>(ingredients);

                            // No longer needed, since you can convert from a HashSet to an
                            // ArrayList directly using new ArrayList(
                            //String[] ingredientsList = ingredients.toArray(new String[0]);

                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            ingredientsRecyclerView.setLayoutManager(gridLayoutManager);

                            ingredientModels = new ArrayList<>();
                            for (String name : ingredients) {
                                ingredientModels.add(new Ingredient(name));
                            }

                            ChooseOptionAdapter adapter = new ChooseOptionAdapter(this, ingredientModels);
                            Log.d("TAG", "onViewCreated: " + ingredients.size());
                            ingredientsRecyclerView.setAdapter(adapter);

                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable)
                ));

                showRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vpListener != null){
                    vpListener.toDisplayAllMealFragment(listOfMeals, userSelections);
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof ViewPagerFragmentInteractionListener){
            vpListener = (ViewPagerFragmentInteractionListener) context;
        }else {
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
        Log.d("TAG", "uresSelectionList" + userSelections);
    }

    @Override
    public void removeItem(Ingredient ingredient) {
        userSelections.remove(ingredient.name);
        Toast.makeText(getContext(), ingredient + " has been un-selected", Toast.LENGTH_SHORT).show();
    }
}
