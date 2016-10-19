package org.catrobat.catroid.Localization.MotionBricks;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
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


import static android.support.test.espresso.assertion.LayoutAssertions.noEllipsizedText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.catrobat.catroid.Localization.LayoutDirectionAssertions.isLayoutDirectionRTL;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

/**
 * Created by Aiman Awwad on 10/04/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MotionBricksNoNullValueTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    private MainMenuActivity mActivity;
    private Sprite sprite1,sprite2;


    public MotionBricksNoNullValueTest() {
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
    public void assertNotNullValueInRTLMode()
    {
        Espresso.onView(withId(R.id.main_menu_button_continue)).perform(ViewActions.click());
        Espresso.onView(withText("كائن1")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.program_menu_button_scripts)).perform(ViewActions.click());


        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_place_at)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_place_at)).check(matches(notNullValue()));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_set_x)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_set_x)).check(matches(notNullValue()));


        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_set_y)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_set_y)).check(matches(notNullValue()));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_change_x_by)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_change_x_by)).check(matches(notNullValue()));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_move)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_move)).check(matches(notNullValue()));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.swipeUp());
        Espresso.onView(withText(R.string.brick_point_in_direction)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_point_in_direction)).check(matches(notNullValue()));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.swipeUp());
        Espresso.onView(withText(R.string.brick_turn_right)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_turn_right)).check(matches(notNullValue()));

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.swipeUp());
        Espresso.onView(withText(R.string.brick_glide)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_glide)).check(matches(notNullValue()));
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