package com.ikoon.dialoglibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author yuyh.
 * @date 16/4/10.
 */
public class DimenUtils
{
    // 超高分辨率       1080×1920
    // 超高分辨率       720×1280
    // 高分辨率         480×800
    // 中分辨率         320×480
    
    public enum EScreenDensity
    {
        XXHDPI, XHDPI, HDPI, MDPI,
    }
    
    public static EScreenDensity getDisply(Context context)
    {
        EScreenDensity eScreenDensity;
        //初始化屏幕密度
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi;
        
        if (densityDpi <= 160)
        {
            eScreenDensity = EScreenDensity.MDPI;
        } else if (densityDpi <= 240)
        {
            eScreenDensity = EScreenDensity.HDPI;
        } else if (densityDpi < 400)
        {
            eScreenDensity = EScreenDensity.XHDPI;
        } else
        {
            eScreenDensity = EScreenDensity.XXHDPI;
        }
        return eScreenDensity;
    }
    
    
    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    public static float dpToPx(Context mContext, float dp)
    {
        return dp * mContext.getResources().getDisplayMetrics().density;
    }
    
    public static int dpToPxInt(Context mContext, float dp)
    {
        return (int) (dpToPx(mContext, dp) + 0.5f);
    }
    
    /**
     * 将px转换成dp
     *
     * @param px
     * @return
     */
    public static float pxToDp(Context mContext, float px)
    {
        return px / mContext.getResources().getDisplayMetrics().density;
    }
    
    public static int pxToDpInt(Context mContext, float px)
    {
        return (int) (pxToDp(mContext, px) + 0.5f);
    }

}
