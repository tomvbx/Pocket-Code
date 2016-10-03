package org.catrobat.catroid.Localization.Activity;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.PlaceAtBrick;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.uitest.util.UiTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.LayoutAssertions.noEllipsizedText;
import static android.support.test.espresso.assertion.LayoutAssertions.noOverlaps;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.catrobat.catroid.Localization.LayoutDirectionAssertions.isLayoutDirectionRTL;
import static org.catrobat.catroid.Localization.TextDirectionAssertions.isTextDirectionRTL;
import static org.catrobat.catroid.Localization.VisibilityAssertions.isVisible;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by aiman awwad on 10/3/2016.
 */
@RunWith(AndroidJUnit4.class)
public class FormulaEditorTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    ViewInteraction formulaEditorLayout;
    private Sprite sprite1;

    public FormulaEditorTest() {
        super(MainMenuActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createProject();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        addBrickChange_x_by();
        formulaEditorLayout= Espresso.onView(withId(R.id.FormulaEditorLayout));
    }

    @Test
    public void assertNoEllipsizedTextInRTLMode()
    {
        formulaEditorLayout.check(noEllipsizedText());
    }

    @Test
    public void assertNotNullValueInRTLMode()
    {
        formulaEditorLayout.check(matches(notNullValue()));
    }

    @Test
    public void assertNoOverLappingBricksInRTLMode()
    {
        formulaEditorLayout.check(noOverlaps());
    }

    @Test
    public void assertLayoutDirectionIsRTL()
    {
        formulaEditorLayout.check(isLayoutDirectionRTL());
    }

    @Test
    public void assertTextDirectionIsRTL()
    {
        formulaEditorLayout.check(isTextDirectionRTL());
    }

    @Test
    public void assertIsVisibleBrickInRTL()
    {
        formulaEditorLayout.check(isVisible());
    }

    private void addBrickChange_x_by()
    {
        Espresso.onView(withId(R.id.main_menu_button_continue)).perform(ViewActions.click());
        Espresso.onView(withText("كائن1")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.program_menu_button_scripts)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_motion)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_change_x_by)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withId(R.id.brick_change_x_edit_text)).perform(ViewActions.click());
    }

    private void createProject() {
        Project project = new Project(null, UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
        sprite1 = new Sprite("كائن1");
        Script script = new StartScript();
        sprite1.addScript(script);
        project.addSprite(sprite1);
        ProjectManager.getInstance().setProject(project);
        ProjectManager.getInstance().setCurrentSprite(sprite1);
        ProjectManager.getInstance().setCurrentScript(script);
    }
}
