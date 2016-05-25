package org.catrobat.catroid.CF;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import org.catrobat.catroid.R;

public class Change_Fonts extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fonts_change);

        RadioButton f1 = (RadioButton)findViewById(R.id.font1);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "FIRST");
                ed.commit();
                Toast.makeText(getApplicationContext(), R.string.please_restart_the_app, Toast.LENGTH_SHORT).show();
            }
        });

        RadioButton f2 = (RadioButton)findViewById(R.id.font2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "SECOND");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_the_app,Toast.LENGTH_SHORT).show();}});

        RadioButton f3 = (RadioButton)findViewById(R.id.font3);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "THIRD");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_the_app,Toast.LENGTH_SHORT).show();}});

        RadioButton f4 = (RadioButton)findViewById(R.id.font4);
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "FOURTH");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_the_app,Toast.LENGTH_SHORT).show();}});

        RadioButton f5 = (RadioButton)findViewById(R.id.font5);
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_for_default,Toast.LENGTH_SHORT).show();}});
        RadioButton f6 = (RadioButton)findViewById(R.id.font6);
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "FIFTH");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_the_app,Toast.LENGTH_SHORT).show();

            }
        });

        RadioButton f8 = (RadioButton)findViewById(R.id.font8);
        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "SEVENTH");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_for_default,Toast.LENGTH_SHORT).show();}});

        RadioButton f7 = (RadioButton)findViewById(R.id.font7);
        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "SIXTH");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_for_default,Toast.LENGTH_SHORT).show();}});
        RadioButton f9 = (RadioButton)findViewById(R.id.font9);
        f9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "EIGHTH");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_for_default,Toast.LENGTH_SHORT).show();}});

        RadioButton f10 = (RadioButton)findViewById(R.id.font10);
        f10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("ONLY_FONTS",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.clear().apply();
                ed.putString("TTF", "NINTH");
                ed.commit();
                Toast.makeText(getApplicationContext(),R.string.please_restart_for_default,Toast.LENGTH_SHORT).show();}});







    }

}
