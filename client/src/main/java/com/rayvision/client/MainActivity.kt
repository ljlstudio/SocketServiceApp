package com.rayvision.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import androidx.appcompat.app.AppCompatActivity
import com.rayvison.socketserviceapp.ComplexData
import com.rayvison.socketserviceapp.ICallBackInterface
import com.rayvison.socketserviceapp.IServerInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var leftHandValue1 = arrayOf(
        floatArrayOf(
            -0.054577f,
            -0.093000f,
            -0.164167f,
            -0.416545f,
            0.200342f,
            -0.033673f,
            -0.886126f
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //绑定服务
        bind.setOnClickListener {
            bindService()
        }
        //发送二维数组
        send.setOnClickListener {
            sendComplexDataToServer()
        }
        //接触绑定
        unbind.setOnClickListener {
            unBind()
        }
    }



    private fun bindService() {
        val intent = Intent()
        intent.setPackage("com.rayvison.socketserviceapp")

        intent.action = "com.sockets.server.Connect"

        startService(intent)
        bindService(intent, mConnection, BIND_AUTO_CREATE)
    }

    private fun unBind(){
        mBinder?.unregisterListener(listener)
        content.text = "解除绑定成功"
        unbindService(mConnection)
    }

    private fun sendComplexDataToServer() {
        val complexData = ComplexData()
        complexData.data = leftHandValue1
        mBinder?.complexData(complexData)
    }

    var mBinder: IServerInterface? = null

    private val listener = object : ICallBackInterface.Stub() {
        override fun onSimpleData(msg: String?) {
            content.text = "收到服务器发送的数据----：$msg"
        }

        override fun onComplexData(complexData: ComplexData?) {

        }


    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            mBinder = IServerInterface.Stub.asInterface(iBinder)
            try {
                //设置监听回调
                //这里已经是绑定成了
                mBinder?.registerListener(listener)
                content.text = "绑定并且注册监听成功"

            } catch (e: RemoteException) {
                e.printStackTrace()
            }

        }

        override fun onServiceDisconnected(componentName: ComponentName) {

        }
    }


    override fun onDestroy() {
        super.onDestroy()

    }
}