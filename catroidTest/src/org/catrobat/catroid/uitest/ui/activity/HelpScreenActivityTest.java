package org.catrobat.catroid.uitest.ui.activity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.webkit.WebView;
import android.widget.EditText;

import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;

import org.catrobat.catroid.R;
import org.catrobat.catroid.common.Constants;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.WebViewActivity;
import org.catrobat.catroid.ui.dialogs.NewProjectDialog;

import java.util.ArrayList;

/**
 * Created by Sareh on 08.06.2016.
 */
public class HelpScreenActivityTest extends ActivityInstrumentationTestCase2<WebViewActivity>{

	private Solo solo;

	public HelpScreenActivityTest (){
		super(WebViewActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		Intent intent = new Intent();
		intent.putExtra(WebViewActivity.TEXT_RTL, true);
		intent.putExtra(WebViewActivity.INTENT_PARAMETER_URL, Constants.CATROBAT_HELP_URL);
		this.setActivityIntent(intent);

		solo= new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	public boolean isRtl() {
		solo.waitForWebElement(By.tagName("html"));
		ArrayList<WebElement> tags;

		for(int i=0; i < 10; i++) {
			solo.sleep(500);
			tags = solo.getWebElements(By.tagName("html"));
			assertEquals(1, tags.size());
			if (tags.get(0).getAttribute("dir") != null)
				break;
		}

		tags = solo.getWebElements(By.tagName("html"));
		assertEquals(1, tags.size());
		String dir = tags.get(0).getAttribute("dir");
		assertNotNull(dir);
		return dir.equals("rtl");
	}

	public void testStarters() {
		solo.waitForView(solo.getView(R.id.webView));
		solo.sleep(1000);
		solo.clickOnWebElement(By.id("starters"));

		assertTrue(isRtl());
	}
}

