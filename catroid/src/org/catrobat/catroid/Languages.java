package org.catrobat.catroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import org.catrobat.catroid.ui.MainMenuActivity;

import java.util.Locale;

/**
 * Created by NurELdin on 05/10/2016.
 */
public class Languages extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_languages);
    }

    public void ln_ar(View view) {
        setLocale("ar");
    }

    public void ln_bs(View view) {
        setLocale("bs");
    }

    public void ln_da(View view) {
        setLocale("da");
    }

    public void ln_de(View view) {
        setLocale("de");
    }

    public void ln_enrau(View view) {
        setLocale("en-rAU");
    }

    public void ln_enrca(View view) {
        setLocale("en-rCA");
    }

    public void ln_enrgb(View view) {
        setLocale("en-rGB");
    }

    public void ln_es(View view) {
        setLocale("es");
    }

    public void ln_fa(View view) {
        setLocale("fa");
    }

    public void ln_fr(View view) {
        setLocale("fr");
    }

    public void ln_gu(View view) {
        setLocale("gu");
    }

    public void ln_hi(View view) {
        setLocale("hi");
    }

    public void ln_hr(View view) {
        setLocale("hr");
    }

    public void ln_hu(View view) {
        setLocale("hu");
    }

    public void ln_in(View view) {
        setLocale("in");
    }

    public void ln_it(View view) {
        setLocale("it");
    }

    public void ln_ja(View view) {
        setLocale("ja");
    }

    public void ln_ko(View view) {
        setLocale("ko");
    }

    public void ln_mk(View view) {
        setLocale("mk");
    }

    public void ln_ms(View view) {
        setLocale("ms");
    }

    public void ln_nl(View view) {
        setLocale("nl");
    }

    public void ln_no(View view) {
        setLocale("no");
    }

    public void ln_pl(View view) {
        setLocale("pl");
    }

    public void ln_ps(View view) {
        setLocale("ps");
    }

    public void ln_pt(View view) {
        setLocale("pt");
    }

    public void ln_ptrbr(View view) {
        setLocale("pt-rBR");
    }

    public void ln_ro(View view) {
        setLocale("ro");
    }

    public void ln_ru(View view) {
        setLocale("ru");
    }

    public void ln_sd(View view) {
        setLocale("sd");
    }

    public void ln_sl(View view) {
        setLocale("sl");
    }

    public void ln_srrcs(View view) {
        setLocale("sr-rCS");
    }

    public void ln_srrsp(View view) {
        setLocale("sr-rSP");
    }

    public void ln_sv(View view) {
        setLocale("sv");
    }

    public void ln_ta(View view) {
        setLocale("ta");
    }

    public void ln_te(View view) {
        setLocale("te");
    }

    public void ln_th(View view) {
        setLocale("th");
    }

    public void ln_tr(View view) {
        setLocale("tr");
    }

    public void ln_ur(View view) {
        setLocale("ur");
    }

    public void ln_vi(View view) {
        setLocale("vi");
    }

    public void ln_zhrcn(View view) {
        setLocale("zh-rCN");
    }

    public void ln_zhrtw(View view) {
        setLocale("zh-rTW");
    }

    static public void setContextLocale(Context context, String lang) {
        Locale Language = new Locale(lang);
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        conf.locale = Language;
        conf.setLayoutDirection(Language);
        resources.updateConfiguration(conf, displayMetrics);
    }

    public void setLocale(String lang) {
        setContextLocale(this, lang);
        Intent intent = new Intent(Languages.this, MainMenuActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}




