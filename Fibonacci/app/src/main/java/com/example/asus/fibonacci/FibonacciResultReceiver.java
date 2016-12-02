package com.example.asus.fibonacci;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Asus on 02/12/2016.
 */

public class FibonacciResultReceiver extends ResultReceiver {
    Handler myHandler;

    public FibonacciResultReceiver(Handler handler){
        super(handler);
        myHandler = handler;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        //Send message to handler at MainActivity
        long output = resultData.getLong(MainActivity.RESULT_DATA);

        myHandler.obtainMessage(resultCode, output).sendToTarget();
    }
}
