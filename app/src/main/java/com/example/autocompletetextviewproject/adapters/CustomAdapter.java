package com.example.autocompletetextviewproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.autocompletetextviewproject.R;
import com.example.autocompletetextviewproject.model.MyObject;

import java.util.List;

public class CustomAdapter extends ArrayAdapter implements StoreFilter.FilterStoresCallbacks {
    private StoreFilter mStoreFilter;
    private List<MyObject> mFilterList;
    private Context context;
    private int layoutId;

    public CustomAdapter(Context context, int resourceId, List<MyObject> stores) {
        super(context, resourceId, stores);
        this.context = context;
        this.layoutId = resourceId;

        mStoreFilter = new StoreFilter(this, stores);
    }

    @Override
    public int getCount() {
        return mFilterList.size();
    }

    @Override
    public MyObject getItem(int position) {
        return mFilterList.get(position);
    }

    @Override
    public Filter getFilter() {
        return mStoreFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }

        TextView storeName = convertView.findViewById(R.id.store_name);
        storeName.setText(getItem(position).getStoreName());

        TextView location = convertView.findViewById(R.id.location);
        location.setText(getItem(position).getLocation());
        return convertView;
    }

    @Override
    public void dataSetChanged(List<MyObject> filterList) {
        mFilterList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void dataSetInvalidated(List<MyObject> filterList) {
        mFilterList = filterList;
        notifyDataSetInvalidated();
    }
}

