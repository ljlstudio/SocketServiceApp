package com.rayvison.socketserviceapp.server;

import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.rayvison.socketserviceapp.ComplexData;
import com.rayvison.socketserviceapp.ICallBackInterface;
import com.rayvison.socketserviceapp.IServerInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocalBinder  extends IServerInterface.Stub {


    private  ConnectServer server;

    public  LocalBinder(ConnectServer connectService){
        this.server=connectService;
    }
    private RemoteCallbackList<ICallBackInterface> mRemoteCallbackList = new RemoteCallbackList();
    private Lock mLock = new ReentrantLock();


    @Override
    public void simpleData(String msg) throws RemoteException {
        mLock.lock();
        int i = 0;
        try {
            i = mRemoteCallbackList.beginBroadcast();
            for (int j = 0; j < i; j++) {
                mRemoteCallbackList.getBroadcastItem(j).onSimpleData(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mRemoteCallbackList.finishBroadcast();
            mLock.unlock();
        }
    }

    @Override
    public void complexData(ComplexData complexData) throws RemoteException {
        mLock.lock();
        try {
            int i = mRemoteCallbackList.beginBroadcast();
            for (int j = 0; j < i; j++) {
                //服务器收到消息，发送一条回复
                mRemoteCallbackList.getBroadcastItem(j).onSimpleData("收到消息");
                mRemoteCallbackList.getBroadcastItem(j).onComplexData(complexData);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            mRemoteCallbackList.finishBroadcast();
            mLock.unlock();
        }
    }

    @Override
    public void destroyService() throws RemoteException {

    }

    @Override
    public void unregisterListener(ICallBackInterface listener) throws RemoteException {
        mRemoteCallbackList.unregister(listener);
    }

    @Override
    public void registerListener(ICallBackInterface listener) throws RemoteException {
        mRemoteCallbackList.register(listener);
    }
}
