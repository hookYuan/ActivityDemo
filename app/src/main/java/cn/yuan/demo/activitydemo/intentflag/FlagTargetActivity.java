package cn.yuan.demo.activitydemo.intentflag;

import cn.yuan.demo.R;
import cn.yuan.demo.activitydemo.lifecycle.LifeActivity;
import yuan.core.title.ActionBarUtil;

import android.os.Bundle;
import android.widget.TextView;

/**
 * 描述：Intent  Flag 启动的对应 Activity
 *
 * @author yuanye
 * @date 2019/9/3 15:58
 */
public class FlagTargetActivity extends LifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_taget);
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("目标页")
                .setLeftClickFinish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView lifeOrder = findViewById(R.id.text_life_order);
        lifeOrder.setText("生命周期执行顺序：" + mLifeMethod);

        TextView taskId = findViewById(R.id.text_task);
        taskId.setText("任务栈id ：" + getTaskId());
    }

}
