package cn.yuan.demo.ipcdemo.bundle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import cn.yuan.demo.R;
import cn.yuan.demo.ipcdemo.aidl.AIDLService;
import cn.yuan.demo.ipcdemo.aidl.IAIDLInterface;
import cn.yuan.demo.ipcdemo.aidl.NotificationListener;
import cn.yuan.demo.ipcdemo.messenger.MessengerService;
import yuan.core.mvp.BaseActivity;
import yuan.core.title.ActionBarUtil;
import yuan.core.tool.ToastUtil;

/**
 * 描述：发送数据的Activity
 *
 * @author yuanye
 * @date 2019/9/4 14:19
 */
public class SendActivity extends BaseActivity {

    protected String type = "123456";

    //消息发送
    private Messenger messenger;

    //服务进程连接
    private ServiceConnection serviceConnection;

    //aidl 实现进程通信
    private IAIDLInterface aidl;

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
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        messenger = new Messenger(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                //绑定服务
                Intent intent = new Intent(this, MessengerService.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case "AIDL通讯":
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        aidl = IAIDLInterface.Stub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                //绑定服务
                Intent intent2 = new Intent(this, AIDLService.class);
                bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(mContext, ReceiveActivity.class);
        switch (view.getId()) {
            case R.id.btn_normal:
                switch (type) {
                    case "Bundle通讯":
                        intent.putExtra("data", "962851730");
                        startActivity(intent);
                        break;
                    case "Messenger通讯":
                        //创建完成Messenger后就可以通过Messenger 来发送 Message 了
                        Message message = Message.obtain();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", "962851730");
                        message.setData(bundle);
                        try {    //通过信使发送信息
                            messenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Binder通讯":


                        break;
                    case "AIDL通讯":
                        try {
                            aidl.send("962851730");
                            aidl.addCallback(new NotificationListener() {
                                @Override
                                public void onNotice() {
                                }

                                @Override
                                public IBinder asBinder() {
                                    return new Stub() {
                                        @Override
                                        public void onNotice() throws RemoteException {
                                            ToastUtil.showShort(mContext, "服务进程" +
                                                    android.os.Process.myPid()
                                                    + "回调函数执行");
                                        }
                                    };
                                }
                            });
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
            case R.id.btn_other:
                Bundle bundle = new Bundle();
                bundle.putString("data", "962851730");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }
}
