package com.example.autocompletetextviewproject.adapters;

import android.widget.Filter;

import com.example.autocompletetextviewproject.model.MyObject;

import java.util.ArrayList;
import java.util.List;

public class StoreFilter extends Filter {

    // -------------------- Interface ------------------

    public interface FilterStoresCallbacks {
        void dataSetChanged(List<MyObject> filterList);
        void dataSetInvalidated(List<MyObject> filterList);
    }

    // ----------------------- members -------------------------

    private List<MyObject> mStores;
    private List<MyObject> mFilterList;
    private FilterStoresCallbacks mCallbacks;


    public StoreFilter(FilterStoresCallbacks callbacks, List<MyObject> mStores) {
        this.mCallbacks = callbacks;
        this.mStores = mStores;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        MyObject store = (MyObject) resultValue;
        return store.getStoreName();
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence prefix) {
        if (prefix != null) {
            final String searchLowerCase = prefix.toString().toLowerCase();
            List<MyObject> matchingValues = new ArrayList<>();

            for(MyObject myObject : mStores) {
                if (myObject.getStoreName().toLowerCase().startsWith(searchLowerCase)) {
                    matchingValues.add(myObject);
                }
            }
            Filter.FilterResults result = new Filter.FilterResults();
            result.values = matchingValues;
            result.count = matchingValues.size();
            return result;
        } else {
            return new Filter.FilterResults();
        }
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        if (results.values != null) {
            mFilterList = new ArrayList<>();
            mFilterList = (List<MyObject>) results.values;
        } else {
            mFilterList = null;
        }

        if (mCallbacks != null) {
            if (results.count > 0) {
                mCallbacks.dataSetChanged(mFilterList);
            } else {
                mCallbacks.dataSetInvalidated(mFilterList);
            }
        }

    }
}

