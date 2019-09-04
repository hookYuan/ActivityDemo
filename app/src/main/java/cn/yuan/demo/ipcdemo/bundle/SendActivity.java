package cn.yuan.demo.ipcdemo.bundle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.TextView;

import cn.yuan.demo.R;
import yuan.core.mvp.BaseActivity;
import yuan.core.title.ActionBarUtil;

/**
 * 描述：发送数据的Activity
 *
 * @author yuanye
 * @date 2019/9/4 14:19
 */
public class SendActivity extends BaseActivity {

    private String type = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_send;
    }

    @Override
    public void initData() {
        ActionBarUtil.create(this)
                .setTextColor(getColor(R.color.white))
                .setLeftIcon(R.drawable.ic_base_back_white)
                .setTitleText(getIntent().getStringExtra("type") + "发送端")
                .setLeftClickFinish();

        type = getIntent().getStringExtra("type");
        TextView process = findViewById(R.id.text_process);
        int pid = android.os.Process.myPid();
        process.setText("进程id: " + pid);
    }

    @Override
    public void setListener() {
        switch (type) {
            case "Bundle通讯":
                findViewById(R.id.btn_other).setVisibility(View.VISIBLE);
                break;
            case "Messenger通讯":
                Messenger messenger = new Messenger(new Handler() {
                    @Override
                    public void handleMessage(Message msg) {

                    }
                });
                break;

        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(mContext, ReceiveActivity.class);
        switch (view.getId()) {
            case R.id.btn_normal:
                if ("Bundle通讯".equals(type)) {
                    intent.putExtra("data", "962851730");
                }
                break;
            case R.id.btn_other:
                Bundle bundle = new Bundle();
                bundle.putString("data", "962851730");
                intent.putExtras(bundle);
                break;
        }
        startActivity(intent);
    }
}
