package com.lyape.baseadapterfordatabinding.recycler;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.lyape.baseadapterfordatabinding.ItemViewDelegate;
import com.lyape.baseadapterfordatabinding.ItemViewDelegateManager;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public class MultiItemTypeRecyclerAdapter<T> extends RecyclerView.Adapter<MultiItemTypeRecyclerAdapter.BindingHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    protected ItemViewDelegateManager mItemViewDelegateManager;
    protected OnItemClickListener mOnItemClickListener;


    public MultiItemTypeRecyclerAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }


    @Override
    public MultiItemTypeRecyclerAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        ViewDataBinding dataBinding = DataBindingUtil.inflate(mInflater,layoutId,parent,false);
        BindingHolder holder = new BindingHolder(dataBinding.getRoot());
        holder.setBinding(dataBinding);
        return holder;
    }


    protected boolean isEnabled(int viewType) {
        return true;
    }


    @Override
    public void onBindViewHolder(MultiItemTypeRecyclerAdapter.BindingHolder holder, int position) {
        ItemViewDelegate delegate = mItemViewDelegateManager.getItemViewDelegate(getItem(position),position);
        ViewDataBinding dataBinding = holder.getBinding();
        dataBinding.setVariable(delegate.getVariableId(),getItem(position));
    }

    @Override
    public int getItemCount() {
        int itemCount = mDatas.size();
        return itemCount;
    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public MultiItemTypeRecyclerAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeRecyclerAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            if(mOnItemClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onItemClick(view,BindingHolder.this,getPosition());
                    }
                });
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return mOnItemClickListener.onItemLongClick(view,BindingHolder.this,getPosition());
                    }
                });
            }
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

}
