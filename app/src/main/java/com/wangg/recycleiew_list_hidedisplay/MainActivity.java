package com.wangg.recycleiew_list_hidedisplay;

import android.renderscript.Type;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	//test 01
    public List<String> array;//数据
    private RecyclerView timelistView;
    private TimeListViewAdapter timeListViewAdapter;
    private FloatingActionButton deleteFloatBtn;
    List<String> deleteData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);
        initData();
        initView();
    }

    private void initData(){
        array = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            array.add("13:1" + i);
        }
    }

    private void initView(){
        timelistView = (RecyclerView) findViewById(R.id.timelist_reccycleView);
        deleteFloatBtn = (FloatingActionButton) findViewById(R.id.delete_floatbtn);

        timelistView.setLayoutManager(new LinearLayoutManager(this));
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(0xFFDDDDDD);
        timelistView.addItemDecoration(dividerLine);
//        timelistView.setItemAnimator(new DefaultItemAnimator());  //Goole默认动画效果

        timeListViewAdapter = new TimeListViewAdapter(array);
        timelistView.setItemAnimator(new SlideInOutLeftItemAnimator(timelistView));
        timelistView.setAdapter(timeListViewAdapter);

        deleteFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sign = true;
                timeListViewAdapter.setSignDelete(sign);
                timeListViewAdapter.notifyDataSetChanged();
            }
        });

        timeListViewAdapter.setmOnItemClickListener(new TimeListViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String s = array.get(position);
                if(s != null &&timeListViewAdapter.getSignDelete()== true){

                }
            }
        });
    }


}
