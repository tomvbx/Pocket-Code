package org.catrobat.catroid.uitest.ui.menu;

/**
 * Created by Hrvoje on 20.4.2016..
 */
import android.test.UiThreadTest;
import android.widget.EditText;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.NoteBrick;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.ProgramMenuActivity;
import org.catrobat.catroid.ui.ProjectActivity;
import org.catrobat.catroid.ui.dialogs.NewStringDialog;
import org.catrobat.catroid.ui.fragment.FormulaEditorFragment;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;
public class ProgramMenuRtlTest extends BaseActivityInstrumentationTestCase<ProgramMenuActivity>  {

    public ProgramMenuRtlTest() {
        super(ProgramMenuActivity.class);
    }

    @UiThreadTest
    public void testShouldNotCrashIfProjectIsNull() {
        ProjectManager.getInstance().setProject(null);
        getInstrumentation().callActivityOnPostCreate(getActivity(), null);
        assertTrue("Test failed!", true);
    }

    public void testContinueButtonIsBiggerThanOthers() {
        int menuButtonLooksPaddingRight = getActivity().findViewById(R.id.program_menu_button_looks).getPaddingRight();
        float continueButtonHeight = getActivity().findViewById(R.id.main_menu_button_continue).getHeight();
        float newButtonHeight = getActivity().findViewById(R.id.main_menu_button_new).getHeight();
        float programsButtonHeight = getActivity().findViewById(R.id.main_menu_button_programs).getHeight();
        float helpButtonHeight = getActivity().findViewById(R.id.main_menu_button_help).getHeight();
        float communityButtonHeight = getActivity().findViewById(R.id.main_menu_button_web).getHeight();
        float uploadButtonHeight = getActivity().findViewById(R.id.main_menu_button_upload).getHeight();

        final String message = "Button heights are not in the correct relation to each other!";
        //assertEquals(message, 1.5, continueButtonHeight / newButtonHeight, 0.05);
        assertEquals(message, 1.5, continueButtonHeight / programsButtonHeight, 0.05);
        assertEquals(message, 1.5, continueButtonHeight / helpButtonHeight, 0.05);
        assertEquals(message, 1.5, continueButtonHeight / communityButtonHeight, 0.05);
        assertEquals(message, 1.5, continueButtonHeight / uploadButtonHeight, 0.05);
    }
}
