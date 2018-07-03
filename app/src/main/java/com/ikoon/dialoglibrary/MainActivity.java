package com.ikoon.dialoglibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ikoon.dialoglibrary.helper.Dialogue;
import com.ikoon.dialoglibrary.listener.OnDismissListener;


/**
 * @author MrKong
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    
    private Button bt_dialog_default;
    private Button bt_dialog_type;
    private Button bt_dialog_text;
    private Button bt_dialog_bg;
    private Button bt_dialog_icon;
    private Button bt_dialog_listener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initEvent();
    }
    
    private void initView()
    {
        bt_dialog_default = (Button) findViewById(R.id.bt_dialog_default);
        bt_dialog_type = (Button) findViewById(R.id.bt_dialog_type);
        bt_dialog_text = (Button) findViewById(R.id.bt_dialog_text);
        bt_dialog_bg = (Button) findViewById(R.id.bt_dialog_bg);
        bt_dialog_icon = (Button) findViewById(R.id.bt_dialog_icon);
        bt_dialog_listener = (Button) findViewById(R.id.bt_dialog_listener);
    }
    
    private void initEvent()
    {
        bt_dialog_default.setOnClickListener(this);
        bt_dialog_type.setOnClickListener(this);
        bt_dialog_text.setOnClickListener(this);
        bt_dialog_bg.setOnClickListener(this);
        bt_dialog_icon.setOnClickListener(this);
        bt_dialog_listener.setOnClickListener(this);
        
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bt_dialog_default:
                Dialogue.create(MainActivity.this).setText("默认是loading").show();
                break;
            
            case R.id.bt_dialog_type:
                Dialogue.create(MainActivity.this)
                        .setTheme(R.style.loading_dialog_with_shadow)
                        .setType(GeneralDialog.TYPE_SUCCESS)
                        .setText("成功啦")
                        .show();
                break;
            
            case R.id.bt_dialog_text:
                Dialogue.create(MainActivity.this)
                        .setType(GeneralDialog.TYPE_SUCCESS)
                        .setText("成功啦")
                        .setTextColor(R.color.colorAccent)
                        .setTextSize(20)
                        .show();
                break;
            
            case R.id.bt_dialog_bg:
                Dialogue.create(MainActivity.this)
                        .setTheme(R.style.loading_dialog_with_shadow)
                        .setType(GeneralDialog.TYPE_SUCCESS)
                        .setBackground(R.drawable.alerter_ic_notifications)
                        .show();
                break;
            
            case R.id.bt_dialog_icon:
                Dialogue.create(MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .show();
                break;
            
            case R.id.bt_dialog_listener:
                Dialogue.create(MainActivity.this)
                        .setType(GeneralDialog.TYPE_SUCCESS)
                        .setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismissListener()
                            {
                                Toast.makeText(MainActivity.this, "我消失了", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            
            default:
                break;
        }
        
    }
}
