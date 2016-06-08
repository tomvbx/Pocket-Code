package org.catrobat.catroid.uitest.ui.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.robotium.solo.Solo;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.dialogs.NewProjectDialog;

/**
 * Created by Sareh on 7.6.2016.
 */
public class GraphicDirectionSensitiveActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity>{

	private Solo solo;

	public GraphicDirectionSensitiveActivityTest (){
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

	public void testUndoAndRedo(){
		solo.clickOnButton(getActivity().getString(R.string.main_menu_continue));
		solo.clickOnText(getActivity().getString(R.string.default_project_cloud_sprite_name_1));
		solo.clickOnButton(getActivity().getString(R.string.scripts));
		solo.sleep(500);
        solo.clickOnText(getActivity().getString(R.string.brick_place_at));
		solo.sleep(500);
		solo.clickOnText("ویرایش فرمول");
		solo.sleep(500);
		solo.clickOnButton(getActivity().getString(R.string.number_1));
		solo.sleep(500);
		solo.clickOnActionBarItem(R.id.menu_undo);
		String number1 = "0 ";
		EditText et=(EditText) solo.getView(R.id.formula_editor_edit_field);
		String st=et.getText().toString();
		assertEquals(number1,st);
		solo.sleep(1000);
		solo.clickOnActionBarItem(R.id.menu_redo);
		String number2 = "1 ";
		EditText ett =(EditText) solo.getView(R.id.formula_editor_edit_field);
		String stt =ett.getText().toString();
		assertEquals(number2, stt);
		solo.sleep(3000);
	}
}