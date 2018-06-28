package com.ikoon.dialoglibrary.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ikoon.dialoglibrary.view.GeneralDialog;
import com.ikoon.dialoglibrary.listener.OnDismissListener;

/**
 * @author MrKong
 * @date 2018/6/26
 * @description
 */

public class Dialogue
{
    private GeneralDialog load;
    private static Dialogue dialogue;
    
    /**
     * Creates the Alert 并维护对调用活动的引用
     *
     * @param mContext The calling Activity
     * @return This Alerter
     */
    public static Dialogue create(@NonNull final Context mContext, int dialogType)
    {
        if (mContext == null)
        {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        
        if (dialogue == null)
        {
            dialogue = new Dialogue();
            dialogue.setGeneralDialog(new GeneralDialog(mContext, dialogType));
        }
        
        return dialogue;
    }
    
    /**
     * 设置Background
     * <p>
     * 圆角和背景颜色
     *
     * @param drawableId
     * @return
     */
    public Dialogue setBackground(int drawableId)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setBackground(drawableId);
        }
        return this;
    }
    
    
    /**
     * 设置progressbar color
     * <p>
     * 只是在api 21以上才有用
     *
     * @param colorId
     * @return
     */
    public Dialogue setProgressbarColor(int colorId)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setProgressbarColor(colorId);
        }
        return this;
    }
    
    /**
     * 设置Icon图标
     * <p>
     * 如果要更改icon需要在setText方法之前调用此方法,否则无效
     *
     * @param resId
     * @return
     */
    public Dialogue setIcon(int resId)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setIcon(resId);
        }
        return this;
    }
    
    /**
     * 设置Text文本
     *
     * @param text
     * @return
     */
    public Dialogue setText(String text)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setText(text);
        }
        return this;
    }
    
    /**
     * 设置Text字体颜色
     *
     * @param colorId
     * @return
     */
    public Dialogue setTextColor(int colorId)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setTextColor(colorId);
        }
        return this;
    }
    
    /**
     * 设置Text字体大小
     *
     * @param dimenSp
     * @return
     */
    public Dialogue setTextSize(int dimenSp)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setTextSize(dimenSp);
        }
        return this;
    }
    
    /**
     * 设置tip dialog展示时间
     *
     * @param duration 毫秒
     * @return
     */
    public Dialogue setShowTime(int duration)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setShowTime(duration);
        }
        return this;
    }
    
    /**
     * 设置load dialog加载一次文字的动画时间
     *
     * @param duration
     * @return
     */
    public Dialogue setLoadingTime(int duration)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setLoadingTime(duration);
        }
        return this;
    }
    
    /**
     * 展示dialog
     */
    public void show()
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().show();
        }
    }
    
    /**
     * 配置是否能返回键取消加载框
     *
     * @param isCancel
     */
    public Dialogue setCancelable(boolean isCancel)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setCancelable(isCancel);
        }
        return this;
    }
    
    /**
     * 配置是否能点击框外取消加载框
     *
     * @param isCancel
     */
    public Dialogue setCanceledOnTouchOutside(boolean isCancel)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setCanceledOnTouchOutside(isCancel);
        }
        return this;
    }
    
    /**
     * 配置dialog取消时间监听
     *
     * @param dismissListener
     * @return
     */
    public Dialogue setOnDismissListener(OnDismissListener dismissListener)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setOnDismissListener(dismissListener);
        }
        return this;
    }
    
    
    /**
     * set load
     *
     * @param load
     */
    private void setGeneralDialog(final GeneralDialog load)
    {
        this.load = load;
    }
    
    /**
     * get load
     *
     * @return
     */
    GeneralDialog getGeneralDialog()
    {
        return load;
    }
    
    
}
