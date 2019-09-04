package cn.yuan.demo;

import android.view.View;

import cn.yuan.demo.activitydemo.ActActivity;
import cn.yuan.demo.ipcdemo.IPCActivity;
import yuan.core.list.BaseViewHolder;
import yuan.core.list.GridDivider;
import yuan.core.list.RecyclerAdapter;
import yuan.core.tool.RouteUtil;
import yuan.core.ui.Adapter;
import yuan.core.ui.RecyclerActivity;


/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/9/4 13:47
 */
@Adapter(adapter = StringAdapter.class)
public class MainActivity extends RecyclerActivity {

    @Override
    public void initData() {
        mRecyclerView.addItemDecoration(new GridDivider());

        mData.add("Activity 示例");
        mData.add("进程通讯IPC 示例");
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClick(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, View view, int position) {
                switch (position) {
                    case 0:
                        RouteUtil.open(mContext, ActActivity.class);
                        break;
                    case 1:
                        RouteUtil.open(mContext, IPCActivity.class);
                        break;
                }
            }
        });
    }

}
