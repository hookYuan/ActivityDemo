package cn.yuan.demo.viewdemo.bezier;

import cn.yuan.demo.R;
import yuan.core.mvp.BaseActivity;
import yuan.core.title.ActionBarUtil;


public class BezierActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_bezier;
    }

    @Override
    public void initData() {
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText(getIntent().getStringExtra("title"))
                .setLeftClickFinish();
    }

    @Override
    public void setListener() {

    }
}
