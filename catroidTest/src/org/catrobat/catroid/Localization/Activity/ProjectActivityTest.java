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

package org.catrobat.catroid.Localization.Activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.uitest.util.UiTestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.test.MoreAsserts.assertEquals;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by ِAiman Awwad on 8/17/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ProjectActivityTest  {
	@Rule
	public ActivityTestRule<MainMenuActivity> mActivityRule;
	private static final String TAG = ProjectActivityTest.class.getSimpleName();
	private MainMenuActivity mActivity;
	private static final String TEST_SPRITE_NAME = "cat";
	private static final String FIRST_TEST_SPRITE_NAME = "كائن1";
	private static final String SECOND_TEST_SPRITE_NAME = "كائن2";
	private static final String THIRD_TEST_SPRITE_NAME = "كائن3";
	private static final String FOURTH_TEST_SPRITE_NAME = "كائن4";
	private ProjectManager projectManager;
	private List<Sprite> spriteList;

	public ProjectActivityTest() {
		mActivityRule = new ActivityTestRule(MainMenuActivity.class);
	}


	public void setUp() throws Exception {
		UiTestUtils.createTestProject();
		UiTestUtils.prepareStageForTest();
		projectManager = ProjectManager.getInstance();
		spriteList = projectManager.getCurrentProject().getSpriteList();
		spriteList.add(new Sprite(FIRST_TEST_SPRITE_NAME));
		spriteList.add(new Sprite(SECOND_TEST_SPRITE_NAME));
		spriteList.add(new Sprite(THIRD_TEST_SPRITE_NAME));
		spriteList.add(new Sprite(FOURTH_TEST_SPRITE_NAME));
	}


	@Test
	public void assertProjectActivityDirection()
	{
		Espresso.onView(withId(R.id.main_menu_button_continue)).perform(click());
		Espresso.onView(isRoot()).check(matches(isDisplayed()));
	}

	@Test
	public void  assertTextDisplay()
	{
	Espresso.onView(isRoot()).check(ViewAssertions.matches(ViewMatchers.withText(R.string.hide_details)));
	}

	@Test
	public void assertSpriteListDetails() {
		Espresso.onView(withId(R.id.main_menu_button_continue)).check(ViewAssertions.matches(ViewMatchers.isDisplayed
				())).perform(ViewActions.click());
		Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

		ViewInteraction viewInteraction=Espresso.onView(withText(R.string.hide_details)).check(ViewAssertions.matches(isCompletelyDisplayed()));
		viewInteraction.equals("sswww");

		Espresso.onView(withText(R.string.hide_details)).perform(ViewActions.click());

		Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
		Espresso.onView(withText(R.string.show_details)).check(ViewAssertions.matches(isCompletelyDisplayed()));
		Espresso.onView(withText(R.string.hide_details)).perform(ViewActions.click());


		Espresso.onView(isRoot()).check(ViewAssertions.matches(notNullValue()));
		Espresso.onView(isRoot()).check(ViewAssertions.matches(isDisplayed()));
	}





}