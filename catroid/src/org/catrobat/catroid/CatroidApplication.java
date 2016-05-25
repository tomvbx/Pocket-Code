/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2016 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.parrot.freeflight.settings.ApplicationSettings;

import org.catrobat.catroid.CF.TypefaceUtil;

public class CatroidApplication extends Application {

    private static final String TAG = CatroidApplication.class.getSimpleName();
    private ApplicationSettings settings;
    private static Context context;

    public static final String OS_ARCH = System.getProperty("os.arch");

    public static boolean parrotLibrariesLoaded = false;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences= getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
         String dd = sharedPreferences.getString("TTF","");
        if (dd.equals("FIRST")) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/DroidSans.ttf");
        }
        if (dd.equals("SECOND") ) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/simpo.ttf");
        }
        if (dd.equals("THIRD")) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/tahoma.ttf");
        }
        if (dd.equals("FOURTH")) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/arial.ttf");
        }
        if (dd.equals("FIFTH")) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/andlso.ttf");
        }
        if (dd.equals("SIXTH")) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/MEIRYO.TTC");
        }
        if (dd.equals("SEVENTH")) {
         TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/HelveticaNeueLTStd Lt.otf");
        }
        if (dd.equals("EIGHTH")) {
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/times.ttf");
        }
        if (dd.equals("NINTH")){
            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "FonT/BELL.TTF");
        }

        Log.d(TAG, "CatroidApplication onCreate");
        settings = new ApplicationSettings(this);
        CatroidApplication.context = getApplicationContext();
    }

    public ApplicationSettings getParrotApplicationSettings() {
        return settings;
    }

    public static synchronized boolean loadNativeLibs() {
        if (parrotLibrariesLoaded) {
            return true;
        }

        try {
            System.loadLibrary("avutil");
            System.loadLibrary("swscale");
            System.loadLibrary("avcodec");
            System.loadLibrary("avfilter");
            System.loadLibrary("avformat");
            System.loadLibrary("avdevice");
            System.loadLibrary("adfreeflight");
            parrotLibrariesLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, Log.getStackTraceString(e));
            parrotLibrariesLoaded = false;
        }
        return parrotLibrariesLoaded;
    }

    public static Context getAppContext() {
        return CatroidApplication.context;
    }
}
