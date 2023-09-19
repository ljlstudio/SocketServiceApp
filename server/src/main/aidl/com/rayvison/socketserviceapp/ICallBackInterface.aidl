// ICallBackInterface.aidl
package com.rayvison.socketserviceapp;
import com.rayvison.socketserviceapp.ComplexData;
// Declare any non-default types here with import statements

interface ICallBackInterface {
   void onSimpleData(String msg);

    void onComplexData(in ComplexData complexData);
}