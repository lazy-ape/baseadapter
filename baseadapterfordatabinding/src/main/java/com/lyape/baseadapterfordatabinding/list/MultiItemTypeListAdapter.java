package com.lyape.baseadapterfordatabinding.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import com.lyape.baseadapterfordatabinding.ItemViewDelegate;
import com.lyape.baseadapterfordatabinding.ItemViewDelegateManager;

import java.util.List;


/**
 * Created by xuqiang on 2016/12/22.
 */

public class MultiItemTypeListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    private LayoutInflater mInflater;

    private ItemViewDelegateManager mItemViewDelegateManager;

    private ViewDataBinding mViewDataBinding;

    public MultiItemTypeListAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
        mInflater = LayoutInflater.from(context);
    }

    public MultiItemTypeListAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager())
            return mItemViewDelegateManager.getItemViewDelegateCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            int viewType = mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(mDatas.get(position), position);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        if (convertView == null)
        {
            mViewDataBinding = DataBindingUtil.inflate(mInflater,layoutId,parent,false);
            convertView = mViewDataBinding.getRoot();
            convertView.setTag(mViewDataBinding);

        } else
        {
           mViewDataBinding = (ViewDataBinding) convertView.getTag();
        }

        mViewDataBinding.setVariable(itemViewDelegate.getVariableId(),getItem(position));
//        mViewDataBinding.executePendingBindings();
        return convertView;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
