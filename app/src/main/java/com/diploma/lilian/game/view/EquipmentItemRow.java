package com.diploma.lilian.game.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.diploma.lilian.runpg.R;

public class EquipmentItemRow extends RelativeLayout {

    private RecyclerView row;

    public EquipmentItemRow(Context context) {
        super(context);
        init();
    }

    public EquipmentItemRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EquipmentItemRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.equipment_row, this, true);
        setupRow(view);
    }

    private void setupRow(View parent) {
        row = (RecyclerView) parent.findViewById(R.id.equipment_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        row.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        row.setAdapter(adapter);
    }


}
