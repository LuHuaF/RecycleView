package com.umeng.soexample.recycleview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.recycleview.adapter.MyAdapter;
import com.umeng.soexample.recycleview.bean.User;
import com.umeng.soexample.recycleview.presenter.PresenterImpl;
import com.umeng.soexample.recycleview.view.IView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView, XRecyclerView.LoadingListener {
    private XRecyclerView xrv_news;
    private String mUrls = "http://www.xieast.com/api/news/news.php?page=1";
    private int mIndex = 1;
    private List<User.DataBean> mListAll;
    private MyAdapter mAdapter;
    private PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();

    }

    private void initData() {
        mListAll = new ArrayList<>();
        mAdapter = new MyAdapter(this,mListAll);
        presenter = new PresenterImpl(this);
        presenter.start(mUrls,mIndex);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayout.VERTICAL);
        xrv_news.setLayoutManager(manager);
        xrv_news.setAdapter(mAdapter);
        xrv_news.setLoadingListener(this);
        xrv_news.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        xrv_news.getDefaultRefreshHeaderView()
                .setRefreshTimeVisible(true);
    }

    private void initViews() {
        xrv_news = findViewById(R.id.xrv_news);
    }


    @Override
    public void seccess(Object success) {
        User user = (User) success;
        List<User.DataBean> list = user.getData();
        mListAll.addAll(list);
        mAdapter.notifyDataSetChanged();;
    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onRefresh() {
        mIndex = 1;
        mListAll.clear();
        presenter.start(mUrls,1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xrv_news.refreshComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        mIndex++;

        presenter.start(mUrls,1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xrv_news.refreshComplete();
            }
        },2000);
    }
}
