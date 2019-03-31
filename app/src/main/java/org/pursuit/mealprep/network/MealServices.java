package org.pursuit.mealprep.network;

import org.pursuit.mealprep.model.MealList;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface MealServices {

    String END_POINT = "LolaAbudu/JsonFiles/master/meals.json";

    @GET(END_POINT)
    Single<MealList> getMealList();
}
