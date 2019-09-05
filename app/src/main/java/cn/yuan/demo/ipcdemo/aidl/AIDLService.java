package cn.yuan.demo.ipcdemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;
import yuan.core.tool.ToastUtil;

/**
 * 描述： Binder 服务进程
 *
 * @author yuanye
 * @date 2019/9/5 14:20
 */
public class AIDLService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IAIDLInterface.Stub() {
            @Override
            public void send(String str) throws RemoteException {
                ToastUtil.showShort(AIDLService.this, "服务进程" +
                        android.os.Process.myPid()
                        + "接收数据为：" + str);
            }

            @Override
            public void addCallback(final NotificationListener listener) throws RemoteException {
                try {
                    Thread.sleep(2000);
                    listener.onNotice();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
