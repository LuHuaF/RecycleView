package com.umeng.soexample.recycleview.model;

import com.umeng.soexample.recycleview.view.callback.MyCallBack;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/15
 */
public interface Model {
    void getData(String url, int index, MyCallBack callBack);
}
