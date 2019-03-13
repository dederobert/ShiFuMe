package fr.m2ihm.a1819.shi_fu_me;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.m2ihm.a1819.shi_fu_me.activities.InGameActivity;

@RunWith(AndroidJUnit4.class)
public class InGameActivityInstrumentedTest  {

    @Rule
    public final ActivityTestRule<InGameActivity> activityTestRule = new ActivityTestRule<>(InGameActivity.class, true, true);

    @Test
    public void scoreInitial0()  {
        InGameActivity  activity = activityTestRule.getActivity();
        Assert.assertEquals(activity.getTxt_your_score().getText().toString(), "Votre score 0");
        Assert.assertEquals(activity.getTxt_adv_score().getText().toString(), "Score adversaire 0");
    }

    @Test
    public void imgSetOnClickScissor() {
        InGameActivity activity = activityTestRule.getActivity();
        activity.clickBtnCiseaux();
        Assert.assertEquals(activity.getImg_you().getDrawable(), InstrumentationRegistry.getTargetContext().getDrawable(R.drawable.ciseaux));
    }

    @Test
    public void imgSetOnClickPaper() {
        InGameActivity activity = activityTestRule.getActivity();
        activity.clickBtnFeuille();
        Assert.assertEquals(activity.getImg_you().getDrawable(), InstrumentationRegistry.getTargetContext().getDrawable(R.drawable.feuille));
    }

    @Test
    public void imgSetOnClickStone() {
        InGameActivity activity = activityTestRule.getActivity();
        activity.clickBtnPierre();
        Assert.assertEquals(activity.getImg_you().getDrawable(), InstrumentationRegistry.getTargetContext().getDrawable(R.drawable.pierre));
    }
}
