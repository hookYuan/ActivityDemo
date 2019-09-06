package cn.yuan.demo.viewdemo;

import android.view.View;

import cn.yuan.demo.R;
import cn.yuan.demo.StringAdapter;
import cn.yuan.demo.viewdemo.bezier.BezierActivity;
import yuan.core.list.BaseViewHolder;
import yuan.core.list.GridDivider;
import yuan.core.list.RecyclerAdapter;
import yuan.core.title.ActionBarUtil;
import yuan.core.tool.RouteUtil;
import yuan.core.ui.Adapter;
import yuan.core.ui.RecyclerActivity;

@Adapter(adapter = StringAdapter.class)
public class ViewActivity extends RecyclerActivity {

    @Override
    public void initData() {
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("自定义View")
                .setLeftClickFinish();

        mRecyclerView.addItemDecoration(new GridDivider());
        mData.add("自定义View--贝塞尔曲线");
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClick(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, View view, int position) {
                switch (position) {
                    case 0:
                        RouteUtil.open(mContext, BezierActivity.class,
                                RouteUtil.buildParam().put("title", String.class
                                        .cast(mData.get(position))));
                        break;
                }
            }
        });
    }
}
