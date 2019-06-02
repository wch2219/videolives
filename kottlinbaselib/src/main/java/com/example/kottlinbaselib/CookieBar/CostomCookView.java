package com.example.kottlinbaselib.CookieBar;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.example.kottlinbaselib.R;


public class CostomCookView extends LinearLayout implements View.OnTouchListener {
    private long slideOutAnimationDuration = 300;
    private Animation slideOutAnimation;

    private ViewGroup layoutCostomCookBar;
    private TextView tvTitle;
    private TextView tvMessage;
    private ImageView ivIcon;
    private TextView btnAction;
    private long duration = 2000;
    private long animationDuration = 300;
    private int layoutGravity = Gravity.BOTTOM;
    private float initialDragX;
    private float dismissOffsetThreshold;
    private float viewWidth;
    private boolean swipedOut;
    private int[] animationIn;
    private int[] animationOut;


    public CostomCookView(@NonNull final Context context) {
        this(context, null);
    }

    public CostomCookView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CostomCookView(@NonNull final Context context, @Nullable final AttributeSet attrs,
                          final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getLayoutGravity() {
        return layoutGravity;
    }

    private void initViews(@LayoutRes int rootView) {

        if (rootView != 0) {
            inflate(getContext(), rootView, this);
        } else {
            inflate(getContext(), R.layout.layout_cook, this);
        }

        layoutCostomCookBar = findViewById(R.id.cookie);
        tvTitle = findViewById(R.id.tv_title);
        tvMessage = findViewById(R.id.tv_message);
        ivIcon = findViewById(R.id.iv_icon);
        btnAction = findViewById(R.id.btn_action);

        validateLayoutIntegrity();
        initDefaultStyle(getContext());
        layoutCostomCookBar.setOnTouchListener(this);
    }

    private void validateLayoutIntegrity() {
        if (layoutCostomCookBar == null || tvTitle == null || tvMessage == null ||
                ivIcon == null || btnAction == null) {

            throw new RuntimeException("Your custom CostomCookBar view is missing one of the default required views");
        }
    }


    /**
     * Init the default text color or background color. You can change the default style by set the
     * Theme's attributes.
     * <p>
     * <pre>
     *  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     *          <item name="CostomCookBarTitleColor">@color/default_title_color</item>
     *          <item name="CostomCookBarMessageColor">@color/default_message_color</item>
     *          <item name="CostomCookBarActionColor">@color/default_action_color</item>
     *          <item name="CostomCookBarBackgroundColor">@color/default_bg_color</item>
     *  </style>
     * </pre>
     */
    private void initDefaultStyle(Context context) {
        //Custom the default style of a CostomCookBar
        int titleColor = ThemeResolver.getColor(context, R.attr.cookieTitleColor, Color.WHITE);
        int messageColor = ThemeResolver.getColor(context, R.attr.cookieMessageColor, Color.WHITE);
        int actionColor = ThemeResolver.getColor(context, R.attr.cookieActionColor, Color.WHITE);
        int backgroundColor = ThemeResolver.getColor(context, R.attr.cookieBackgroundColor,
                ContextCompat.getColor(context, R.color.blue));

        tvTitle.setTextColor(titleColor);
        tvMessage.setTextColor(messageColor);
        btnAction.setTextColor(actionColor);
        layoutCostomCookBar.setBackgroundColor(backgroundColor);
    }

    public void setParams(final CookieBar.Params params) {
        initViews(params.customView);

        duration = params.duration;
        layoutGravity = params.layoutGravity;
        animationDuration = params.animationDuration;
        animationIn = params.animationIn;
        animationOut = params.animationOut;

        //Icon
        if (params.iconResId != 0) {
            ivIcon.setVisibility(VISIBLE);
            ivIcon.setBackgroundResource(params.iconResId);
            if (params.iconAnimator != null) {
                params.iconAnimator.setTarget(ivIcon);
                params.iconAnimator.start();

            }
        }

        //Title
        if (!TextUtils.isEmpty(params.title)) {
            tvTitle.setVisibility(VISIBLE);
            tvTitle.setText(params.title);
            if (params.titleColor != 0) {
                tvTitle.setTextColor(ContextCompat.getColor(getContext(), params.titleColor));
            }
        }

        //Message
        if (!TextUtils.isEmpty(params.message)) {
            tvMessage.setVisibility(VISIBLE);
            tvMessage.setText(params.message);
            if (params.messageColor != 0) {
                tvMessage.setTextColor(ContextCompat.getColor(getContext(), params.messageColor));
            }
        }

        //Action
        if (!TextUtils.isEmpty(params.action) && params.onActionClickListener != null) {
            btnAction.setVisibility(VISIBLE);
            btnAction.setText(params.action);
            btnAction.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    params.onActionClickListener.onClick();
                    dismiss();
                }
            });

            //Action Color
            if (params.actionColor != 0) {
                btnAction.setTextColor(ContextCompat.getColor(getContext(), params.actionColor));
            }
        }

        //Background
        if (params.backgroundColor != 0) {
            layoutCostomCookBar
                    .setBackgroundColor(ContextCompat.getColor(getContext(), params.backgroundColor));
        }

        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_16dp);
        if (layoutGravity == Gravity.BOTTOM) {
            layoutCostomCookBar.setPadding(padding, padding, padding, padding);
        }

        createInAnim();
        createOutAnim();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        viewWidth = getWidth();
        dismissOffsetThreshold = viewWidth / 3;

        if (layoutGravity == Gravity.TOP) {
            super.onLayout(changed, l, 0, r, layoutCostomCookBar.getMeasuredHeight());
        } else {
            super.onLayout(changed, l, t, r, b);
        }
    }

    private void createInAnim() {
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), (layoutGravity == Gravity.BOTTOM) ? animationIn[0] : animationIn[1]);
        slideInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // no implementation
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, duration);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // no implementation
            }
        });
        slideInAnimation.setDuration(animationDuration);
        setAnimation(slideInAnimation);
    }

    private void createOutAnim() {
        slideOutAnimation = AnimationUtils.loadAnimation(getContext(), layoutGravity == Gravity.BOTTOM ? animationOut[0] : animationOut[1]);
        slideOutAnimation.setDuration(animationDuration);
        slideOutAnimationDuration = slideOutAnimation.getDuration();
        slideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // no implementation
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // no implementation
            }
        });
    }

    public void dismiss() {
        dismiss(null);
    }

    public void dismiss(final CostomCookBarBarDismissListener listener) {
        if (swipedOut) {
            removeFromParent();
            return;
        }

        slideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {
                // no implementation
            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                if (listener != null) {
                    listener.onDismiss();
                }
                setVisibility(View.GONE);
                removeFromParent();
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {
                // no implementation
            }
        });

        long speed = listener == null ? slideOutAnimationDuration : slideOutAnimationDuration / 2;
        slideOutAnimation.setDuration(speed);
        startAnimation(slideOutAnimation);
    }

    private void removeFromParent() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewParent parent = getParent();
                if (parent != null) {
                    CostomCookView.this.clearAnimation();
                    ((ViewGroup) parent).removeView(CostomCookView.this);
                }
            }
        }, 200);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialDragX = motionEvent.getRawX();
                return true;

            case MotionEvent.ACTION_UP:
                if (!swipedOut) {
                    view.animate()
                            .x(0)
                            .alpha(1)
                            .setDuration(200)
                            .start();
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if (swipedOut) {
                    return true;
                }
                float offset = motionEvent.getRawX() - initialDragX;
                float alpha = 1 - Math.abs(offset / viewWidth);
                long duration = 0;

                if (Math.abs(offset) > dismissOffsetThreshold) {
                    offset = viewWidth * Math.signum(offset);
                    alpha = 0;
                    duration = 200;
                    swipedOut = true;
                }

                view.animate()
                        .setListener(swipedOut ? getDestroyListener() : null)
                        .x(offset)
                        .alpha(alpha)
                        .setDuration(duration)
                        .start();

                return true;

            default:
                return false;
        }
    }

    private Animator.AnimatorListener getDestroyListener() {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // no implementation
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeFromParent();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // no implementation
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // no implementation
            }
        };
    }

    public interface CostomCookBarBarDismissListener {
        void onDismiss();
    }
}
