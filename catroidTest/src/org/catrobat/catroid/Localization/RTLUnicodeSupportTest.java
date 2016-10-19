package org.catrobat.catroid.Localization;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.common.ScreenValues;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.stage.StageActivity;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.uitest.util.UiTestUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;
import static org.catrobat.catroid.Localization.EditTextDirection.editTextShouldHaveDirection;
import static org.catrobat.catroid.Localization.LayoutDirectionAssertions.isLayoutDirectionRTL;
import static org.catrobat.catroid.Localization.VisibilityAssertions.isVisible;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Aiman Awwad on 9/29/2016.
 */
@RunWith(AndroidJUnit4.class)
public class RTLUnicodeSupportTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    private MainMenuActivity mActivity;
    private Sprite sprite1,sprite2;
    String variableName, variableValue;
    int virtualScreenWidth;
    int virtualScreenHeight;
    private float screenScaleFactorX = 1.0F;
    private float screenScaleFactorY = 1.0F;
    private static final byte[] WHITE_PIXEL = { (byte) 255, (byte) 255, (byte) 255, (byte) 255 };



    public RTLUnicodeSupportTest() {
        super(MainMenuActivity.class);
    }
    @Before
    public void setUp() throws Exception {
        super.setUp();
        createProject();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
        virtualScreenWidth = ProjectManager.getInstance().getCurrentProject().getXmlHeader().virtualScreenWidth;
        virtualScreenHeight = ProjectManager.getInstance().getCurrentProject().getXmlHeader().virtualScreenHeight;
    }

    @Before
    public void initValidString() {
        // Specify a valid string.
        variableName = "Variable1";
        variableValue="Hello";
    }

    @Test
    public void assertTextDisplayedCorrectlyOnStage()
    {

        Espresso.onView(withId(R.id.main_menu_button_continue)).perform(ViewActions.click());
        Espresso.onView(withText("كائن2")).perform(ViewActions.click());
        Espresso.onView(withId(R.id.program_menu_button_scripts)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.button_add)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.category_data)).perform(ViewActions.click());
        Espresso.onView(withText(R.string.brick_set_variable)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
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

        Espresso.onView(withText(R.string.brick_show_text_var)).perform(ViewActions.click());
        Espresso.onView(isRoot()).perform(ViewActions.click());
        Espresso.onView(withId(R.id.brick_set_variable_edit_text)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.formula_editor_keyboard_string)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.formula_editor_string_name_edit_text))
                .perform(replaceText(variableValue));

        ViewInteraction Ok_StringButton=onView(withText(R.string.ok));
        Ok_StringButton.perform(ViewActions.click());

        Espresso.onView(withId(R.id.formula_editor_keyboard_ok))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.brick_show_text_edit_text_x))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.formula_editor_keyboard_0))
                .perform(ViewActions.click());

          Espresso.onView(withId(R.id.formula_editor_keyboard_ok))
           .perform(ViewActions.click());

        Espresso.pressBack();
        Espresso.onView(withId(R.id.program_menu_button_scripts)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.brick_show_text_edit_text_y))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.formula_editor_keyboard_0))
                .perform(ViewActions.click());

        Espresso.onView(withId(R.id.formula_editor_keyboard_ok))
                .perform(ViewActions.click());
         Espresso.onView(withId(R.id.button_play))
                .perform(ViewActions.click());
        float scale = 1f;
         byte[] blackPixel = { (byte) 0, (byte) 0, (byte) 0, (byte) 0 };
         byte[] result = StageActivity.stageListener.getPixels( (int) (ScreenValues.SCREEN_WIDTH * scale), (int) (ScreenValues.SCREEN_HEIGHT * scale), 1, 1);
         UiTestUtils.compareByteArrays(blackPixel, result);


        /*Espresso.pressBack();
        Espresso.onView(withId(R.id.stage_layout_relative)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.stage_layout_relative)).check(isVisible());
        Espresso.onView(withId(R.id.stage_layout_relative)).check(matches(notNullValue()));
        Espresso.onView(withId(R.id.stage_layout_relative)).check(isLayoutDirectionRTL());*/

        //Bitmap bitmap=takeScreenshot();
        //saveBitmap(bitmap);

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


    public static void takeScreenshot(String name,Activity activity)
    {
        // Screenshots are always stored under /Pictures folder
        String path =
                Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures/" + name + ".png";

        View scrScreenshotView = activity.getWindow().getDecorView().getRootView();
        scrScreenshotView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(scrScreenshotView.getDrawingCache());
        scrScreenshotView.setDrawingCacheEnabled(false);
        OutputStream out = null;
        File imageFile = new File(path);

        try {
            out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
        } catch (FileNotFoundException e) {
            // exception
        } catch (IOException e) {
            // exception
        } finally {

            try {
                if (out != null) {
                    out.close();
                }

            } catch (Exception exc) {
            }

        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        activity.startActivity(intent);
    }



    public Bitmap takeScreenshot() {
        View rootView = getActivity().findViewById(R.id.stage_layout_relative).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imagePath);
        intent.setDataAndType(uri, "image/*");
        getActivity().startActivity(intent);
    }


    private void comparePixelColorArrayWithScreenArrayAtProjectCoordinatesWithTolerance(byte[] screenArray, byte[] pixelArray, int x,
                                                                                        int y, int tolerance) {
        assertEquals("Length of pixel array not 4", 4, pixelArray.length);
        int convertedX = (int) Math.ceil((x * screenScaleFactorX) + (ScreenValues.SCREEN_WIDTH / 2));
        int convertedY = (int) Math.ceil((y * screenScaleFactorY) + (ScreenValues.SCREEN_HEIGHT / 2));

        byte[] screenPixel = new byte[4];
        for (int i = 0; i < 4; i++) {
            screenPixel[i] = screenArray[((convertedX * 3) + convertedX + (convertedY * ScreenValues.SCREEN_WIDTH * 4)) + i];
        }
        assertEquals("Pixels don't have same content. x:" + x + "->" + convertedX + " y:" + y + "->" + convertedY, pixelArray[0] & 0xFF, screenPixel[0] & 0xFF, tolerance);
        assertEquals("Pixels don't have same content. x:" + x + "->" + convertedX + " y:" + y + "->" + convertedY, pixelArray[1] & 0xFF, screenPixel[1] & 0xFF, tolerance);
        assertEquals("Pixels don't have same content. x:" + x + "->" + convertedX + " y:" + y + "->" + convertedY, pixelArray[2] & 0xFF, screenPixel[2] & 0xFF, tolerance);
        assertEquals("Pixels don't have same content. x:" + x + "->" + convertedX + " y:" + y + "->" + convertedY, pixelArray[3] & 0xFF, screenPixel[3] & 0xFF, tolerance);
    }



}