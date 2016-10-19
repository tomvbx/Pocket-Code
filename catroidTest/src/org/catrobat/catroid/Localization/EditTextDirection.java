package org.catrobat.catroid.Localization;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by aiman awwad on 10/11/2016.
 */
public class EditTextDirection
{
    public static Matcher<Object> editTextShouldHaveDirection(int expectedDirection) {
        return UIElementTextShouldHaveDirection(equalTo(expectedDirection));
    }
    private static Matcher<Object> UIElementTextShouldHaveDirection(final Matcher<Integer> expectedObject) {
        final int[] direction = new int[1];
        return new BoundedMatcher<Object, EditText>( EditText.class) {
            @Override
            public boolean matchesSafely(final EditText actualObject) {

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
                description.appendText("EditText Direction Text did not match "+ direction[0]);
            }
        };
    }


}
