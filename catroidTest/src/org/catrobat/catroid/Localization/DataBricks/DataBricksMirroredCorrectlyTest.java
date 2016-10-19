package org.catrobat.catroid.Localization.DataBricks;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.catrobat.catroid.Localization.MirroringAssertions.UserBrickLayoutShouldBeMirrored;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

/**
 * Created by Aiman Awwad on 10/17/2016.
 */
@RunWith(AndroidJUnit4.class)

public class DataBricksMirroredCorrectlyTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    private MainMenuActivity mActivity;
    private Sprite sprite1,sprite2;
    String variableName, variableValue;



    public DataBricksMirroredCorrectlyTest() {
        super(MainMenuActivity.class);
    }
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createProject();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Before
    public void initValidString() {
        // Specify a valid string.
        variableName = "Variable1";
        variableValue="Hello";
    }

    @Test
    public void assertBricksIsDisplayedCorrectlyInRTLMode()
    {
        Espresso.onView(withId(R.id.main_menu_button_continue)).perform(ViewActions.click());
        Espresso.onView(withText("كائن1")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.program_menu_button_scripts)).perform(ViewActions.click());


        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_set_variable)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withId(R.id.brick_set_variable_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));


        Espresso.onView(withId(R.id.set_variable_spinner)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.dialog_formula_editor_data_name_edit_text))
                .perform(typeTextIntoFocusedView(variableName));
        Espresso.onView(withId(R.id.dialog_formula_editor_data_name_local_variable_radio_button)).perform(ViewActions.click());
        Espresso.closeSoftKeyboard();
        ViewInteraction Ok_button=onView(withText(R.string.ok));
        Ok_button.perform(ViewActions.click());
        Espresso.pressBack();

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_change_variable)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withId(R.id.brick_change_variable_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));


        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_show_text_var)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withId(R.id.brick_show_text_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_add_item_to_userlist_add)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.swipeUp());
        Espresso.onView(withText(R.string.brick_delete_item_from_userlist_delete)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.swipeUp());
        Espresso.onView(withText(R.string.brick_insert_item_into_userlist_insert_into)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.swipeUp());
        Espresso.onView(withText(R.string.brick_replace_item_in_userlist_replace_in_list)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());

        Espresso.onView(withId(R.id.brick_add_item_to_userlist_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));
        Espresso.onView(withId(R.id.brick_delete_item_of_userlist_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));
        Espresso.onView(withId(R.id.brick_insert_item_into_userlist_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));
        Espresso.onView(withId(R.id.brick_replace_item_in_userlist_layout)).check(matches(UserBrickLayoutShouldBeMirrored(Boolean.TRUE)));

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