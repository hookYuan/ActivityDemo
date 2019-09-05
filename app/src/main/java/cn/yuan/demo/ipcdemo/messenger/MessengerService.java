package cn.yuan.demo.ipcdemo.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.Nullable;
import yuan.core.tool.ToastUtil;

/**
 * 描述：
 *
 * @author yuanye
 * @date 2019/9/4 15:22
 */
public class MessengerService extends Service {

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String str = msg.getData().getString("data");
            ToastUtil.showShort(MessengerService.this, "服务进程" +
                    android.os.Process.myPid()
                    + "接收数据：" + str);
        }
    }

    //此Messenger将客户端发送的消息传递给 MessengerHandler
    private Messenger messenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
