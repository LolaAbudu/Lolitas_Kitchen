package org.pursuit.mealprep.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.model.NutritionFacts;
import org.pursuit.mealprep.model.Time;

import java.util.ArrayList;
import java.util.List;

public class SelectedMealFragment extends Fragment {

    private static final String NAME_KEY = "name";
    private static final String IMAGE_KEY = "image";
    private static final String DESCRIPTION_KEY = "description";
    private static final String INGREDIENTS_KEY = "ingredients";
    private static final String DIRECTION_KEY = "direction";
    private static final String NUTRITIONAL_FACT_KEY = "nutritionalFacts";
    private static final String COOK_TIME_KEY = "cookTime";

    private String name;
    private String image;
    private String description;
    private List<String> ingredients;
    private List<String> direction;
    private NutritionFacts nutritionalFacts;
    private Time cookTime;

    private TextView nameTextView;
    private ImageView imageImageView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;
    private TextView directionTextView;
    private TextView nutritionalFactsTextView;
    private TextView cookTimeTextView;
    private Button emailButton;

    public SelectedMealFragment() { }

    public static SelectedMealFragment newInstance(String name,
                                                   String image,
                                                   String description,
                                                   List<String> ingredients,
                                                   List<String> direction,
                                                   NutritionFacts nutritionalFacts,
                                                   Time cookTime) {
        SelectedMealFragment fragment = new SelectedMealFragment();
        Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(IMAGE_KEY, image);
        args.putString(DESCRIPTION_KEY, description);
        args.putStringArrayList(INGREDIENTS_KEY, (ArrayList<String>) ingredients);
        args.putStringArrayList(DIRECTION_KEY, (ArrayList<String>) direction);
        args.putSerializable(NUTRITIONAL_FACT_KEY, nutritionalFacts);
        args.putSerializable(COOK_TIME_KEY, cookTime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            image = getArguments().getString(IMAGE_KEY);
            description = getArguments().getString(DESCRIPTION_KEY);
            ingredients = getArguments().getStringArrayList(INGREDIENTS_KEY);
            direction = getArguments().getStringArrayList(DIRECTION_KEY);
            nutritionalFacts = (NutritionFacts) getArguments().getSerializable(NUTRITIONAL_FACT_KEY);
            cookTime = (Time) getArguments().getSerializable(COOK_TIME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selected_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewsById(view);
        displayView();
        emailButtonClick();
    }

    private void findViewsById(@NonNull View view) {
        nameTextView = view.findViewById(R.id.selected_meal_name_textView);
        imageImageView = view.findViewById(R.id.selected_meal_image_imageView);
        descriptionTextView = view.findViewById(R.id.selected_meal_description_textView);
        ingredientsTextView = view.findViewById(R.id.selected_meal_ingredients_textView);
        directionTextView = view.findViewById(R.id.selected_meal_direction_textView);
        nutritionalFactsTextView = view.findViewById(R.id.selected_meal_nutritionalFacts_textView);
        cookTimeTextView = view.findViewById(R.id.selected_meal_cookTime_textView);
        emailButton = view.findViewById(R.id.selected_email_button);
    }

    private void displayView() {
        Picasso.get().load(image).into(imageImageView);
        nameTextView.setText(name);
        descriptionTextView.setText(description);

        for(int i = 0; i < ingredients.size(); i++) {
            String ingredients = ingredientsTextView.getText()+ this.ingredients.get(i);
            ingredientsTextView.setText(ingredients);
        }
        for(int i = 0; i < direction.size(); i++) {
            String directions = directionTextView.getText()+ "\n" + direction.get(i);
            directionTextView.setText(directions);
        }

        String nutrition = "Fat: "+nutritionalFacts.getFat() + " \nCalories: " + nutritionalFacts.getCalories() + ", \nCarbohydrates: " + nutritionalFacts.getCarbohydrates()+", \nCholesterol: " + nutritionalFacts.getCholesterol();
        nutritionalFactsTextView.setText(nutrition);
        String cookingTime = "Prep Time: " + cookTime.getPrepTime() + "Cook Time: " + cookTime.getCookTime() + "Total Time: " + cookTime.getTotalTime();
        cookTimeTextView.setText(cookingTime);
    }

    private void emailButtonClick() {
        emailButton.setOnClickListener(v -> {
            String email = "lolabunny1206@yahoo.com";
            String subject = "Meal: " + name;
            String body = name + "/n" + image + "/n" + description + "/n" + ingredients + "/n" + direction;
            String title = "Lola Meal Prep";

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);

            startActivity(Intent.createChooser(emailIntent, title));
        });
    }
}
