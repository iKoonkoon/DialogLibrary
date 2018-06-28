package com.ikoon.dialoglibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ikoon.dialoglibrary.helper.Alerter;
import com.ikoon.dialoglibrary.helper.Dialogue;
import com.ikoon.dialoglibrary.listener.OnDismissListener;
import com.ikoon.dialoglibrary.view.GeneralDialog;


/**
 * @author MrKong
 */
public class MainActivity extends AppCompatActivity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Button btTest = findViewById(R.id.bt_test);
        Button btTest1 = findViewById(R.id.bt_test1);
        btTest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Dialogue.create(MainActivity.this, GeneralDialog.ICON_TYPE_LOADING)
                        .setBackground(R.color.alerter_default_success_background)
                        .setIcon(R.drawable.qmui_icon_notify_error)
                        .setText("fffffffffffffffffff")
                        .setTextColor(getResources().getColor(R.color.colorAccent))
                        .setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismissListener()
                            {
                                Log.e("test", "chengle");
                            }
                        })
                        .show();


            }
        });
        btTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dialogue.create(MainActivity.this, GeneralDialog.ICON_TYPE_SUCCESS)
                        .setText("1111")
                        .setTextColor(getResources().getColor(R.color.colorAccent))
                        .show();
            }
        });
    
        
    }
}
