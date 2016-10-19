package org.catrobat.catroid.Localization;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.widget.Button;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by aiman awwad on 10/11/2016.
 */
public class ButtonTextDirection
{
    public static Matcher<Object> buttonShouldHaveDirection(int expectedDirection) {
        return UIElementTextShouldHaveDirection(equalTo(expectedDirection));
    }
    private static Matcher<Object> UIElementTextShouldHaveDirection(final Matcher<Integer> expectedObject) {
        final int[] direction = new int[1];
        return new BoundedMatcher<Object, Button>( Button.class) {
            @Override
            public boolean matchesSafely(final Button actualObject) {

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
                description.appendText("Button Text Direction did not match "+ direction[0]);
            }
        };
    }


}
