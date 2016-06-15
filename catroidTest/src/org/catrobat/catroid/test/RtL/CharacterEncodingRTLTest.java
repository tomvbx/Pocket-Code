/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2016 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.test.RtL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.common.Constants;
import org.catrobat.catroid.common.DefaultProjectHandler;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.ComeToFrontBrick;
import org.catrobat.catroid.content.bricks.HideBrick;
import org.catrobat.catroid.content.bricks.PlaceAtBrick;
import org.catrobat.catroid.content.bricks.SetLookBrick;
import org.catrobat.catroid.content.bricks.SetSizeToBrick;
import org.catrobat.catroid.content.bricks.ShowBrick;
import org.catrobat.catroid.exceptions.CompatibilityProjectException;
import org.catrobat.catroid.exceptions.ProjectException;
import org.catrobat.catroid.io.StorageHandler;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.MyProjectsActivity;
import org.catrobat.catroid.ui.ProjectActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;
import org.catrobat.catroid.utils.UtilFile;
import org.catrobat.catroid.utils.Utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zlatko on 1.6.2016.
 */
public class CharacterEncodingRTLTest extends BaseActivityInstrumentationTestCase<MainMenuActivity> {
    private static final String TAG = CharacterEncodingRTLTest.class.getSimpleName();

    private String testProject = UiTestUtils.PROJECTNAME1;
    private String testProject2 = UiTestUtils.PROJECTNAME_ARABIC;

    private static final float CATROBAT_LANGUAGE_VERSION_TOO_LOW = 0.0f;

    public CharacterEncodingRTLTest() {
        super(MainMenuActivity.class);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateNewProject() {
        File directory = new File(Constants.DEFAULT_ROOT + "/" + testProject);
        UtilFile.deleteDirectory(directory);
        assertFalse("testProject was not deleted!", directory.exists());

        String hintNewProjectText = solo.getString(R.string.new_project_dialog_hint);

        solo.clickOnButton(solo.getString(R.string.main_menu_new));
        solo.waitForText(hintNewProjectText);
        EditText addNewProjectEditText = solo.getEditText(0);
        //check if hint is set
        assertEquals("Not the proper hint set", hintNewProjectText, addNewProjectEditText.getHint());
        assertEquals("There should no text be set", "", addNewProjectEditText.getText().toString());
        solo.clearEditText(0);
        solo.enterText(0, testProject);
        String buttonOKText = solo.getString(R.string.ok);
        solo.waitForText(buttonOKText);
        solo.clickOnText(buttonOKText);
        assertTrue("dialog not loaded in 5 seconds",
                solo.waitForText(solo.getString(R.string.project_orientation_title), 0, 5000));
        solo.clickOnButton(buttonOKText);
        solo.waitForActivity(ProjectActivity.class.getSimpleName());

        File file = new File(Constants.DEFAULT_ROOT + "/" + testProject + "/" + Constants.PROJECTCODE_NAME);
        assertTrue(testProject + " was not created!", file.exists());

        solo.goBack();
        assertFalse("New project dialog shouldn't show up again!",
                solo.searchText(solo.getString(R.string.new_project_dialog_title)));
    }

    public void testCreateNewProject_Arabic() {
        File directory = new File(Constants.DEFAULT_ROOT + "/" + testProject2);
        UtilFile.deleteDirectory(directory);
        assertFalse("testProject was not deleted!", directory.exists());

        String hintNewProjectText = solo.getString(R.string.new_project_dialog_hint);

        solo.clickOnButton(solo.getString(R.string.main_menu_new));
        solo.waitForText(hintNewProjectText);
        EditText addNewProjectEditText = solo.getEditText(0);
        //check if hint is set
        assertEquals("Not the proper hint set", hintNewProjectText, addNewProjectEditText.getHint());
        assertEquals("There should no text be set", "", addNewProjectEditText.getText().toString());
        solo.clearEditText(0);
        solo.enterText(0, testProject2);
        String buttonOKText = solo.getString(R.string.ok);
        solo.waitForText(buttonOKText);
        solo.clickOnText(buttonOKText);
        assertTrue("dialog not loaded in 5 seconds",
                solo.waitForText(solo.getString(R.string.project_orientation_title), 0, 5000));
        solo.clickOnButton(buttonOKText);
        solo.waitForActivity(ProjectActivity.class.getSimpleName());

        File file = new File(Constants.DEFAULT_ROOT + "/" + testProject2 + "/" + Constants.PROJECTCODE_NAME);
        assertTrue(testProject2 + " was not created!", file.exists());

        solo.goBack();
        assertFalse("New project dialog shouldn't show up again!",
                solo.searchText(solo.getString(R.string.new_project_dialog_title)));
    }


    public void testBottombarElementsVisibilty() {
        assertFalse("Add button is visible", solo.searchButton(solo.getString(R.id.button_add)));
        assertFalse("Play button is visible", solo.searchButton(solo.getString(R.id.button_play)));
    }

    public void testLoadProject() {
        File directory = new File(Constants.DEFAULT_ROOT + "/" + testProject2);
        UtilFile.deleteDirectory(directory);
        assertFalse(testProject2 + " was not deleted!", directory.exists());

        createTestProject(testProject2);
        solo.sleep(200);

        solo.clickOnButton(solo.getString(R.string.main_menu_programs));
        assertTrue("MyProjectsActivity not shown", solo.waitForActivity(MyProjectsActivity.class.getSimpleName()));
        solo.clickOnText(testProject2);
        assertTrue("ProjectActivity not shown", solo.waitForActivity(ProjectActivity.class.getSimpleName()));
        assertTrue("SpritesListFragment not shown", solo.waitForFragmentById(R.id.fragment_container));

        ListView spritesList = (ListView) solo.getCurrentActivity().findViewById(android.R.id.list);
        Sprite first = (Sprite) spritesList.getItemAtPosition(1);
        assertEquals("Sprite at index 1 is not \"cat\"!", "cat", first.getName());
        Sprite second = (Sprite) spritesList.getItemAtPosition(2);
        assertEquals("Sprite at index 2 is not \"dog\"!", "dog", second.getName());
        Sprite third = (Sprite) spritesList.getItemAtPosition(3);
        assertEquals("Sprite at index 3 is not \"horse\"!", "horse", third.getName());
        Sprite fourth = (Sprite) spritesList.getItemAtPosition(4);
        assertEquals("Sprite at index 4 is not \"pig\"!", "pig", fourth.getName());
    }


    public void createTestProject(String projectName) {
        int xPosition = 457;
        int yPosition = 598;
        double size = 0.8;

        Project project = new Project(getActivity(), projectName);
        Sprite firstSprite = new Sprite("cat");
        Sprite secondSprite = new Sprite("dog");
        Sprite thirdSprite = new Sprite("horse");
        Sprite fourthSprite = new Sprite("pig");
        Script testScript = new StartScript();
        Script otherScript = new StartScript();
        HideBrick hideBrick = new HideBrick();
        ShowBrick showBrick = new ShowBrick();
        SetSizeToBrick setSizeToBrick = new SetSizeToBrick(size);
        ComeToFrontBrick comeToFrontBrick = new ComeToFrontBrick();
        PlaceAtBrick placeAtBrick = new PlaceAtBrick(xPosition, yPosition);

        // adding Bricks: ----------------
        testScript.addBrick(hideBrick);
        testScript.addBrick(showBrick);
        testScript.addBrick(setSizeToBrick);
        testScript.addBrick(comeToFrontBrick);

        otherScript.addBrick(placeAtBrick); // secondSprite
        otherScript.setPaused(true);
        // -------------------------------

        firstSprite.addScript(testScript);
        secondSprite.addScript(otherScript);

        project.addSprite(firstSprite);
        project.addSprite(secondSprite);
        project.addSprite(thirdSprite);
        project.addSprite(fourthSprite);

        ProjectManager.getInstance().setProject(project);
        StorageHandler.getInstance().saveProject(project);
    }

    public void testProjectNameVisible() {
        createTestProject(testProject);
        createTestProject(testProject2);

        solo.clickOnText(solo.getString(R.string.main_menu_programs));

        solo.waitForActivity(MyProjectsActivity.class.getSimpleName());
        UiTestUtils.clickOnExactText(solo, testProject);
        solo.waitForFragmentById(R.id.fragment_container);

        solo.goBack();
        solo.waitForActivity(MyProjectsActivity.class.getSimpleName());
        solo.goBack();
        solo.waitForActivity(MainMenuActivity.class.getSimpleName());
        assertTrue("The name of the current testProject is not displayed on the continue button", solo.getButton(0)
                .getText().toString().endsWith(testProject));

        solo.clickOnText(solo.getString(R.string.main_menu_programs));
        solo.waitForActivity(MyProjectsActivity.class.getSimpleName());

        solo.clickOnText(testProject2, 1, true);
        solo.waitForFragmentById(R.id.fragment_container);

        solo.goBack();
        solo.waitForActivity(MyProjectsActivity.class.getSimpleName());
        solo.goBack();
        solo.waitForActivity(MainMenuActivity.class.getSimpleName());
        assertTrue("The name of the current testProject2 is not displayed on the continue button", solo.getButton(0)
                .getText().toString().endsWith(testProject2));
    }

    public boolean isRTL() {
        return TextUtils.getLayoutDirectionFromLocale(solo.getCurrentActivity().getResources().getConfiguration().locale) == View.LAYOUT_DIRECTION_RTL;
    }

    public void testCreatingProjectWithArabicName()
    {
        File directory = new File(Constants.DEFAULT_ROOT + "/" + testProject2);
        UtilFile.deleteDirectory(directory);
        assertFalse("testProject2 was not deleted!", directory.exists());

        String hintNewProjectText = solo.getString(R.string.new_project_dialog_hint);

        solo.clickOnButton(solo.getString(R.string.main_menu_new));
        solo.waitForText(hintNewProjectText);
        EditText addNewProjectEditText = solo.getEditText(0);
        //check if hint is set
        assertEquals("Not the proper hint set", hintNewProjectText, addNewProjectEditText.getHint());
        assertEquals("There should no text be set", "", addNewProjectEditText.getText().toString());
        solo.clearEditText(0);
        solo.enterText(0, testProject2);
        String buttonOKText = solo.getString(R.string.ok);
        solo.waitForText(buttonOKText);
        solo.clickOnText(buttonOKText);
        assertTrue("dialog not loaded in 5 seconds",
                solo.waitForText(solo.getString(R.string.project_orientation_title), 0, 5000));
        solo.clickOnButton(buttonOKText);
        solo.waitForActivity(ProjectActivity.class.getSimpleName());

        File file = new File(Constants.DEFAULT_ROOT + "/" + testProject2 + "/" + Constants.PROJECTCODE_NAME);
        assertTrue(testProject2 + " was not created!", file.exists());

        solo.goBack();
        assertFalse("New project dialog shouldn't show up again!",
                solo.searchText(solo.getString(R.string.new_project_dialog_title)));
    }

    public void testArabicInput()
    {
        //newproject
        //background
        //script
        //add set variable numbers latin
        //add set variable numbers hindi
        //add set variable text latin
        //add variable text hindi
        //show variable x4
        //see if they appear

        File directory = new File(Constants.DEFAULT_ROOT + "/" + testProject2);
        UtilFile.deleteDirectory(directory);
        assertFalse("testProject2 was not deleted!", directory.exists());

        String hintNewProjectText = solo.getString(R.string.new_project_dialog_hint);

        solo.clickOnButton(solo.getString(R.string.main_menu_new));
        solo.waitForText(hintNewProjectText);
        EditText addNewProjectEditText = solo.getEditText(0);
        //check if hint is set
        assertEquals("Not the proper hint set", hintNewProjectText, addNewProjectEditText.getHint());
        assertEquals("There should no text be set", "", addNewProjectEditText.getText().toString());
        solo.clearEditText(0);
        solo.enterText(0, testProject2);
        String buttonOKText = solo.getString(R.string.ok);
        solo.waitForText(buttonOKText);
        solo.clickOnText(buttonOKText);
        assertTrue("dialog not loaded in 5 seconds",
                solo.waitForText(solo.getString(R.string.project_orientation_title), 0, 5000));
        solo.clickOnButton(buttonOKText);
        solo.waitForActivity(ProjectActivity.class.getSimpleName());

        File file = new File(Constants.DEFAULT_ROOT + "/" + testProject2 + "/" + Constants.PROJECTCODE_NAME);
        assertTrue(testProject2 + " was not created!", file.exists());

        solo.goBack();
        assertFalse("New project dialog shouldn't show up again!",
                solo.searchText(solo.getString(R.string.new_project_dialog_title)));
    }

}
