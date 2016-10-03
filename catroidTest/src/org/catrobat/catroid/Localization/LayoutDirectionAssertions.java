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
public class LayoutDirectionAssertions {
    public static ViewAssertion isLayoutDirectionRTL() {
        return new ViewAssertion() {
            public void check(View view, NoMatchingViewException noView) {
                assertThat(view, new LayoutDirectionMatcher(View.LAYOUT_DIRECTION_RTL));
            }
        };
    }

    private static class LayoutDirectionMatcher extends BaseMatcher<View> {

        private int layoutDirection;

        public LayoutDirectionMatcher(int layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        @Override public void describeTo(Description description) {
            String LayoutDirection;
            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) LayoutDirection = "Right to Left";
            else LayoutDirection = "Left to Right";
            description.appendText("View LayoutDirection must has equals " + LayoutDirection);
        }

        @Override public boolean matches(Object o) {

            if (o == null) {
                if (layoutDirection == View.LAYOUT_DIRECTION_RTL) return true;
                else if (layoutDirection == View.LAYOUT_DIRECTION_LTR) return false;
            }

            if (!(o instanceof View))
                throw new IllegalArgumentException("Object must be instance of View. Object is instance of " + o);
            return ((View) o).getLayoutDirection() == layoutDirection;
        }
    }
}