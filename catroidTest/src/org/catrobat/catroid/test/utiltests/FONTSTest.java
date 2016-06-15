package org.catrobat.catroid.test.utiltests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.catrobat.catroid.CF.Change_Fonts;
import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.SettingsActivity;

/**
 * Created by NurELdin on 05/25/2016.
 */
public class FONTSTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {


    private Solo solo;


    public FONTSTest() {
        super(MainMenuActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testfont_tahoma (){
        solo.sendKey(solo.MENU);
        solo.clickOnMenuItem(solo.getString(R.string.settings));
        solo.waitForActivity(SettingsActivity.class);
        solo.clickOnMenuItem(solo.getString(R.string.preference_title_change_font));
        solo.waitForActivity(Change_Fonts.class);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ONLY_FONTS", Context.MODE_PRIVATE);
        String dd = sharedPreferences.getString("TTF","");
        solo.clickOnMenuItem(solo.getString(R.string.Tahoma));
        solo.finishOpenedActivities();
        Intent intent = new Intent(getActivity(),MainMenuActivity.class);
        getActivity().startActivity(intent);
       String exp =sharedPreferences.getString("TTF","");
        assertNotSame(dd,exp);







    }
}
