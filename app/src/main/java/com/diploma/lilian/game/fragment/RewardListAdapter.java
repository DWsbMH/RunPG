package com.diploma.lilian.game.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diploma.lilian.database.entity.Reward;
import com.diploma.lilian.runpg.R;

import java.util.List;

public class RewardListAdapter extends BaseAdapter {

    private final Context context;
    private List<Reward> rewards;
    private final LayoutInflater mInflater;

    public RewardListAdapter(Context context, List<Reward> rewards) {

        this.context = context;
        this.rewards = rewards;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rewards.size();
    }

    @Override
    public Reward getItem(int position) {
        return rewards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.reward_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Reward item = getItem(position);

        holder.what.setText(item.getWhat());
        holder.whatFor.setText(item.getWhatFor());
        holder.when.setText(item.getWhen());

        return convertView;
    }

    public void setData(List<Reward> rewards) {
        this.rewards = rewards;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private TextView what;
        private TextView whatFor;
        private TextView when;

        ViewHolder(View convertView) {
            what = (TextView) convertView.findViewById(R.id.reward_what);
            whatFor = (TextView) convertView.findViewById(R.id.reward_what_for);
            when = (TextView) convertView.findViewById(R.id.reward_time);
        }

    }
}
