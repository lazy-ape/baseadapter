package com.lyape.baseadapterfordatabinding.list;

import android.content.Context;



import com.lyape.baseadapterfordatabinding.ItemViewDelegate;

import java.util.List;

public class CommonListAdapter<T> extends MultiItemTypeListAdapter<T> {

    public CommonListAdapter(Context context, final int layoutId, final int variableId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public int getVariableId() {
                return variableId;
            }
        });
    }
}
