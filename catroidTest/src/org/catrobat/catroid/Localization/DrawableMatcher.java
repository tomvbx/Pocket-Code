package org.catrobat.catroid.Localization;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Aiman Awwad on 10/18/2016.
 */
public class DrawableMatcher  {


    public static Matcher<View> isBitmapTheSame(final Context context, final int resourceId) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has the same Bitmap as the resource with id=");
                description.appendValue(resourceId);
                description.appendText(" has.");
            }

            @Override
            public boolean matchesSafely(ImageView view) {
                Bitmap bitmapCompare = BitmapFactory.decodeResource(context.getResources(), resourceId);
                Drawable drawable = view.getDrawable();
                Bitmap bitmap = drawableToBitmap(drawable);
                boolean result = bitmapCompare.sameAs(bitmap);
                return result;
            }
        };
    }


    public static Matcher<View> isBitmapTheSame(final Drawable drawable) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has the same Bitmap as this drawable: ");
                description.appendValue(drawable);
                description.appendText(" has.");
            }

            @Override
            public boolean matchesSafely(ImageView view) {
                Bitmap bitmapCompare = drawableToBitmap(drawable);
                Drawable drawable = view.getDrawable();
                Bitmap bitmap = drawableToBitmap(drawable);
                return bitmapCompare.sameAs(bitmap);
            }
        };
    }


    private static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }














































   /* public static Matcher<View> withImageDrawable(final int resourceId) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has image drawable resource " + resourceId);
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return sameBitmap(imageView.getContext(), imageView.getDrawable(), resourceId);
            }
        };
    }

    private static boolean sameBitmap(Context context, Drawable drawable, int resourceId) {
        Drawable otherDrawable = context.getResources().getDrawable(resourceId);
        if (drawable == null || otherDrawable == null) {
            return false;
        }
        if (drawable instanceof StateListDrawable && otherDrawable instanceof StateListDrawable) {
            drawable = drawable.getCurrent();
            otherDrawable = otherDrawable.getCurrent();
        }
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap otherBitmap = ((BitmapDrawable) otherDrawable).getBitmap();
            return bitmap.sameAs(otherBitmap);
        }
        return false;
    }
*/









































   /* private final int expectedId;
    String resourceName;
    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }
    public DrawableMatcher(int expectedId) {
        super(View.class);
        this.expectedId = expectedId;
    }

    @Override
    protected boolean matchesSafely(View target) {
        if (!(target instanceof ImageView)){
            return false;
        }
        ImageView imageView = (ImageView) target;
        if (expectedId < 0){
            return imageView.getDrawable() == null;
        }
        Resources resources = target.getContext().getResources();
        Drawable expectedDrawable = resources.getDrawable(expectedId);
        resourceName = resources.getResourceEntryName(expectedId);

        if (expectedDrawable == null) {
            return false;
        }

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap otherBitmap = ((BitmapDrawable) expectedDrawable).getBitmap();
        return bitmap.sameAs(otherBitmap);
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("with drawable from resource id: ");
        description.appendValue(expectedId);
        if (resourceName != null) {
            description.appendText("[");
            description.appendText(resourceName);
            description.appendText("]");
        }
    }*/
}