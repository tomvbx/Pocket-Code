package org.catrobat.catroid.uitest.ui.menu;

/**
 * Created by Hrvoje on 20.4.2016..
 */
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
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
public class ProgramMenuActivityTest extends ActivityInstrumentationTestCase2<ProgramMenuActivity> {

    public ProgramMenuActivityTest() {
        super(ProgramMenuActivity.class);
    }

    public void tearDown() throws Exception {
        super.tearDown();

    }

    public void testContinueButtonIsBiggerThanOthers() {

        Log.d("Message","test");
//        int menuButtonLooksPaddingRight = getActivity().findViewById(R.id.program_menu_button_looks).getPaddingRight();

    }
}
