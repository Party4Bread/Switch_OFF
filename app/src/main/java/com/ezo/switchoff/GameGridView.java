package com.ezo.switchoff;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;


import java.util.ArrayList;

/**
 * Created by solsa on 2017-07-27.
 */

public class GameGridView extends ArrayAdapter<Item> {
    Context context;
    int layoutResourceId;
    ArrayList<Item> data = new ArrayList<Item>();

    public GameGridView(Context context, int layoutResourceId, ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Switch holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new Switch();
            holder.btn = new ImageButton(this.context);
            row.setTag(holder);
        } else {
            holder = (Switch) row.getTag();
        }
        Item item = data.get(position);
        holder.btn=item.getBtn();
        return row;
    }

    static class Switch {
        ImageButton btn;
    }
}


