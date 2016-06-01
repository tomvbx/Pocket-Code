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
package org.catrobat.catroid.uitest.ui.activity;

import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.catrobat.catroid.R;
import org.catrobat.catroid.common.Constants;
import org.catrobat.catroid.common.DefaultProjectHandler;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.io.StorageHandler;
import org.catrobat.catroid.test.utils.TestUtils;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.MyProjectsActivity;
import org.catrobat.catroid.ui.SettingsActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;
import org.catrobat.catroid.utils.UtilFile;
import org.catrobat.catroid.utils.Utils;
import org.mockito.cglib.core.Local;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsActivityTest extends BaseActivityInstrumentationTestCase<MainMenuActivity> {

	private String settings;
	private SharedPreferences preferences;

	public SettingsActivityTest() {
		super(MainMenuActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		TestUtils.createEmptyProject();
		settings = solo.getString(R.string.settings);
		preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		preferences.edit().putBoolean(SettingsActivity.SETTINGS_MINDSTORMS_NXT_BRICKS_ENABLED, false).commit();
	}

	@Override
	protected void tearDown() throws Exception {
		preferences.edit().putBoolean(SettingsActivity.SETTINGS_MINDSTORMS_NXT_BRICKS_ENABLED, false).commit();
		solo.finishOpenedActivities();
		super.tearDown();
	}

	public void testToggleMindstormsNXTBricks() {
		String mindstormsPreferenceString = solo.getString(R.string.preference_title_enable_mindstorms_nxt_bricks);
		String categoryLegoNXTLabel = solo.getString(R.string.category_lego_nxt);

		solo.waitForActivity(MainMenuActivity.class);
		UiTestUtils.getIntoScriptActivityFromMainMenu(solo);
		UiTestUtils.clickOnBottomBar(solo, R.id.button_add);
		solo.sleep(200);
		ListView fragmentListView = solo.getCurrentViews(ListView.class).get(
				solo.getCurrentViews(ListView.class).size() - 1);
		solo.sleep(200);
		solo.scrollListToBottom(fragmentListView);
		solo.sleep(200);
		assertFalse("Lego brick category is showing!", solo.searchText(categoryLegoNXTLabel));
		solo.goBack();
		solo.goBack();
		solo.goBack();
		solo.goBack();
		solo.waitForActivity(MainMenuActivity.class.getSimpleName());

		solo.clickOnMenuItem(settings);
		solo.waitForActivity(SettingsActivity.class.getSimpleName());

		assertTrue("Wrong title", solo.searchText(solo.getString(R.string.preference_title)));

		solo.clickOnText(mindstormsPreferenceString); // submenu
		solo.waitForText(mindstormsPreferenceString);
		solo.clickOnText(mindstormsPreferenceString); // checkbox
		solo.goBack();
		solo.goBack();
		solo.waitForActivity(MainMenuActivity.class.getSimpleName());
		UiTestUtils.getIntoScriptActivityFromMainMenu(solo);
		UiTestUtils.clickOnBottomBar(solo, R.id.button_add);
		solo.sleep(200);
		fragmentListView = solo.getCurrentViews(ListView.class).get(
				solo.getCurrentViews(ListView.class).size() - 1);
		solo.sleep(200);
		solo.scrollListToBottom(fragmentListView);
		solo.scrollDown();
		solo.sleep(200);
		assertTrue("Lego brick category is not showing!", solo.searchText(categoryLegoNXTLabel));
	}

	public void testChangeNumberFormatSetting() throws Exception {
		String showDetailsText = solo.getString(R.string.show_details);

		Date date = new Date(1357038000000L);
		DateFormat mediumDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		SimpleDateFormat newDateFormat;

		// sometimes standard project is not created for some reason!
		// this test needs at least 3 projects in list!
		// creating standard project if no project is loaded on test start
		createStandardProgramIfNeeded();

		createProjectsWithoutSprites();
		String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/Pocket Code";
		String projectFilePath = Utils.buildPath(Utils.buildProjectPath(UiTestUtils.DEFAULT_TEST_PROJECT_NAME),
				Constants.PROJECTCODE_NAME);
		File projectCodeFile = new File(projectFilePath);
		Date now = new Date();
		long timeInMilliSeconds = now.getTime() - DateUtils.DAY_IN_MILLIS;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");


		String projectFilePathSec = Utils.buildPath(Utils.buildProjectPath(UiTestUtils.PROJECTNAME1), Constants.PROJECTCODE_NAME);
		projectCodeFile = new File(projectFilePathSec);


		Date date_2 = new Date(timeInMilliSeconds);
		String result = formatter.format(date_2);


		try{
			Process su = Runtime.getRuntime().exec("su");
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

			outputStream.writeBytes("cd \"/storage/storage0/Pocket Code/testProject/\"\n");
			outputStream.flush();
			outputStream.writeBytes("touch -t 20160101 code.xml\n");
			outputStream.flush();

			outputStream.writeBytes("cd \"/storage/storage0/Pocket Code/testingproject1/\"\n");
			outputStream.flush();
			outputStream.writeBytes("touch -t " + result + " code.xml\n");
			outputStream.flush();

			outputStream.writeBytes("exit\n");
			outputStream.flush();
			su.waitFor();
		}catch(IOException e){
			throw new Exception(e);
		}catch(InterruptedException e){
			throw new Exception(e);
		}

		solo.sleep(200);


		solo.waitForActivity(MainMenuActivity.class.getSimpleName());
		solo.sleep(200);
		solo.clickOnMenuItem(settings);
		solo.sleep(200);

		solo.waitForActivity(SettingsActivity.class.getSimpleName());
		assertTrue("Wrong title", solo.searchText(solo.getString(R.string.preference_title)));
		solo.sleep(200);
		solo.clickOnText("Change date format");
		solo.waitForText("Change date format");
		solo.sleep(200);
		solo.clickOnText("MMMM-dd-yyyy");
		solo.waitForText("MMMM-dd-yyyy");
		newDateFormat= new SimpleDateFormat("MMMM-dd-yyyy");
		solo.sleep(200);

		solo.goBack();
		solo.goBack();
		solo.goBack();
		solo.sleep(200);

		solo.clickOnButton(solo.getString(R.string.main_menu_programs));
		solo.waitForActivity(MyProjectsActivity.class.getSimpleName());
		solo.waitForFragmentById(R.id.fragment_projects_list);

		View projectDetails = solo.getView(R.id.my_projects_activity_list_item_details);
		solo.waitForView(projectDetails);
		UiTestUtils.openOptionsMenu(solo);

		solo.waitForText(showDetailsText);
		solo.clickOnText(showDetailsText);
		solo.sleep(400);

		//get details view again, otherwise assert will fail
		projectDetails = solo.getView(R.id.my_projects_activity_list_item_details);
		assertEquals("Project details are not showing!", View.VISIBLE, projectDetails.getVisibility());

		assertTrue("Last access is not correct!", solo.searchText(solo.getString(R.string.details_date_today)));
		assertTrue("Last access is not correct!", solo.searchText(solo.getString(R.string.details_date_yesterday)));
		assertTrue("Last access is not correct!", solo.searchText(newDateFormat.format(date)));




	/*	solo.waitForActivity(MainMenuActivity.class.getSimpleName());
		solo.clickOnButton(solo.getString(R.string.main_menu_programs));
		solo.waitForActivity(MyProjectsActivity.class.getSimpleName());
		solo.waitForFragmentById(R.id.fragment_projects_list);
		solo.waitForText(solo.getString(R.string.default_project_name));
		UiTestUtils.clickOnTextInList(solo, solo.getString(R.string.default_project_name));*/



	}
	private void createStandardProgramIfNeeded() {
		File rootDirectory = new File(Constants.DEFAULT_ROOT);
		if (UtilFile.getProjectNames(rootDirectory).isEmpty()) {

			try {
				DefaultProjectHandler.createAndSaveDefaultProject(getActivity());
			} catch (IOException e) {

				fail("Standard Project could not be not created");
			}
		}
	}
	private void createProjectsWithoutSprites() {
		Project project1 = new Project(getActivity(), UiTestUtils.PROJECTNAME1);
		StorageHandler.getInstance().saveProject(project1);
		solo.sleep(2000);

		Project project2 = new Project(getActivity(), UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
		StorageHandler.getInstance().saveProject(project2);
		solo.sleep(2000);
	}
}

