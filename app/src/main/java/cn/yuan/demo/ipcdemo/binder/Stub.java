package cn.yuan.demo.ipcdemo.binder;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import yuan.core.tool.ToastUtil;

/**
 * 描述：可以跨进程传输的对象
 *
 * @author yuanye
 * @date 2019/9/5 13:17
 */
public class Stub extends Binder implements IInterface {
    /**
     * Binder server 注册于 ServerManager中的名称
     * Binder client 通过 DESCRIPTOR 查找Stub 的引用
     */
    private static final java.lang.String DESCRIPTOR = "cn.yuan.IMessageInterface";

    private Context context;

    public Stub(Context context) {
        this.context = context;
    }

    /**
     * Cast an IBinder object into an cn.yuan.demo.ipcdemo.aidl.IMessageInterface interface,
     * generating a proxy if needed.
     */
    public static Stub asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof Stub))) {
            return ((Stub) iin);
        }
        return null;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        String descriptor = DESCRIPTOR;
        switch (code) {
            /**
             * IBinder协议代码： 询问接收方事务的规范接口描述符
             */
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }

            /**
             * 可用于用户命令的第一个事务代码
             */
            case FIRST_CALL_TRANSACTION: {
                data.enforceInterface(descriptor);
                //读取数据
                String dataString = data.readString();
                sendMessage(dataString);
                ToastUtil.showShort(context, "接收数据为：" + dataString);
                reply.writeNoException();
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }

    class Proxy implements IInterface {

        private IBinder iBinder;

        public Proxy(IBinder iBinder) {
            this.iBinder = iBinder;
        }

        @Override
        public IBinder asBinder() {
            return iBinder;
        }
    }

    public void sendMessage(String msg) {

    }

}
