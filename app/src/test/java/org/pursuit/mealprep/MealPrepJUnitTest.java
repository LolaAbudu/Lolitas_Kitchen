package org.pursuit.mealprep;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.pursuit.mealprep.fragments.DisplayAllMealsFragment;
import org.pursuit.mealprep.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealPrepJUnitTest {
    //private ChooseOptionFragment chooseOptionFragment;
    private DisplayAllMealsFragment displayAllMealsFragment;
    //private String ingredient;
    private List<Meal> mealList = new ArrayList<>();
    private List<String> userIngredientSelectionList = new ArrayList<>();
    private List<Meal> mealWithUserSelectionList = new ArrayList<>();

    @Mock
    public Meal meal = new Meal();

    @Before
    public void setUp() throws Exception{
        //chooseOptionFragment = ChooseOptionFragment.newInstance(ingredient);
        displayAllMealsFragment = DisplayAllMealsFragment.newInstance(mealList, userIngredientSelectionList);
    }
//
//    @Test
//    public void check_list_of_meal_size(){
//        //Given
//        List<String> ingredients = new ArrayList<>();
//        ingredients.add("bacon");
//        ingredients.add("beef");
//        ingredients.add("fish");
//
//        //When
//        boolean actual = chooseOptionFragment.(numbers);
//
//        //Then
//        Assert.assertTrue(actual);
//    }

//    @Test
//    public void check_list_only_receives_unique_ingredients(){
//
//        meal = new Meal(spy(new Meal()));
//        mealList.add(meal);
//        //Given
////        mealList.add(meal);
//
//        //List<String> ingredients = new ArrayList<>();
//        userSelectionList.add("bacon");
//        userSelectionList.add("beef");
//        userSelectionList.add("fish");
//        userSelectionList.add("fish");
//
//        //When
//
//
//        //String[] actual = arrayMethodTesting.smallEvensOnly(input);
//        List<String> expected = Arrays.asList("bacon", "beef", "fish");
//
//        List<String> actual = chooseOptionFragment.addsAllUniqueIngredients(meal);
//
//        //Then
//        Assert.assertEquals(expected, actual);
//    }

    //TODO try to fix below test--Bryant side to make the method return a List, for this to work
//    @Test
//    public void check_unique_List(){
//        //Given
//        userIngredientSelectionList.add("bacon");
//        userIngredientSelectionList.add("bacon");
//        userIngredientSelectionList.add("beef");
//
//        //when
//        List<Meal> actual = mealWithUserSelectionList.size() = 124;
//        List<Meal> expected = displayAllMealsFragment.addUniqueMealsWithSelectedIngredients();
//
//        //Then
//        Assert.assertEquals(actual, expected);
//    }
}
