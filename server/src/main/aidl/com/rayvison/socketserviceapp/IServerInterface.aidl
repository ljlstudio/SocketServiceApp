// IServerInterface.aidl
package com.rayvison.socketserviceapp;
import com.rayvison.socketserviceapp.ComplexData;
// Declare any non-default types here with import statements

interface IServerInterface {

void simpleData(String msg);
void complexData(in ComplexData complexData);
void destroyService();
void unregisterListener(com.rayvison.socketserviceapp.ICallBackInterface listener);
void registerListener(com.rayvison.socketserviceapp.ICallBackInterface listener);
}