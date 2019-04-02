package org.pursuit.mealprep.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.controller.ChooseOptionAdapter;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.MealList;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;
import org.pursuit.mealprep.network.MealServices;
import org.pursuit.mealprep.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.support.constraint.Constraints.TAG;

public class ChooseOptionFragment extends Fragment implements IngredientSelectedListener {
    private static final String INGREDIENTS = "ingredients";
    private ChooseOptionItemClickListener listener;
    private CompositeDisposable compositeDisposable;
    Meal meal;

    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> userSelections = new ArrayList<>();

//    ChooseOptionAdapter chooseAdapter;
//    RecyclerView chooseRecyclerView;


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
        RecyclerView ingredientsRecyclerView = view.findViewById(R.id.ingredients_recycler_view);

//        List<String> items = new ArrayList<>();
        //find out how to save my keywords in this list/ or should it be an array of strings rather than a list
        //String[] items = meal.getKeywords();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.choose_option_itemview, R.id.checked_text_view, items);
//        ingredientsRecyclerView.setAdapter(adapter);
//        ingredientsRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = view.toString();
//                if (ingredients.contains(selectedItem)) {
//                    ingredients.remove(selectedItem);
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

                            for (Meal meal1 : meals) {
                                ingredients.addAll(meal1.getKeywords());
                            }

                            Set<String> uniqueIngredients = new HashSet<>(ingredients);
                            String[] ingredientsList = uniqueIngredients.toArray(new String[0]);
//                            Log.d("TAG", "size" + items.size());
//                            Log.d("TAG", "first" + items.get(0));
//                            Log.d("TAG", "last" + items.get(142));
//
//                            Log.d("TAG", "newList " + listItem.size());
//                            Log.d("TAG", "13 " + listItem.get(13));
                            ChooseOptionAdapter adapter = new ChooseOptionAdapter(this, Arrays.asList(ingredientsList));
                            Log.d(TAG, "onViewCreated: " + ingredients.size());
                            ingredientsRecyclerView.setAdapter(adapter);
                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable)
                ));
    }

    //Log.d("TAG", "userSelection " + userSelections);
//                                        checkedTextView.setCheckMarkDrawable(R.drawable.ic_check);

    //                                        checkedTextView.setChecked(false);


//    public void showSelectedItems(View view) {
//        String items = "";
//        for (String item : ingredients) {
//            items += " " + item + "\n";
//        }
//        Toast.makeText(getContext(), "You have selected \n" + items, Toast.LENGTH_SHORT).show();
//    }

    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            //listener.onIngredientClick(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChooseOptionItemClickListener) {
            listener = (ChooseOptionItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void addItem(String ingredient) {
        userSelections.add(ingredient);
        Toast.makeText(getContext(), ingredient + " has been un-selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void remove(String ingredient) {
        userSelections.remove(ingredient);
        Toast.makeText(getContext(), ingredient + " has been selected", Toast.LENGTH_SHORT).show();
    }

//
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
