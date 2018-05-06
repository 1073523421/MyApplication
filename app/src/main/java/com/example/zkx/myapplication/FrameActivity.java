package com.example.zkx.myapplication;
;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class FrameActivity extends AppCompatActivity {

    int current=0;
    Button bn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        final int[] colors = new int[]{
                R.color.color1,
                R.color.color2,
                R.color.color3,
                R.color.color4,
                R.color.color5
        };
        final int[] names = new int[]{
                R.id.imageView1,
                R.id.imageView2,
                R.id.imageView3,
                R.id.imageView4,
                R.id.imageView5
        };

        final ImageView[] views = new ImageView[names.length];

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 0x1){
                    for(int i=0;i<names.length;i++){
                        views[i].setBackgroundResource(colors[(i+current)%names.length]);
                    }
                    current++;
                }
                super.handleMessage(msg);
            }
        };

        for(int i=0;i<names.length;i++){
            views[i]=(ImageView) findViewById(names[i]);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x1);
            }
        },0,100);



        bn_back = (Button)findViewById(R.id.frame_bn_back);
        bn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrameActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }
}
