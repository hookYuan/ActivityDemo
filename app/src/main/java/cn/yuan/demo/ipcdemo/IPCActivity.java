package cn.yuan.demo.ipcdemo;

import android.view.View;

import cn.yuan.demo.R;
import cn.yuan.demo.StringAdapter;
import cn.yuan.demo.activitydemo.ActActivity;
import cn.yuan.demo.ipcdemo.bundle.SendActivity;
import yuan.core.list.BaseViewHolder;
import yuan.core.list.GridDivider;
import yuan.core.list.RecyclerAdapter;
import yuan.core.title.ActionBarUtil;
import yuan.core.tool.RouteUtil;
import yuan.core.ui.Adapter;
import yuan.core.ui.RecyclerActivity;

/**
 * 描述：进程通信 示例
 * <p>
 * 1.Bundle(四大组件的通讯方式)
 * 2.AIDL 基于Binder
 * 3.Messenger 基于Binder
 * 4.ContentProvider 基于Binder
 * 5.Socket 网络通信
 *
 * @author yuanye
 * @date 2019/9/4 13:55
 */
@Adapter(adapter = StringAdapter.class)
public class IPCActivity extends RecyclerActivity {

    @Override
    public void initData() {

        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("IPC 通信")
                .setLeftClickFinish();

        mRecyclerView.addItemDecoration(new GridDivider());

        mData.add("Bundle通讯");
        mData.add("Messenger通讯");
        mData.add("Binder通讯");
        mData.add("文件共享通讯");
        mData.add("AIDL通讯");
        mData.add("ContentProvider通讯");
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClick(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, View view, int position) {
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
                RouteUtil.open(mContext, SendActivity.class, RouteUtil.buildParam()
                        .put("type", String.class.cast(mData.get(position))));
            }
        });
    }
}
