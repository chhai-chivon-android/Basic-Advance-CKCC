package com.nuon.peter.demoapp.base.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nuon.peter.demoapp.base.adapters.models.Option;
import java.util.List;

public class FileArrayAdapter extends ArrayAdapter<Option> {

    private LayoutInflater mLayoutInflater;
    private int id;
    private List<Option> mItems;

    public FileArrayAdapter(Context context, int textViewResourceId, List<Option> items) {
        super(context, textViewResourceId, items);
        id = textViewResourceId;
        mItems = items;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Option getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(id, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Option option = mItems.get(position);
        if (option != null) {
            holder.text1.setText(option.getName());
            holder.text2.setText(option.getData());
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(android.R.id.text1) TextView text1;
        @BindView(android.R.id.text2) TextView text2;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

}
