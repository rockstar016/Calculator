/*
 * Copyright (C) 2016 Jared Rummler <jared.rummler@gmail.com>
 * Copyright (C) 2015 Daniel Nilsson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package calc.rock.calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jrummyapps.android.colorpicker.ColorPickerView;

import calc.rock.calculator.R;
import calc.rock.calculator.Utils.Constants;

import static calc.rock.calculator.R.styleable.ColorPanelView;
import static calc.rock.calculator.R.styleable.ColorPickerView;

public class ColorPickerActivity extends Activity implements com.jrummyapps.android.colorpicker.ColorPickerView.OnColorChangedListener, View.OnClickListener {

    private ColorPickerView colorPickerView;
    private com.jrummyapps.android.colorpicker.ColorPanelView newColorPanelView;
    private TextView        colorValue;
    String  color_key;
    TextView                oldColorValue;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.activity_color_picker);

        Intent intent = getIntent();
        color_key = intent.getStringExtra("Color");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int initialColor = prefs.getInt(color_key, 0xFF000000);

        colorValue = (TextView)findViewById(R.id.colorValue);
        oldColorValue = (TextView)findViewById(R.id.oldColorValue);
        colorPickerView = (ColorPickerView) findViewById(R.id.cpv_color_picker_view);
        com.jrummyapps.android.colorpicker.ColorPanelView colorPanelView = (com.jrummyapps.android.colorpicker.ColorPanelView) findViewById(R.id.cpv_color_panel_old);
        newColorPanelView = (com.jrummyapps.android.colorpicker.ColorPanelView) findViewById(R.id.cpv_color_panel_new);
        String  oldColorStr = "#"+(Integer.toHexString(initialColor)).toUpperCase();
        oldColorValue.setText(oldColorStr);
        Button btnOK = (Button) findViewById(R.id.okButton);
        Button btnCancel = (Button) findViewById(R.id.cancelButton);

        ((LinearLayout) colorPanelView.getParent())
                .setPadding(colorPickerView.getPaddingLeft(), 0, colorPickerView.getPaddingRight(), 0);

        colorPickerView.setOnColorChangedListener(this);
        colorPickerView.setColor(initialColor, true);
        colorPanelView.setColor(initialColor);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override public void onColorChanged(int newColor) {
        newColorPanelView.setColor(colorPickerView.getColor());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String colorStr = "#"+(Integer.toHexString(colorPickerView.getColor())).toUpperCase();
                colorValue.setText(colorStr);
            }
        });


    }

    @Override public void onClick(View v) {
        Intent intent = new Intent(ColorPickerActivity.this,SettingActivity.class);
        switch (v.getId()) {
            case R.id.okButton:
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
                edit.putInt(color_key, colorPickerView.getColor());
                edit.apply();

                break;
            case R.id.cancelButton:

                break;
        }
        startActivity(intent);
        finish();
    }

}
