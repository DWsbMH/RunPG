package com.diploma.lilian.game.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.diploma.lilian.database.entity.Item;
import com.diploma.lilian.runpg.R;

import java.util.ArrayList;
import java.util.List;

public class EquipmentItemRowAdapter<T extends Item> extends RecyclerView.Adapter<EquipmentItemRowAdapter<T>.ViewHolder> {

    private static final String TAG = "EquipmentItemRowAdapter";

    private final Context context;
    private final List<T> items;
    private LayoutInflater layoutInflater;
    private PopupWindow popupWindow;

    public EquipmentItemRowAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = new ArrayList<>(items);
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public EquipmentItemRowAdapter<T>.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipment_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EquipmentItemRowAdapter<T>.ViewHolder holder, int position) {
        holder.ivEquipmentItem.setImageResource(
                context.getResources().getIdentifier(items.get(position).getImageName(),"drawable",
                        context.getPackageName())
        );
        holder.ivEquipmentItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG, "onLongClick: ");
                View contentView = layoutInflater.inflate(R.layout.equipment_popupwindow_content, null);

                if(popupWindow!=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }

                popupWindow = new PopupWindow(contentView,LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, false);

                contentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                int[] location = new int[2];
                v.getLocationInWindow(location);
                popupWindow.showAtLocation(contentView, Gravity.NO_GRAVITY , location[0], location[1]+v.getHeight());
                return true;
            }
        });
        holder.btnEquipmentItem.setText("EQUIP");
        holder.btnEquipmentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivEquipmentItem;
        Button btnEquipmentItem;

        ViewHolder(View view) {
            super(view);
            ivEquipmentItem = (ImageView) view.findViewById(R.id.iv_equipment_item);
            btnEquipmentItem = (Button) view.findViewById(R.id.btn_equipment_item);
        }
    }
}
