package org.pursuit.mealprep.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.controller.ChooseOptionAdapter;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.model.MealList;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;
import org.pursuit.mealprep.network.MealServices;
import org.pursuit.mealprep.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ChooseOptionFragment extends Fragment {
    private static final String INGREDIENTS = "ingredients";
    private CheckBox ingredientsCheckbox;
    private String ingredient;
    private ChooseOptionItemClickListener listener;
    Meal meal;

    ArrayList<String> ingredients = new ArrayList<>();
    CheckedTextView checkedTextView;
    ArrayList<String> userSelections = new ArrayList<>();

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
        if (getArguments() != null) {
            ingredient = getArguments().getString(INGREDIENTS);
        }

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

        //ingredientsCheckbox = view.findViewById(R.id.ingredient_checkbox);
        checkedTextView = view.findViewById(R.id.checked_text_view);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

//        List<String> items = new ArrayList<>();
        //find out how to save my keywords in this list/ or should it be an array of strings rather than a list
        //String[] items = meal.getKeywords();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.choose_option_itemview, R.id.checked_text_view, items);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        mealCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> {
                            List<Meal> mealList = meal.getMeals();
                            Log.d("TAG", "onResponse" + meal.getMeals().get(0).getKeywords());

                            List<String> items = new ArrayList<>();
                            for (Meal meal1 : mealList) {
                                items.addAll(meal1.getKeywords());
                            }

                            Set<String> singleItems = new HashSet<>();
                            for(String meal2 : items){
                                singleItems.add(meal2);
                            }

                            List<String> listItem = new ArrayList<>();
                            for(String meal3 : singleItems){
                                listItem.add(meal3);
                            }

                            Log.d("TAG", "size" + items.size());
                            Log.d("TAG", "first" + items.get(0));
                            Log.d("TAG", "last" + items.get(142));

                            Log.d("TAG", "newList " + listItem.size());
                            Log.d("TAG", "13 " + listItem.get(13));

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.choose_option_itemview, R.id.checked_text_view, listItem);
                            listView.setAdapter(adapter);
//                            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedItem = listItem.get(position);
                                    if (ingredients.contains(selectedItem)) {
                                        ingredients.remove(selectedItem);
                                        userSelections.add(selectedItem);
                                        Toast.makeText(getContext(), selectedItem + " has been un-selected", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ingredients.add(selectedItem);
                                        userSelections.remove(selectedItem);
                                        Toast.makeText(getContext(), selectedItem + " has been selected", Toast.LENGTH_SHORT).show();
                                    }
                                    Log.d("TAG", "userSelection " + userSelections);

                                }
                            });
                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable)
                );
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
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
//
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
