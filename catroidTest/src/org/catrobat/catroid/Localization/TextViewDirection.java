package org.catrobat.catroid.Localization;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by aiman awwad on 10/19/2016.
 */
public class TextViewDirection
{
    public static Matcher<Object> textViewShouldHaveDirection(int expectedDirection) {
        return UIElementTextShouldHaveDirection(equalTo(expectedDirection));
    }
    private static Matcher<Object> UIElementTextShouldHaveDirection(final Matcher<Integer> expectedObject) {
        final int[] direction = new int[1];
        return new BoundedMatcher<Object, TextView>( TextView.class) {
            @Override
            public boolean matchesSafely(final TextView actualObject) {

                direction[0] =actualObject.getTextDirection();

                if( expectedObject.matches(direction[0])) {
                    return true;
                } else {
                    return false;
                }
            }
            @Override
            public void describeTo(final Description description) {
                // Should be improved!
                description.appendText("TextView Direction Text did not match "+ direction[0]);
            }
        };
    }


}
