package org.catrobat.catroid.Localization.ViewsTextOrientation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.uitest.util.UiTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.catrobat.catroid.Localization.ButtonTextDirection.buttonShouldHaveDirection;
import static org.catrobat.catroid.Localization.EditTextDirection.editTextShouldHaveDirection;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

/**
 * Created by Aiman Awwad on 10/19/2016.
 */
@RunWith(AndroidJUnit4.class)

public class ButtonTextDirectionTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    private MainMenuActivity mActivity;
    private Sprite sprite1,sprite2;
    String variableName, variableValue;



    public ButtonTextDirectionTest() {
        super(MainMenuActivity.class);
    }
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createProject();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void assertFormulaButtonsIsRTL() {
        Espresso.onView(withId(R.id.main_menu_button_continue)).perform(ViewActions.click());
        Espresso.onView(withText("كائن1")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.program_menu_button_scripts)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_change_x_by)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withId(R.id.brick_change_x_edit_text)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.formula_editor_keyboard_1)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.formula_editor_keyboard_5)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.formula_editor_keyboard_object)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.formula_editor_keyboard_logic)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
    }


    @Test
    public void assertMainMenuActivityButtonsTextIsRTLDirection()
    {
        Espresso.onView(withId(R.id.main_menu_button_continue)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.main_menu_button_new)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.main_menu_button_programs)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.main_menu_button_help)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.main_menu_button_web)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.main_menu_button_upload)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
    }



    @Test
    public void assertProgramMenuButtonsTextDirectionIsRT()
    {
        Espresso.onView(withId(R.id.main_menu_button_continue)).perform(ViewActions.click());
        Espresso.onView(withText("كائن1")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.program_menu_button_scripts)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.program_menu_button_looks)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.program_menu_button_sounds)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
        Espresso.onView(withId(R.id.program_menu_button_nfctags)).check(matches(buttonShouldHaveDirection(View.TEXT_DIRECTION_FIRST_STRONG)));
    }

    private void createProject() {
        Project project = new Project(null, UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
        sprite1 = new Sprite("كائن1");
        sprite2=new Sprite("كائن2");
        Script script = new StartScript();
        sprite1.addScript(script);
        sprite2.addScript(script);
        project.addSprite(sprite1);
        project.addSprite(sprite2);
        ProjectManager.getInstance().setProject(project);
        ProjectManager.getInstance().setCurrentSprite(sprite1);
        ProjectManager.getInstance().setCurrentSprite(sprite2);
        ProjectManager.getInstance().setCurrentScript(script);
    }

}





