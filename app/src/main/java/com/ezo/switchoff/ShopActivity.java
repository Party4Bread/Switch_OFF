package com.ezo.switchoff;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class ShopActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        int[][] SkinList = {
            {R.drawable.april_fake_bg, R.drawable.kaori_back, R.drawable.kaori_front},
            {R.drawable.card_bg, R.drawable.unknown_back, R.drawable.bicycle_front},
            //{R.drawable.concol_bg, R.drawable.off_back, R.drawable.on_front},
            {R.drawable.corpse_bg, R.drawable.yuuki_back, R.drawable.kaori_front},
            {R.drawable.rezero_bg, R.drawable.rem_back, R.drawable.ram_front},
            {R.drawable.sao_bg, R.drawable.yuuki_back, R.drawable.asuna_front},
            {R.drawable.your_name_bg, R.drawable.musebee_back, R.drawable.mitsuha_front}
        };
        GridLayout gl = (GridLayout) findViewById(R.id.shGV);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        final SharedPreferences pref;
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        for(int k=0;k<SkinList.length;k++) {
            int[] drv = SkinList[k];
            ImageButton switchbtn = new ImageButton(this);
            switchbtn.setId(View.generateViewId());
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.setGravity(Gravity.FILL);
            //lp.rowSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
            //lp.columnSpec=GridLayout.spec (GridLayout.UNDEFINED,1f);
            lp.width = width / 3;
            lp.height = width / 3;
            switchbtn.setLayoutParams(lp);
            switchbtn.setBackground(ContextCompat.getDrawable(ShopActivity.this,drv[0]));
            final int z = k;
            switchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pref.edit().putInt("skin", z).apply();
                }
            });
            gl.addView(switchbtn);
        }
    }
}
