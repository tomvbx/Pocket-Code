package org.catrobat.catroid.ui.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ersatz2 on 5/4/2016.
 */
public class DateTimeDialogFragment extends DialogFragment {
    public static final String DIALOG_FRAGMENT_TAG = "dialog_about_pocketcode";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time


    }

}

