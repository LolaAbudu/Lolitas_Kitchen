package org.pursuit.mealprep.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.model.Meal;
import org.pursuit.mealprep.network.ChooseOptionItemClickListener;

import java.util.List;

public class ChooseOptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private CheckBox ingredientCheckBox;
    ChooseOptionItemClickListener listener;

    public ChooseOptionViewHolder(@NonNull View itemView) {
        super(itemView);
        //ingredientCheckBox = itemView.findViewById(R.id.ingredient_checkbox);
    }

    //ask if i should be passing the list of ingredients here
    public void onBind(final Meal meal){
        List<String> allIngredients = meal.getKeywords();
        Log.d("TAG", "all" + allIngredients);
        ingredientCheckBox.setText(allIngredients.get(0));

        ingredientCheckBox.setOnClickListener(this);
    }

    public void setListener(ChooseOptionItemClickListener listener){
        this.listener = listener;
    }

    //returns current position of displayed items
    @Override
    public void onClick(View v) {
        this.listener.onIngredientClick(v, getLayoutPosition());
    }
}
