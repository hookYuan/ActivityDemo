package cn.yuan.demo.ipcdemo.binder;

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
public class BinderService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        return new IMessageInterface.Stub() {
//            @Override
//            public void basicTypes(String str) throws RemoteException {
//                ToastUtil.showShort(BinderService.this, "接收数据为：" + str);
//            }
//        };
        return null;
    }
}
