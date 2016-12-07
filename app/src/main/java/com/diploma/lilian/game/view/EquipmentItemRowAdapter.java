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
import android.widget.TextView;

import com.diploma.lilian.database.entity.Item;
import com.diploma.lilian.database.entity.Potion;
import com.diploma.lilian.database.entity.Weapon;
import com.diploma.lilian.game.OnEquipInventoryListener;
import com.diploma.lilian.game.OnEquipShopListener;
import com.diploma.lilian.runpg.R;

import java.util.ArrayList;
import java.util.List;

public class EquipmentItemRowAdapter<T extends Item> extends RecyclerView.Adapter<EquipmentItemRowAdapter<T>.ViewHolder> {

    private static final String TAG = "EquipmentItemRowAdapter";

    private final Context context;
    private final List<T> items;
    private LayoutInflater layoutInflater;
    private PopupWindow popupWindow;
    private OnEquipInventoryListener onEquipInventoryListener;
    private OnEquipShopListener onEquipShopListener;
    private String buttonText;

    public EquipmentItemRowAdapter(Context context, List<T> items, OnEquipInventoryListener onEquipInventoryListener) {
        this.context = context;
        this.items = new ArrayList<>(items);
        this.onEquipInventoryListener = onEquipInventoryListener;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        buttonText = "EQUIP";
    }

    public EquipmentItemRowAdapter(Context context, List<T> items, OnEquipShopListener onEquipShopListener, String type) {
        this.context = context;
        this.items = new ArrayList<>(items);
        this.onEquipShopListener = onEquipShopListener;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        buttonText = type;
    }

    @Override
    public EquipmentItemRowAdapter<T>.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipment_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EquipmentItemRowAdapter<T>.ViewHolder holder, final int position) {


        holder.ivEquipmentItem.setImageResource(
                context.getResources().getIdentifier(items.get(position).getImageName(), "drawable",
                        context.getPackageName())
        );
        holder.ivEquipmentItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG, "onLongClick: ");
                View contentView = layoutInflater.inflate(R.layout.equipment_popupwindow_content, null);

                closePopup();

                int pos = holder.getAdapterPosition();

                TextView equipmentDetails = (TextView) contentView.findViewById(R.id.equipment_details);
                TextView equipmentPopupName = (TextView) contentView.findViewById(R.id.equipment_popup_name);

                equipmentPopupName.setText(items.get(pos).getName());

                String details = "";
                if (items.get(pos) instanceof Weapon) {
                    details += ((Weapon) items.get(pos)).getMinDamage();
                    details += " - ";
                    details += ((Weapon) items.get(pos)).getMaxDamage();
                    details += " damage";
                } else if (items.get(pos) instanceof Potion) {
                    details += String.valueOf(((Potion) items.get(pos)).getEffect().getDurability());
                    details += " more round(s)";
                }

                equipmentDetails.setText(details);
                popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, false);

                contentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                int[] location = new int[2];
                v.getLocationInWindow(location);
                popupWindow.showAtLocation(contentView, Gravity.NO_GRAVITY, location[0], location[1] - 150);
                return true;
            }
        });
        holder.btnEquipmentItem.setText(buttonText);

        if (buttonText.equals("EQUIP")) {
            holder.btnEquipmentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEquipInventoryListener != null) {
                        Item item = onEquipInventoryListener.equipItem(items.get(holder.getAdapterPosition()));
                        items.remove(items.get(holder.getAdapterPosition()));
                        if (item != null) {
                            items.add((T) item);
                        }
                        notifyDataSetChanged();
                    } else {
                        throw new NullPointerException("Listener cannot be null!");
                    }
                }
            });
        } else if(buttonText.equals("SELL")){
            holder.btnEquipmentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEquipShopListener != null) {
                        onEquipShopListener.sellItem(items.get(holder.getAdapterPosition()));
                        items.remove(items.get(holder.getAdapterPosition()));
                        notifyDataSetChanged();
                    } else {
                        throw new NullPointerException("Listener cannot be null!");
                    }
                }
            });
        } else {
            holder.btnEquipmentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEquipShopListener != null) {
                        boolean successfulBuy = onEquipShopListener.buyItem(items.get(holder.getAdapterPosition()));
                        if(successfulBuy) {
                            items.remove(items.get(holder.getAdapterPosition()));
                        }
                        notifyDataSetChanged();
                    } else {
                        throw new NullPointerException("Listener cannot be null!");
                    }
                }
            });
        }
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

    public void add(Item item){
        items.add((T) item);
        notifyDataSetChanged();
    }

    public void closePopup(){
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }

}
