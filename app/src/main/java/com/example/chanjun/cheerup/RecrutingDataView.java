package com.example.chanjun.cheerup;

/**
 * Created by chanjun on 2017. 6. 23..
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecrutingDataView extends LinearLayout {
    TextView textView;

    public RecrutingDataView(Context context){
        super(context);
        init(context);
    }
    public RecrutingDataView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_item, this, true);

        textView = (TextView) findViewById(R.id.mainTextView1);
    }

    public void setName(String name) {
        textView.setText(name);
    }

}
