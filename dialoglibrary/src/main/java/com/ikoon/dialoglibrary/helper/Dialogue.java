package com.ikoon.dialoglibrary.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ikoon.dialoglibrary.listener.OnDismissListener;
import com.ikoon.dialoglibrary.GeneralDialog;

/**
 * @author MrKong
 * @date 2018/6/26
 * @description tip dialog 和 load dialog 启动帮助类
 */

public class Dialogue
{
    private GeneralDialog mGeneralDialog;
    private static volatile Dialogue mDialogue = null;
    private static GeneralDialog generalDialog;
    
    private Dialogue(){}
    
    public static Dialogue getInstance(){
        if(mDialogue == null){
            synchronized (Dialogue.class){
                if(mDialogue == null){
                    mDialogue = new Dialogue();
                }
            }
        }
        return mDialogue;
    }
    
    /**
     * 创建 dialog
     *
     * @param mContext 上下文
     * @return Dialogue
     */
    public static Dialogue create(@NonNull final Context mContext)
    {
        if (mContext == null)
        {
            throw new IllegalArgumentException("Context cannot be null!");
        }
    
        
        generalDialog = new GeneralDialog(mContext);
    
        getInstance().setGeneralDialog(generalDialog);
    
        return getInstance();
    }
    
    /**
     * 设置dialog 主题
     *
     * @param type
     * @return
     */
    public Dialogue setTheme(int type)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setTheme(type);
        }
        return this;
    }
    
    /**
     * 设置dialog 类型
     *
     * @param type
     * @return
     */
    public Dialogue setType(int type)
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().setType(type);
        }
        return this;
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
     * 消失dialog
     */
    public void dismiss()
    {
        if (getGeneralDialog() != null)
        {
            getGeneralDialog().dismiss();
        }
    }
    
    /**
     * set
     *
     * @param generalDialog
     */
    private void setGeneralDialog(final GeneralDialog generalDialog)
    {
        this.mGeneralDialog = generalDialog;
    }
    
    /**
     * get
     *
     * @return
     */
    GeneralDialog getGeneralDialog()
    {
        return mGeneralDialog;
    }
    
    
}
