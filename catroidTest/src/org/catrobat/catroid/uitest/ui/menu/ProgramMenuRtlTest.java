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

    }
}
