package com.example.asus.fibonacci;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * Created by Asus on 02/12/2016.
 */

public class FibonacciService extends IntentService {
    private ResultReceiver resultReceiver;
    //create service
    public FibonacciService() {
        super("service");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        int number = (int) workIntent.getExtras().get(MainActivity.INPUT_NUMBER);

        resultReceiver = (ResultReceiver) workIntent.getExtras().get(MainActivity.MY_HANDLER);

        Long output = fibonacci(number);

        //Send result to MainActivity
        Bundle bundle = new Bundle();
        bundle.putLong(MainActivity.RESULT_DATA, output);
        resultReceiver.send(MainActivity.SUCCESS_RESULT, bundle);
    }

    private long fibonacci(int n) {
        Log.d("SERVICE", "Calculando: "+n);
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }

}
