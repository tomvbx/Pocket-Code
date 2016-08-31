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

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.LayoutDirection;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.LayoutAssertions.noEllipsizedText;
import static android.support.test.espresso.assertion.LayoutAssertions.noOverlaps;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

/**
 * Created by Aiman Awwad on 7/27/2016.
 */

@RunWith(AndroidJUnit4.class)

public class MainMenuActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

	private MainMenuActivity mActivity;

	public MainMenuActivityTest() {
		super(MainMenuActivity.class);
	}
	@Before
	public void setUp() throws Exception {
		super.setUp();
		injectInstrumentation(InstrumentationRegistry.getInstrumentation());
		mActivity = getActivity();
	}
	@Test
	public void assertNoOverlapping()
	{
		onView(isRoot()).check(noOverlaps());
	}

	@Test
	public void assertNoEllipseizedText() {
		onView(isRoot()).check(noEllipsizedText());
	}

	@Test
	public void assertLayoutRightToLeftDirection()
	{
		int layoutDirection_continue =getActivity().findViewById(R.id.main_menu_button_continue).getLayoutDirection();
		int layoutDirection_new =getActivity().findViewById(R.id.main_menu_button_new).getLayoutDirection();
		int layoutDirection_programs =getActivity().findViewById(R.id.main_menu_button_programs).getLayoutDirection();
		int layoutDirection_help =getActivity().findViewById(R.id.main_menu_button_help).getLayoutDirection();
		int layoutDirection_web =getActivity().findViewById(R.id.main_menu_button_web).getLayoutDirection();
		int layoutDirection_upload =getActivity().findViewById(R.id.main_menu_button_upload).getLayoutDirection();
        String failMsg="The layout Direction is Left to Right";
		assertEquals(failMsg,layoutDirection_continue, LayoutDirection.RTL);
		assertEquals(failMsg,layoutDirection_new, LayoutDirection.RTL);
		assertEquals(failMsg,layoutDirection_programs, LayoutDirection.RTL);
		assertEquals(failMsg,layoutDirection_help, LayoutDirection.RTL);
		assertEquals(failMsg,layoutDirection_web, LayoutDirection.RTL);
		assertEquals(failMsg,layoutDirection_upload, LayoutDirection.RTL);
	}

	@Test
	public void assertTextDirectionRightToLeft()
	{
		int textDirection_continue =getActivity().findViewById(R.id.main_menu_button_continue).getTextDirection();
		int textDirection_new =getActivity().findViewById(R.id.main_menu_button_new).getTextDirection();
		int textDirection_programs =getActivity().findViewById(R.id.main_menu_button_programs).getTextDirection();
		int textDirection_help =getActivity().findViewById(R.id.main_menu_button_help).getTextDirection();
		int textDirection_web =getActivity().findViewById(R.id.main_menu_button_web).getTextDirection();
		int textDirection_upload =getActivity().findViewById(R.id.main_menu_button_upload).getTextDirection();
		String failMsg="The Text Direction is Left to Right";
		assertEquals(failMsg,textDirection_continue, View.TEXT_DIRECTION_FIRST_STRONG);
		assertEquals(failMsg,textDirection_new, View.TEXT_DIRECTION_FIRST_STRONG);
		assertEquals(failMsg,textDirection_programs, View.TEXT_DIRECTION_FIRST_STRONG);
		assertEquals(failMsg,textDirection_help, View.TEXT_DIRECTION_FIRST_STRONG);
		assertEquals(failMsg,textDirection_upload, View.TEXT_DIRECTION_FIRST_STRONG);
		assertEquals(failMsg,textDirection_web, View.TEXT_DIRECTION_FIRST_STRONG);

	}
    @Test
	public void assertRightAlighnedDrawable()
    {
		Button continue_Button =(Button) getActivity().findViewById(R.id.main_menu_button_continue);
		int gravity=continue_Button.getGravity();
		assertEquals(gravity, Gravity.CENTER);

	}

	@Test
	public void assertScreenShots() {

		takeScreenshot("screenshot-001", getActivity());
	}
/*	@Test
	public void assertDrawableRight()
	{
		 Context mContext = getActivity().getApplicationContext();
		Drawable button_drawable= ContextCompat.getDrawable(mContext,R.drawable.ic_main_menu_continue);
		Button button=(Button) getActivity().findViewById(R.id.main_menu_button_continue);
		Drawable[] drawableValue=button.getCompoundDrawables();
		assertEquals(drawableValue,7);
	}*/


	public static void takeScreenshot(String name, Activity activity)
	{
		// Screenshots are always stored
		// under /Pictures folder and this ensures those screenshots
		// be shown under Test Results
		String path =
				Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/Pictures/" + name + ".png";
		View scrView = activity.getWindow().getDecorView().getRootView();
		scrView.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(scrView.getDrawingCache());
		scrView.setDrawingCacheEnabled(false);
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
	}
}

