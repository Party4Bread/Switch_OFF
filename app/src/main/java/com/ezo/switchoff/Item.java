package com.ezo.switchoff;

import android.widget.ImageButton;

/**
 * Created by solsa on 2017-07-27.
 */

public class Item {
    ImageButton btn;
    Item(ImageButton btn){
        this.btn=btn;
    }
    ImageButton getBtn(){
        return btn;
    }
}
