package org.pursuit.mealprep;

import android.support.v4.app.Fragment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.pursuit.mealprep.fragments.ChooseOptionFragment;

public class MealPrepJUnitTest {

    private Fragment chooseOptionFragment;

    @Before
    public void setup(){
        chooseOptionFragment = ChooseOptionFragment.newInstance();
    }

    @Test
    public void check_choose_option_fragment_returns_a_fragment(){
        Fragment actual = chooseOptionFragment;
        Assert.assertNotNull(actual);
    }

    @After
    public void tearDown(){
        chooseOptionFragment = null;
    }
}
