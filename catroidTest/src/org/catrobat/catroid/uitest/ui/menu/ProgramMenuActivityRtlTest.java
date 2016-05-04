package org.catrobat.catroid.uitest.ui.menu;

import android.util.Log;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.ProgramMenuActivity;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Hrvoje on 27.4.2016..
 */
public class ProgramMenuActivityRtlTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {
    public ProgramMenuActivityRtlTest() {
        super(MainMenuActivity.class);
    }

    private Solo mySolo;

    public void setUp () throws Exception {
        super.setUp ();
        mySolo = new Solo( getInstrumentation (), getActivity ());
    }
    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testScriptButton() {

      //  Log.d("Message", "test");
//        int menuButtonLooksPaddingRight = getActivity().findViewById(R.id.program_menu_button_looks).getPaddingRight();
        mySolo.goBackToActivity("MainMenuActivity");
        mySolo.clickOnButton(getActivity().getString(R.string.main_menu_programs));
        UiTestUtils.clickOnTextInList(mySolo, mySolo.getString(R.string.default_project_name));
        UiTestUtils.clickOnTextInList(mySolo, mySolo.getString(R.string.default_project_cloud_sprite_name_1));
        //mySolo.clickOnButton(getActivity().getString(R.string.default_project_cloud_sprite_name_1));
       // mySolo.clickOnText(getActivity().getString(R.string.scripts));
        UiTestUtils.clickOnTextInList(mySolo, mySolo.getString(R.string.scripts));



}
        }
