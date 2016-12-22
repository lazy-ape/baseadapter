package com.lyape.baseadapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.lyape.baseadapter.databinding.ActivityMainBinding;
import com.lyape.baseadapterfordatabinding.list.CommonListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        final List<String> entityList = new ArrayList<>();
        entityList.add("listview");
        entityList.add("RecyclerView");


        mainBinding.list.setAdapter(new CommonListAdapter<String>(this,R.layout.text_item, com.lyape.baseadapter.BR.text,entityList));
        mainBinding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(MainActivity.this,ListActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,RecyclerActivity.class));
                        break;
                }
            }
        });

    }
}
