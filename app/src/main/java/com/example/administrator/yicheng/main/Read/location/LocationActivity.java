package com.example.administrator.yicheng.main.Read.location;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.example.administrator.yicheng.R;
import com.example.administrator.yicheng.adapter.MyCityAdapter;
import com.example.administrator.yicheng.base.BaseActivity;
import com.example.administrator.yicheng.bean.City;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import butterknife.BindView;



/**
 * Created by Jensen on 2016/8/5.
 */
public class LocationActivity extends BaseActivity implements LocationContract.View ,OnQuickSideBarTouchListener{

    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;
    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;
    @BindView(R.id.listView)
    ListView listView;
    private LocationPresenter presenter;
      HashMap<String, Integer> letters = new HashMap<>();
    private List<City> cities = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                List<City> list = (List<City>) msg.obj;
                cities.addAll(list);
                adapter.notifyDataSetChanged();
                int position = 0;
                for (City city : cities) {
                    String letter = city.getEname().substring(0, 1);
                    if (!letters.containsKey(letter)) {
                        letters.put(letter, position);
                    }
                    position++;
                }
            }
        }
    };
    private MyCityAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
  //      View header = getLayoutInflater().inflate(R.layout.head_layout, null);
        TextView tv= (TextView) findViewById(R.id.tv_cityNow);
        tv.setText(city);
 //       listView.addHeaderView(header);
        LocationModel model = new LocationModel();
        presenter = new LocationPresenter(model, this);
    }

    @Override
    public void initData() {
       quickSideBarView.setOnQuickSideBarTouchListener(this);
        presenter.getList();
        adapter = new MyCityAdapter(cities, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city = cities.get(position);
                Log.i("TAG", "onItemClick: "+position);
                if (city != null) {
                    Intent intent = new Intent();
                    intent.setAction("city");
                    intent.putExtra("city", city);
                    LocationActivity.this.sendBroadcast(intent);
                    LocationActivity.this.finish();
                }
            }
        });
    }


    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        //有此key则获取位置并滚动到该位置
        if (letters.containsKey(letter)) {
           listView.setSelection(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
       quickSideBarTipsView.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void getCityList(List<City> list) {
        Message msg = Message.obtain(handler, 1);
        msg.obj = list;
        msg.sendToTarget();
    }

}

