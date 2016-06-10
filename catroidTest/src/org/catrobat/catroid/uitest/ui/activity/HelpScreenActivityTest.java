package org.catrobat.catroid.uitest.ui.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.webkit.WebView;
import android.widget.EditText;

import com.robotium.solo.By;
import com.robotium.solo.Solo;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.dialogs.NewProjectDialog;

/**
 * Created by Sareh on 08.06.2016.
 */
public class HelpScreenActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity>{

	private Solo solo;

	public HelpScreenActivityTest (){
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

	public void testHelpScreens(){
		solo.clickOnButton(getActivity().getString(R.string.main_menu_help));
		solo.waitForView(solo.getView(R.id.webView));
		solo.sleep(1000);
		solo.clickOnWebElement(By.id("alice-tut-mobile"));      //game design
		solo.sleep(1000);
		solo.goBack();
		//WebView object = (WebView) getActivity().findViewById((package name).R.id.(Webview ID));
		//solo.clickOnWebElement(
		solo.waitForWebElement(By.id("step-by-step-mobile"));
		solo.goBack();
		/*solo.clickOnWebElement(By.textConten
		t("STEP-BY-STEP INTRO"));  //
		solo.sleep(1000);
		solo.goBack();
		solo.clickOnWebElement(By.id("step-by-step-mobile")); //tutorials
		solo.sleep(1000);
		solo.goBack();
		solo.clickOnWebElement(By.className("help-split"));   //
		solo.sleep(1000);
		solo.goBack();*/
	}
	public void testStarters() {
		solo.clickOnButton(getActivity().getString(R.string.main_menu_help));
		solo.waitForView(solo.getView(R.id.webView));
		solo.sleep(1000);
		solo.clickOnWebElement(By.className("help-mobile"));
		solo.clickOnWebElement(By.id("step-by-step-mobile")); //tutorials

		solo.goBack();

	}
}

