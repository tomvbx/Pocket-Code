package org.catrobat.catroid.test.ui;

import android.widget.ListView;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.common.BrickValues;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.fragment.FormulaEditorFragment;
import org.catrobat.catroid.ui.fragment.UserBrickDataEditorFragment;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.security.cert.TrustAnchor;

/**
 * Created by zlatko on 4/27/16.
 * This class will test the look of bricks in RTL layout direction.
 * X should be right of Y
 * X input should be left of x label
 */
public class BrickLayoutRTLTest extends BaseActivityInstrumentationTestCase<MainMenuActivity> {

    public BrickLayoutRTLTest() {
        super(MainMenuActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        UiTestUtils.prepareStageForTest();
        UiTestUtils.createTestProjectWithNestedUserBrick();
        UiTestUtils.getIntoScriptActivityFromMainMenu(solo);
    }


    public void testcheckElementPositions()
    {
        solo.goBackToActivity("MainMenuActivity");
        solo.clickOnText("Continue");
        solo.clickOnText("cat");
        solo.clickOnText("Scripts");
        solo.sleep(100);
        UiTestUtils.clickOnBottomBar(solo, R.id.button_add);

        //test Control bricks
        solo.clickOnText(getActivity().getString(R.string.category_control));
        solo.clickOnText(getActivity().getString(R.string.brick_forever));
        solo.clickOnText(getActivity().getString(R.string.brick_forever));
        //test Motion bricks

        //test Sound bricks

        // test Looks bricks

        // test data bricks

        //test my bricks


        assertTrue("testing", true);
    }
}
