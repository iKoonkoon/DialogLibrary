package com.ikoon.dialoglibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ikoon.dialoglibrary.R;
import com.ikoon.dialoglibrary.listener.OnDismissListener;
import com.ikoon.dialoglibrary.utils.DimenUtils;
import com.ikoon.dialoglibrary.view.FadeInTextView;
import com.ikoon.dialoglibrary.view.GraduallyTextView;

/**
 * @author MrKong
 * @date 2018/6/26
 * @description
 */

public class GeneralDialog
{
    /**
     * 不显示任何icon
     */
    public static final int ICON_TYPE_NOTHING = 0;
    /**
     * Loading状态
     */
    public static final int ICON_TYPE_LOADING = 1;
    /**
     * loading状态2
     */
    public static final int ICON_TYPE_LOADING2 = 5;
    /**
     * 显示成功状态
     */
    public static final int ICON_TYPE_SUCCESS = 2;
    /**
     * 显示失败状态
     */
    public static final int ICON_TYPE_FAILED = 3;
    /**
     * 显示信息状态
     */
    public static final int ICON_TYPE_INFO = 4;
    
    /**
     * 默认成功图标
     */
    public int SUCCESS_ICON = R.drawable.qmui_icon_notify_done;
    /**
     * 默认失败图标
     */
    public int FAILED_ICON = R.drawable.qmui_icon_notify_error;
    /**
     * 默认提示信息图标
     */
    public int INFO_ICON = R.drawable.qmui_icon_notify_info;
    
    /**
     * 消失监听
     */
    private OnDismissListener listener;
    private int dismissTime = 2000;
    
    private Context mContext;
    private int currentType;
    private View view;
    private LinearLayout dialogView;
    private ImageView tipImg;
    private TextView tipText;
    private ProgressBar progressBar;
    private GraduallyTextView loadingTextGradually;
    private FadeInTextView loadingTextFade;
    private LoadDialog dialog;
    
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    
    public GeneralDialog(@NonNull final Context context, int type)
    {
        this(context, " ", type);
    }
    
    public GeneralDialog(Context context, String info, int type)
    {
        this.mContext = context;
        this.currentType = type;
        initView(info, type);
    }
    
    private void initView(String info, int type)
    {
        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_view, null);
        
        dialogView = (LinearLayout) view.findViewById(R.id.dialog_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        loadingTextGradually = (GraduallyTextView) view.findViewById(R.id.loading_text_gradually);
        loadingTextFade = (FadeInTextView) view.findViewById(R.id.loading_text_fade);
        tipImg = (ImageView) view.findViewById(R.id.tip_img);
        tipText = (TextView) view.findViewById(R.id.tip_text);
        
        setType(info, type);
        setDefaultTheme();
    }
    
    private void setType(String info, int type)
    {
        loadingTextGradually.setText(info);
        loadingTextFade.setTextString(info + ".", "...");
        tipText.setText(info);
        
        switch (type)
        {
            case ICON_TYPE_SUCCESS:
                tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, SUCCESS_ICON));
                tipImg.setVisibility(View.VISIBLE);
                tipText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case ICON_TYPE_FAILED:
                tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, FAILED_ICON));
                tipImg.setVisibility(View.VISIBLE);
                tipText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case ICON_TYPE_INFO:
                tipImg.setImageDrawable(ContextCompat.getDrawable(mContext, INFO_ICON));
                tipImg.setVisibility(View.VISIBLE);
                tipText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case ICON_TYPE_LOADING:
                tipImg.setVisibility(View.GONE);
                tipText.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadingTextGradually.setVisibility(View.VISIBLE);
                loadingTextFade.setVisibility(View.GONE);
                break;
            
            case ICON_TYPE_LOADING2:
                tipImg.setVisibility(View.GONE);
                tipText.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadingTextGradually.setVisibility(View.GONE);
                loadingTextFade.setVisibility(View.VISIBLE);
                break;
            
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
        switch (currentType)
        {
            case ICON_TYPE_SUCCESS:
                SUCCESS_ICON = resId;
                break;
            
            case ICON_TYPE_FAILED:
                FAILED_ICON = resId;
                break;
            
            case ICON_TYPE_INFO:
                INFO_ICON = resId;
                break;
            
            default:
                break;
        }
    }
    
    /**
     * 设置Text文本
     *
     * @param text
     */
    public void setText(String text)
    {
        setType(text, currentType);
        
        if (currentType != ICON_TYPE_LOADING)
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
        if (currentType != ICON_TYPE_LOADING)
        {
            tipText.setTextColor(colorId);
        } else
        {
            loadingTextGradually.setTextColor(colorId);
        }
    }
    
    /**
     * 设置Text字体大小
     *
     * @param dimenSp
     * @return
     */
    public void setTextSize(int dimenSp)
    {
        if (currentType != ICON_TYPE_LOADING)
        {
            tipText.setTextSize(TypedValue.COMPLEX_UNIT_SP, dimenSp);
        } else
        {
            loadingTextGradually.setTextSize(TypedValue.COMPLEX_UNIT_SP, dimenSp);
        }
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
        if (this.currentType != ICON_TYPE_LOADING && this.currentType != ICON_TYPE_LOADING2)
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
    
    /**
     * 设置默认主题
     *
     * @return
     */
    private void setDefaultTheme()
    {
        if (dialog != null)
        {
            FrameLayout parent = (FrameLayout) dialogView.getParent();
            parent.removeAllViews();
        }
        dialog = new LoadDialog(mContext, R.style.loading_dialog_no_shadow);
        initDialog();
    }
    
    private void initDialog()
    {
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(dialogView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }
    
    class LoadDialog extends Dialog
    {
        
        public LoadDialog(@NonNull Context context, @StyleRes int themeResId)
        {
            super(context, themeResId);
        }
        
        @Override
        public boolean onKeyDown(int keyCode, @NonNull KeyEvent event)
        {
            if (keyCode == KeyEvent.KEYCODE_BACK)
            {
                //拦截back键,防止loadview的内存泄漏
                dialog.dismiss();
                if (loadingTextGradually.isLoading())
                {
                    loadingTextGradually.stopLoading();
                }
                if (loadingTextFade.isLoading())
                {
                    loadingTextFade.stopFadeInAnimation();
                }
            }
            return super.onKeyDown(keyCode, event);
        }
    }
    
}
