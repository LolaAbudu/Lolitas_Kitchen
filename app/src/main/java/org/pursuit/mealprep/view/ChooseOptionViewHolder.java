package org.pursuit.mealprep.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import org.pursuit.mealprep.R;
import org.pursuit.mealprep.fragments.IngredientSelectedListener;

public class ChooseOptionViewHolder extends RecyclerView.ViewHolder {
    private CheckedTextView ingredientCheckBox;

    public ChooseOptionViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredientCheckBox = itemView.findViewById(R.id.checked_text_view);
    }

    //ask if i should be passing the list of ingredients here
    public void onBind(IngredientSelectedListener listener, final String ingredient) {
        ingredientCheckBox.setText(ingredient);
        ingredientCheckBox.setOnClickListener(item -> {
            if (ingredientCheckBox.isChecked()) {
                ingredientCheckBox.setChecked(false);
                listener.remove(ingredient);
            } else {
                ingredientCheckBox.setChecked(true);
                listener.addItem(ingredient);
            }
        });
    }
}
