package org.catrobat.catroid.Localization;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.view.View;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aiman awwad on 9/29/2016.
 */
public class TextDirectionAssertions {
    public static ViewAssertion isTextDirectionRTL() {
        return new ViewAssertion() {
            public void check(View view, NoMatchingViewException noView) {
                assertThat(view, new TextDirectionMatcher(View.TEXT_DIRECTION_FIRST_STRONG));
            }
        };
    }



    private static class TextDirectionMatcher extends BaseMatcher<View> {

        private int textDirection;

        public TextDirectionMatcher(int textDirection) {
            this.textDirection = textDirection;
        }

        @Override public void describeTo(Description description) {
            String TextDirection;
            if (textDirection == View.TEXT_DIRECTION_FIRST_STRONG) TextDirection = "Right to Left";
            else TextDirection = "Left to Right";
            description.appendText("View TextDirection must has equals " + TextDirection);
        }

        @Override public boolean matches(Object o) {

            if (o == null) {
                if (textDirection == View.TEXT_DIRECTION_FIRST_STRONG) return true;
                else if (textDirection == View.TEXT_DIRECTION_LTR) return false;
            }

            if (!(o instanceof View))
                throw new IllegalArgumentException("Object must be instance of View. Object is instance of " + o);
            return ((View) o).getTextDirection() == textDirection;
        }
    }
}