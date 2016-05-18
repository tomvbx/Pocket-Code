package org.catrobat.catroid.uitest.languages;

import android.content.res.Configuration;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import org.catrobat.catroid.Languages;
import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.MainMenuActivity;
import org.catrobat.catroid.ui.SettingsActivity;

import java.util.regex.Pattern;

/**
 * Created by NurELdin on 05/16/2016.
 */
public class LanguagesTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {

    private Solo solo;

    public LanguagesTest() {
        super(MainMenuActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        solo = null;
    }

    public void testLanguagesar() throws Exception {
        solo.sendKey(solo.MENU);
        solo.clickOnMenuItem(solo.getString(R.string.settings));
        solo.waitForActivity(SettingsActivity.class);
        solo.clickOnMenuItem(solo.getString(R.string.preference_title_change_language));
        solo.getCurrentActivity().getResources().getConfiguration();
        solo.waitForActivity(Languages.class);
        solo.clickOnMenuItem(solo.getString(R.string.arabic));
        solo.waitForActivity(MainMenuActivity.class);
        Configuration conf = solo.getCurrentActivity().getResources().getConfiguration();
        Assert.assertEquals(conf.locale.getLanguage(), "ar");
        Assert.assertTrue(solo.searchText(Pattern.quote("\u0628\u0648\u0643\u062a \u0643\u0648\u0648\u062f")));
        Languages.setContextLocale(getActivity(), "en");
        solo.getCurrentActivity().finishAffinity();
    }

    public void testLanguagesurdu() throws Exception {
        solo.sendKey(solo.MENU);
        solo.clickOnMenuItem(solo.getString(R.string.settings));
        solo.waitForActivity(SettingsActivity.class);
        solo.clickOnMenuItem(solo.getString(R.string.preference_title_change_language));
        solo.getCurrentActivity().getResources().getConfiguration();
        solo.waitForActivity(Languages.class);
        solo.clickOnMenuItem(solo.getString(R.string.urdu));
        solo.waitForActivity(MainMenuActivity.class);
        Configuration conf = solo.getCurrentActivity().getResources().getConfiguration();
        Assert.assertEquals(conf.locale.getLanguage(), "ur");
        Assert.assertTrue(solo.searchText(Pattern.quote("\u067e\u0627\u06a9\u0679 \u06a9\u0648\u0688")));
        Languages.setContextLocale(getActivity(), "en");
        solo.getCurrentActivity().finishAffinity();
    }
}
