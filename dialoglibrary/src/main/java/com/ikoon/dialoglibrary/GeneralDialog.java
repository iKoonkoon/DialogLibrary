package com.ikoon.dialoglibrary;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ikoon.dialoglibrary.listener.OnDismissListener;
import com.ikoon.dialoglibrary.utils.DimenUtils;
import com.ikoon.dialoglibrary.view.FadeInTextView;
import com.ikoon.dialoglibrary.view.GraduallyTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author MrKong
 * @date 2018/6/26
 * @description 自定义 tip dialog 和 load dialog 类
 */

public class GeneralDialog
{
    /**
     * text dialog 类型状态码
     */
    public static final int TYPE_NOTHING = 0;
    /**
     * loading dialog 类型状态码
     */
    public static final int TYPE_LOADING = 1;
    /**
     * loading dialog 类型状态码
     * <p>
     * 此类型暂时有问题 按照 loading 处理
     */
    public static final int TYPE_LOADING2 = 1;
    /**
     * success dialog 类型状态码
     */
    public static final int TYPE_SUCCESS = 2;
    /**
     * failed dialog 类型状态码
     */
    public static final int TYPE_FAILED = 3;
    /**
     * info dialog 类型状态码
     */
    public static final int TYPE_INFO = 4;
    
    /**
     * 默认success dialog图标
     */
    public int SUCCESS_ICON = R.drawable.qmui_icon_notify_done;
    /**
     * 默认failed dialog图标
     */
    public int FAILED_ICON = R.drawable.qmui_icon_notify_error;
    /**
     * 默认info dialog图标
     */
    public int INFO_ICON = R.drawable.qmui_icon_notify_info;
    
    /**
     * 默认 dialog 消失时间
     */
    private int dismissTime = 2000;
    /**
     * 默认 dialog 主题
     */
    private static int DEFAULT_THEME = R.style.loading_dialog_no_shadow;
    
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private OnDismissListener listener;
    
    private Context mContext;
    private int mType;
    
    private View view;
    private LinearLayout dialogView;
    private ImageView tipImg;
    private TextView tipText;
    private ProgressBar progressBar;
    private GraduallyTextView loadingTextGradually;
    private FadeInTextView loadingTextFade;
    private LoadDialogView dialog;
    
    
    public GeneralDialog(@NonNull final Context context)
    {
        this.mContext = context;
        initView(TYPE_LOADING);
        initEvent();
    }
    
    /**
     * 初始化view
     *
     * @param type
     */
    private void initView(int type)
    {
        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_view, null);
        
        dialogView = (LinearLayout) view.findViewById(R.id.dialog_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        loadingTextGradually = (GraduallyTextView) view.findViewById(R.id.loading_text_gradually);
        loadingTextFade = (FadeInTextView) view.findViewById(R.id.loading_text_fade);
        tipImg = (ImageView) view.findViewById(R.id.tip_img);
        tipText = (TextView) view.findViewById(R.id.tip_text);
        
        setTheme(DEFAULT_THEME);
        setType(type);
    }
    
    /**
     * 初始化事件监听
     */
    protected void initEvent()
    {
        Observable<String> register = RxBus.getInstance().register("test");
        register.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>()
        {
            @Override
            public void accept(String str) throws Exception
            {
                dismiss();
            }
        });
    }
    
    /**
     * 设置dialog 主题
     *
     * @return
     */
    public void setTheme(int themeId)
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) dialogView.getParent();
            parent.removeAllViews();
        }
        dialog = new LoadDialogView(mContext, themeId);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(dialogView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }
    
    /**
     * 设置dialog 类型
     *
     * @param type
     */
    public void setType(int type)
    {
        this.mType = type;
        
        switch (type)
        {
            case TYPE_SUCCESS:
                tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, SUCCESS_ICON));
                tipImg.setVisibility(View.VISIBLE);
                tipText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case TYPE_FAILED:
                tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, FAILED_ICON));
                tipImg.setVisibility(View.VISIBLE);
                tipText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case TYPE_INFO:
                tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, INFO_ICON));
                tipImg.setVisibility(View.VISIBLE);
                tipText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case TYPE_LOADING:
                tipImg.setVisibility(View.GONE);
                tipText.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadingTextGradually.setVisibility(View.VISIBLE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
//            case TYPE_LOADING2:
//                tipImg.setVisibility(View.GONE);
//                tipText.setVisibility(View.GONE);
//                progressBar.setVisibility(View.VISIBLE);
//                loadingTextGradually.setVisibility(View.GONE);
//                loadingTextFade.setVisibility(View.VISIBLE);
//                break;
            
            default:
                
                break;
        }
        
    }
    
    /**
     * 设置Background
     * <p>
     * 圆角和背景颜色
     *
     * @param drawableId
     * @return
     */
    public void setBackground(int drawableId)
    {
        dialogView.setBackgroundResource(drawableId);
    }
    
    /**
     * 设置progressbar color
     * <p>
     * 只是在api 21以上才有用
     *
     * @param colorId
     * @return
     */
    public void setProgressbarColor(int colorId)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
            progressBar.setIndeterminateTintList(ColorStateList.valueOf(colorId));
        }
    }
    
    /**
     * 设置Icon图标
     * <p>
     * 如果要更改icon需要在setText方法之前调用此方法
     *
     * @param resId
     * @return
     */
    public void setIcon(int resId)
    {
        switch (mType)
        {
            case TYPE_SUCCESS:
                SUCCESS_ICON = resId;
                break;
            
            case TYPE_FAILED:
                FAILED_ICON = resId;
                break;
            
            case TYPE_INFO:
                INFO_ICON = resId;
                break;
            
            default:
                break;
        }
        tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, resId));
    }
    
    /**
     * 设置Text文本
     *
     * @param text
     */
    public void setText(String text)
    {
        tipText.setText(text);
        loadingTextGradually.setText(text);
        loadingTextFade.setTextString(text + ".", "...");
        
        if (mType != TYPE_LOADING && mType != TYPE_LOADING2)
        {
            tipImg.post(new Runnable()
            {
                @Override
                public void run()
                {
                    /**
                     * 88dp = width - paddingLeft -paddingRight ;
                     * 1.icon的宽度<88dp,则设置msg的最大宽度为88dp
                     * 2.icon的宽度>88dp,则设置msg的最大宽度为icon宽度
                     *
                     * 88太小  设置为200
                     */
                    int maxWidth = DimenUtils.dpToPxInt(mContext, 200);
                    int width = tipImg.getWidth();
                    if (maxWidth > width)
                    {
                        tipText.setMaxWidth(maxWidth);
                    } else
                    {
                        tipText.setMaxWidth(width);
                    }
                }
            });
        }
    }
    
    /**
     * 设置Text字体颜色
     *
     * @param colorId
     * @return
     */
    public void setTextColor(int colorId)
    {
        tipText.setTextColor(colorId);
        loadingTextGradually.setTextColor(colorId);
        loadingTextFade.setTextColor(colorId);
    }
    
    /**
     * 设置Text字体大小
     *
     * @param dimenSp
     * @return
     */
    public void setTextSize(int dimenSp)
    {
        tipText.setTextSize(TypedValue.COMPLEX_UNIT_SP, dimenSp);
        loadingTextGradually.setTextSize(TypedValue.COMPLEX_UNIT_SP, dimenSp);
        loadingTextFade.setTextSize(TypedValue.COMPLEX_UNIT_SP, dimenSp);
    }
    
    /**
     * 设置tip dialog展示时间
     *
     * @param duration 毫秒
     * @return
     */
    public void setShowTime(int duration)
    {
        this.dismissTime = duration;
    }
    
    /**
     * 设置load dialog加载一次文字的动画时间
     *
     * @param duration
     * @return
     */
    public void setLoadingTime(int duration)
    {
        loadingTextGradually.setDuration(duration);
    }
    
    /**
     * 配置是否能返回键取消加载框
     *
     * @param isCancel
     */
    public void setCancelable(boolean isCancel)
    {
        dialog.setCancelable(isCancel);
    }
    
    /**
     * 配置是否能点击框外取消加载框
     *
     * @param isCancel
     */
    public void setCanceledOnTouchOutside(boolean isCancel)
    {
        dialog.setCanceledOnTouchOutside(isCancel);
    }
    
    /**
     * 配置dialog取消时间监听
     *
     * @param dismissListener
     * @return
     */
    public void setOnDismissListener(OnDismissListener dismissListener)
    {
        this.listener = dismissListener;
    }
    
    /**
     * 显示dialog
     */
    public void show()
    {
        dialog.show();
        if (loadingTextGradually.getVisibility() == View.VISIBLE)
        {
            loadingTextGradually.startLoading();
        }
        if (loadingTextFade.getVisibility() == View.VISIBLE)
        {
            loadingTextFade.startFadeInAnimation();
        }
        // 移除所有的message和callback,
        // 防止返回键dismiss后,callback没移除
        sHandler.removeCallbacksAndMessages(null);
        if (this.mType != TYPE_LOADING && this.mType != TYPE_LOADING2)
        {
            if (loadingTextFade.isLoading())
            {
                loadingTextFade.stopFadeInAnimation();
            }
            if (loadingTextGradually.isLoading())
            {
                loadingTextGradually.stopLoading();
            }
            sHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    dismiss();
                }
            }, dismissTime);
        }
    }
    
    /**
     * 消失dialog
     */
    public void dismiss()
    {
        dialog.dismiss();
        if (loadingTextGradually.isLoading())
        {
            loadingTextGradually.stopLoading();
        }
        if (loadingTextFade.isLoading())
        {
            loadingTextFade.stopFadeInAnimation();
        }
        if (listener != null)
        {
            listener.onDismissListener();
        }
    }
    
}
