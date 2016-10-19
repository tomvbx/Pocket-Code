package org.catrobat.catroid.Localization;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.catrobat.catroid.ui.BrickLayout;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by Aiman Awwad on 10/17/2016.
 */
public class MirroringAssertions
{
    public static Matcher<Object> UserBrickLayoutShouldBeMirrored(boolean isMirrored) {
        return LayoutShouldHaveMirroredValue(equalTo(isMirrored));
    }
    private static Matcher<Object> LayoutShouldHaveMirroredValue(final Matcher<Boolean> expectedObject) {
        final boolean[] isAutoMirrored = new boolean[1];
        return new BoundedMatcher<Object, BrickLayout>( BrickLayout.class) {
            @Override
            public boolean matchesSafely(final BrickLayout actualObject) {

                 isAutoMirrored[0] = actualObject.getBackground().isAutoMirrored();

                if( expectedObject.matches(isAutoMirrored[0])) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void describeTo(final Description description) {
                // Should be improved!
                description.appendText("Layout Mirroring did not match "+ isAutoMirrored[0]);
            }
        };
    }


}
