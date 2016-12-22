package com.lyape.baseadapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.lyape.baseadapter.databinding.ActivityRecyclerBinding;
import com.lyape.baseadapterfordatabinding.ItemViewDelegate;
import com.lyape.baseadapterfordatabinding.recycler.MultiItemTypeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqiang on 2016/12/22.
 */

public class RecyclerActivity extends AppCompatActivity {

    private ActivityRecyclerBinding recyclerBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerBinding = DataBindingUtil.setContentView(this,R.layout.activity_recycler);


        final List<PersonEntity> personEntities = new ArrayList<>();
        for (int i = 0 ;i < 30 ; i++ ){
            PersonEntity entity = new PersonEntity();
            entity.name = "name" + i;
            entity.age = i;
            if(i % 2 == 0){
                entity.isLeft = false;
            }else{
                entity.isLeft = true;
            }
            personEntities.add(entity);
        }

        MultiItemTypeRecyclerAdapter typeListAdapter = new MultiItemTypeRecyclerAdapter(this,personEntities);
        typeListAdapter.addItemViewDelegate(new ItemViewDelegate<PersonEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.list_item;
            }

            @Override
            public boolean isForViewType(PersonEntity item, int position) {
                return item.isLeft();
            }

            @Override
            public int getVariableId() {
                return com.lyape.baseadapter.BR.person;
            }
        });
        typeListAdapter.addItemViewDelegate(new ItemViewDelegate<PersonEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.list_item2;
            }

            @Override
            public boolean isForViewType(PersonEntity item, int position) {
                return !item.isLeft();
            }

            @Override
            public int getVariableId() {
                return com.lyape.baseadapter.BR.person;
            }
        });
        typeListAdapter.setOnItemClickListener(new MultiItemTypeRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                PersonEntity entity = personEntities.get(position);
                Toast.makeText(RecyclerActivity.this,entity.name,Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        recyclerBinding.recycler.setAdapter(typeListAdapter);

    }
}
