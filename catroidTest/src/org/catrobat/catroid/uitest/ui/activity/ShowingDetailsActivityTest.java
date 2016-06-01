package org.catrobat.catroid.uitest.ui.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.dialogs.NewProjectDialog;

/**
 * Created by Sareh on 25.5.2016.
 */
public class ShowingDetailsActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity>{

	private Solo solo;

	public ShowingDetailsActivityTest (){
		super(MainMenuActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		solo= new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDetails(){
		solo.clickOnButton(getActivity().getString(R.string.main_menu_new));
		solo.sleep(1000);
		EditText et=(EditText) solo.getView(R.id.project_name_edittext);
		solo.enterText(et, "test1");
		solo.sleep(1000);
		solo.clickOnButton(getActivity().getString(R.string.ok));
		solo.sleep(1000);
		solo.clickOnButton(getActivity().getString(R.string.ok));
		solo.sleep(1000);
		solo.clickOnMenuItem(getActivity().getString(R.string.show_details));
		solo.sleep(1000);
		solo.goBack();
		solo.clickOnButton(getActivity().getString(R.string.main_menu_programs));
		solo.sleep(1000);
		solo.clickOnMenuItem(getActivity().getString(R.string.show_details));
	}
}

