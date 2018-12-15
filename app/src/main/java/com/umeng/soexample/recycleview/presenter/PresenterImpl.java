package com.umeng.soexample.recycleview.presenter;

import com.umeng.soexample.recycleview.model.ModelImpl;
import com.umeng.soexample.recycleview.view.IView;
import com.umeng.soexample.recycleview.view.callback.MyCallBack;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/15
 */
public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IView iView;
    public PresenterImpl(IView iView){
        this.iView = iView;
        model = new ModelImpl();
    }
    @Override
    public void start(String url, int index) {
        model.getData(url, index, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.seccess(success);
            }

            @Override
            public void setRrror(Object error) {
                iView.error(error);
            }
        });
    }
}
