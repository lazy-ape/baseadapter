package com.lyape.baseadapterfordatabinding.recycler;

import android.content.Context;
import android.view.LayoutInflater;



import com.lyape.baseadapterfordatabinding.ItemViewDelegate;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public class CommonRecyclerAdapter<T> extends MultiItemTypeRecyclerAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonRecyclerAdapter(final Context context, final int layoutId, final int variableId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

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
