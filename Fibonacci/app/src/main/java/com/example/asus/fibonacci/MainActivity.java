package com.example.asus.fibonacci;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;
    private FibonacciResultReceiver fibonacciResultReceiver;
    private ProgressDialog dialog;

    public static final String INPUT_NUMBER = "input";
    public static final String MY_HANDLER = "my_handler";
    public static final String RESULT_DATA = "result_data";
    public static final int SUCCESS_RESULT = 1;
    public static final int FAILURE_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializa as views utilizadas
        setViews();
    }

    private void setViews() {
        textView = (TextView) findViewById(R.id.textViewOutput);
        editText = (EditText) findViewById(R.id.editTextInput);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               dialog = ProgressDialog.show(MainActivity.this, "",
                        "Calculando...", true);
                startService();
            }
        });
    }

    private void startService(){
        String text = editText.getText().toString();
        if(text.length() > 0) {
            int number = Integer.valueOf(editText.getText().toString());
            if(number <= 1000 && number > 0) {
                fibonacciResultReceiver = new FibonacciResultReceiver(myhandler);
                Intent intent = new Intent(MainActivity.this, FibonacciService.class);
                intent.putExtra(INPUT_NUMBER, number);
                intent.putExtra(MY_HANDLER, fibonacciResultReceiver);
                startService(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "O número deve estar entre 1 e 1000",
                        Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Voce precisa digitar um número",
                    Toast.LENGTH_LONG).show();
        }
    }

    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            int result = message.what;
            dialog.dismiss();
            if(result == SUCCESS_RESULT){
                long output = (long) message.obj;
                textView.setText("Resultado: "+ output);
            }else if(result == FAILURE_RESULT){
                textView.setText("Error");
            }

        }
    };

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
