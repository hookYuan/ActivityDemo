package cn.yuan.demo.ipcdemo.bundle;

import android.text.TextUtils;
import android.widget.TextView;

import cn.yuan.demo.R;
import yuan.core.mvp.BaseActivity;
import yuan.core.title.ActionBarUtil;
import yuan.core.tool.Kits;


/**
 * 描述：接收数据Activity,单独一个进程
 *
 * @author yuanye
 * @date 2019/9/4 14:18
 */
public class ReceiveActivity extends BaseActivity {

    private TextView textReceive;

    @Override
    public int getLayoutId() {
        return R.layout.activity_receive;
    }

    @Override
    public void initData() {
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText("数据接收端")
                .setLeftClickFinish();

        TextView process = findViewById(R.id.text_process);
        int pid = android.os.Process.myPid();
        process.setText("进程id: " + pid);

        textReceive = findViewById(R.id.text_receive);
        String str = getIntent().getStringExtra("data");

        if (TextUtils.isEmpty(str)) {
            str = getIntent().getExtras().getString("data");
        }



        textReceive.setText("接收到的数据为：" + str);
    }

    @Override
    public void setListener() {

    }
}
