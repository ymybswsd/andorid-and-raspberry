package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9;
    EditText editText1,editText2,editText3;
    SeekBar seekBar1,seekBar2;

    Socket socket = null;

    OutputStream outputStream = null;
    BufferedReader bufferedReader;
    InputStream inputStream = null;

    PrintWriter pw = null;

    int steeringX = 0;
    int steeringY = 0;

    int direction = 0;


    boolean isConnected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView3);
        textView3 = findViewById(R.id.textView4);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        editText3 = findViewById(R.id.editTextTextPersonName3);
        seekBar1 = findViewById(R.id.seekBar5);
        seekBar2 = findViewById(R.id.seekBar6);
        steeringX = seekBar1.getProgress();
        steeringY = seekBar2.getProgress();
        textView2.setText("X:"+steeringX+"");
        textView3.setText("Y:"+steeringY+"");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = editText1.getText().toString();
                String sn = editText2.getText().toString();
                if((ip== null || ip.length() == 0 )&& (sn== null || sn.length() == 0))
                    Toast.makeText(MainActivity.this, "IP地址、端口号不能为空", Toast.LENGTH_SHORT).show();
                else if(ip== null || ip.length() == 0)
                    Toast.makeText(MainActivity.this, "IP地址不能为空", Toast.LENGTH_SHORT).show();
                else if(sn== null || sn.length() == 0)
                    Toast.makeText(MainActivity.this, "端口号不能为空", Toast.LENGTH_SHORT).show();
                else{
                    new ClientThread().start();
                    textView1.setText("已连接");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new sj().start();
            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView2.setText("X:"+i+"");
                new sj().start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView3.setText("Y:"+i+"");
                new sj().start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 1;
                new sj2().start();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 2;
                new sj2().start();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 3;
                new sj2().start();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 4;
                new sj2().start();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 5;
                new sj2().start();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 6;
                new sj2().start();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = 7;
                new sj2().start();
            }
        });
    }

    public class ClientThread extends Thread {
        public void run() {
            String ip = editText1.getText().toString();
            int port = Integer.parseInt(editText2.getText().toString());
            try {
                socket = new Socket(ip, port);
                pw = new PrintWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw.println("be concected successfully");
                pw.flush();
//                String line = bf.readLine();
//                textView1.setText(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class sj extends Thread {
        public void run() {
            //判断连接状态
            if (socket != null) {
                //判断输入框是否为空
//                if (editText3 != null) {
//                    //输入框内容转码后向服务器发送
//                    pw.println(editText3.getText().toString());
//                    //清空缓冲区
//                    pw.flush();
//                }
                pw.println("d"+seekBar1.getProgress()+"d"+seekBar2.getProgress());
                pw.flush();
            }
        }
    }

    public class sj2 extends Thread {
        public void run() {
            //判断连接状态
            if (socket != null) {
                //判断输入框是否为空
//                if (editText3 != null) {
//                    //输入框内容转码后向服务器发送
//                    pw.println(editText3.getText().toString());
//                    //清空缓冲区
//                    pw.flush();
//                }
                System.out.println("sxinacheng2");
                if (direction == 1) {
                    System.out.println("gf");
                    pw.println("gf");
                    pw.flush();
                    direction = 0;
                }else if(direction == 2){
                    pw.println("gb");
                    pw.flush();
                    direction = 0;
                }else if(direction == 3){
                    pw.println("gl");
                    pw.flush();
                    direction = 0;
                }else if(direction == 4){
                    pw.println("gr");
                    pw.flush();
                    direction = 0;
                }else if(direction == 5){
                    pw.println("le");
                    pw.flush();
                    direction = 0;
                }else if(direction == 6){
                    pw.println("lj");
                    pw.flush();
                    direction = 0;
                }else if(direction == 7){
                    pw.println("lc");
                    pw.flush();
                    direction = 0;
                }
            }
        }
    }
}