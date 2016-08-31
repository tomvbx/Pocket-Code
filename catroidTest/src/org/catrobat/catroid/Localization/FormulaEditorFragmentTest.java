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

import android.util.LayoutDirection;
import android.widget.RelativeLayout;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.PlaceAtBrick;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Aiman Awwad on 8/16/2016.
 */
public class FormulaEditorFragmentTest extends BaseActivityInstrumentationTestCase<MainMenuActivity>{

		private PlaceAtBrick placeAtBrick;
		private static final int INITIAL_X = 8;
		private static final int INITIAL_Y = 7;

		private static final int X_POS_EDIT_TEXT_RID = R.id.brick_place_at_edit_text_x;
		private Sprite sprite;

		public FormulaEditorFragmentTest() {
			super(MainMenuActivity.class);
		}

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			createProject();
			UiTestUtils.getIntoScriptActivityFromMainMenu(solo);
		}

		private void createProject() {
			Project project = new Project(null, UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
			sprite = new Sprite("Arabic");
			Script script = new StartScript();
			placeAtBrick = new PlaceAtBrick(INITIAL_X, INITIAL_Y);
			script.addBrick(placeAtBrick);
			sprite.addScript(script);
			project.addSprite(sprite);
			ProjectManager.getInstance().setProject(project);
			ProjectManager.getInstance().setCurrentSprite(sprite);
			ProjectManager.getInstance().setCurrentScript(script);
		}

	public void testFormulaEditorLayoutDirection() throws InterruptedException {
		onView(withId(X_POS_EDIT_TEXT_RID)).perform(click());
		onView(withId(R.id.formula_editor_keyboard_1)).perform(click());
		Thread.sleep(1000);
		RelativeLayout relativeLayout=(RelativeLayout) getActivity().findViewById(R.id.FormulaEditorLayout);
		int Expected= LayoutDirection.RTL;
		int Actual=relativeLayout.getLayoutDirection();
		assertEquals(Expected,Actual);


	}

	}
