package com.ikoon.dialoglibrary;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;



/**
 * @author MrKong
 * @date 2018/7/2
 * @description
 */

public class LoadDialogView extends Dialog
{
    public LoadDialogView(@NonNull Context context)
    {
        super(context);
    }
    
    public LoadDialogView(@NonNull Context context, int themeResId)
    {
        super(context, themeResId);
    }
    
    protected LoadDialogView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            RxBus.getInstance().post("test", "1");
        }
        return super.onKeyDown(keyCode, event);
    }
}
