package com.lyape.baseadapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.lyape.baseadapter.databinding.ActivityListBinding;
import com.lyape.baseadapterfordatabinding.ItemViewDelegate;
import com.lyape.baseadapterfordatabinding.list.MultiItemTypeListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqiang on 2016/12/22.
 */

public class ListActivity extends AppCompatActivity{

    private ActivityListBinding listBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listBinding = DataBindingUtil.setContentView(this,R.layout.activity_list);


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

        MultiItemTypeListAdapter typeListAdapter = new MultiItemTypeListAdapter(this,personEntities);
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
        listBinding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PersonEntity entity = personEntities.get(i);
                Toast.makeText(ListActivity.this,entity.name,Toast.LENGTH_LONG).show();
            }
        });

        listBinding.list.setAdapter(typeListAdapter);

    }
}
