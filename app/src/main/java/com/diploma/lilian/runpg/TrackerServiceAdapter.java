package com.diploma.lilian.runpg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.diploma.lilian.database.entity.TrackerService;

import java.util.List;

public class TrackerServiceAdapter extends BaseAdapter {

    private List<TrackerService> mDataSet;
    private OnConnectListener onConnectListener;
    private LayoutInflater mInflater;

    public TrackerServiceAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void loadConnectibleTrackerServices(List<TrackerService> connectibleServices) {
        mDataSet = connectibleServices;
    }

    public void setOnConnectListener(OnConnectListener onConnectListener) {
        this.onConnectListener = onConnectListener;
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDataSet.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.connect_to_item, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.getName().setText(mDataSet.get(position).getName());
        holder.getConnect().setText("Connect");
        holder.getConnect().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConnectListener.onConnect(mDataSet.get(position));
            }
        });
        return convertView;
    }

    public void removeConnectedTrackerService(TrackerService tracker) {
        int position = mDataSet.indexOf(tracker);
        mDataSet.remove(position);
        notifyDataSetChanged();
    }


    private static class ViewHolder {
        private TextView name;
        private Button connect;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.connect_to_name);
            connect = (Button) convertView.findViewById(R.id.connect);
        }

        public TextView getName() {
            return name;
        }

        public Button getConnect() {
            return connect;
        }
    }
}
