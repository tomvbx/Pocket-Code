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

package org.catrobat.catroid.Localization;

import android.widget.CheckBox;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.io.File;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by dell on 8/17/2016.
 */
public class ProjectActivityTest extends BaseActivityInstrumentationTestCase<MainMenuActivity> {
	private static final String TAG = ProjectActivityTest.class.getSimpleName();

	private static final String TEST_SPRITE_NAME = "cat";
	private static final String FIRST_TEST_SPRITE_NAME = "test1";
	private static final String SECOND_TEST_SPRITE_NAME = "test2";
	private static final String THIRD_TEST_SPRITE_NAME = "test3";
	private static final String FOURTH_TEST_SPRITE_NAME = "test4";

	private String rename;
	private String renameDialogTitle;
	private String delete;
	String defaultSpriteName;

	private CheckBox firstCheckBox;
	private CheckBox secondCheckBox;

	private ProjectManager projectManager;
	private List<Sprite> spriteList;

	private File lookFile;

	public ProjectActivityTest() {
		super(MainMenuActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		UiTestUtils.createTestProject();
		UiTestUtils.prepareStageForTest();
		lookFile = UiTestUtils.setUpLookFile(solo, getActivity());

		projectManager = ProjectManager.getInstance();
		spriteList = projectManager.getCurrentProject().getSpriteList();

		spriteList.add(new Sprite(FIRST_TEST_SPRITE_NAME));
		spriteList.add(new Sprite(SECOND_TEST_SPRITE_NAME));
		spriteList.add(new Sprite(THIRD_TEST_SPRITE_NAME));
		spriteList.add(new Sprite(FOURTH_TEST_SPRITE_NAME));

		rename = solo.getString(R.string.rename);
		renameDialogTitle = solo.getString(R.string.rename_sprite_dialog);
		delete = solo.getString(R.string.delete);
		defaultSpriteName = solo.getString(R.string.default_project_sprites_bird_name);
	}

	@Override
	public void tearDown() throws Exception {
		lookFile.delete();

		super.tearDown();
	}

	public void testProjectActivity() {
		UiTestUtils.getIntoSpritesFromMainMenu(solo);

		int spriteToCheckIndex = 2;
		String spriteToCheckName = spriteList.get(spriteToCheckIndex).getName();

		assertEquals("Sprite at index " + spriteToCheckIndex + " is not '" + SECOND_TEST_SPRITE_NAME + "'",
				SECOND_TEST_SPRITE_NAME, spriteToCheckName);
		assertTrue("Sprite is not in current Project", projectManager.spriteExists(spriteToCheckName));

		final String addedSpriteName = "addedTestSprite";
		UiTestUtils.addNewSprite(solo, addedSpriteName, lookFile, null);


		spriteList = projectManager.getCurrentProject().getSpriteList();

		spriteToCheckIndex = 5;

		Sprite spriteToCheck = spriteList.get(spriteToCheckIndex);
		spriteToCheckName = spriteToCheck.getName();

		assertEquals("Sprite at index " + spriteToCheckIndex + " is not '" + addedSpriteName + "'", addedSpriteName,
				spriteToCheckName);
		assertTrue("Sprite is not in current Project", spriteList.contains(spriteToCheck));
		assertTrue("Sprite not shown in List", solo.searchText(spriteToCheckName));

	}

	public void testProjectActivityDirection()
	{
		onView(withId(R.id.main_menu_button_continue)).perform(click());
		onView(isRoot()).check(matches(isDisplayed()));

	}

	public void testSpriteListDetails() {
		String showDetailsText = solo.getString(R.string.show_details);
		String hideDetailsText = solo.getString(R.string.hide_details);
		onView(withId(R.id.main_menu_button_continue)).perform(click());

		openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
		onView(withText(hideDetailsText)).perform(click());

		openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
		onView(withText(showDetailsText)).perform(click());
		onView(isRoot()).check(matches(notNullValue()));
		onView(withId(R.id.textView_number_of_scripts)).check(matches(notNullValue()));
		onView(withId(R.id.project_activity_sprite_details)).check(matches(isDisplayed()));

      //  onView(isRoot()).check(noOverlaps());


				/*onView(withId(R.id.textView_number_of_scripts)).check(matches(isDisplayed()));
		onView(withId(R.id.textView_number_of_bricks)).check(matches(isDisplayed()));
		onView(withId(R.id.textView_number_of_looks)).check(matches(isDisplayed()));
		onView(withId(R.id.textView_number_of_sounds)).check(matches(isDisplayed()));*/

	}




}