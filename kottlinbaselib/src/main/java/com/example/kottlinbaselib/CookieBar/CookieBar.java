package com.example.kottlinbaselib.CookieBar;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.*;
import com.example.kottlinbaselib.R;


public class CookieBar {

    private CostomCookView cookieView;
    private final Activity context;

    public static CookieBar.Builder build(Activity activity) {
        return new CookieBar.Builder(activity);
    }

    public static void dismiss(Activity activity) {
        new CookieBar(activity, null);
    }

    private CookieBar(Activity context, CookieBar.Params params) {
        this.context = context;
        if(params == null) {
            // since params is null, this CookieBar object can only be used to dismiss
            // existing cookies
            dismiss();
            return;
        }

        cookieView = new CostomCookView(context);
        cookieView.setParams(params);
    }

    private void show() {
        if (cookieView != null) {
            final ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
            final ViewGroup content = decorView.findViewById(android.R.id.content);
            if (cookieView.getParent() == null) {
                ViewGroup parent = cookieView.getLayoutGravity() == Gravity.BOTTOM ?
                        content : decorView;
                addCookie(parent, cookieView);
            }
        }
    }

    private void dismiss() {
        final ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
        final ViewGroup content = decorView.findViewById(android.R.id.content);

        removeFromParent(decorView);
        removeFromParent(content);
    }

    private void removeFromParent(ViewGroup parent) {
        int childCount = parent .getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if(child instanceof CostomCookView) {
                ((CostomCookView) child).dismiss();
                return;
            }
        }

    }

    private void addCookie(final ViewGroup parent, final CostomCookView cookie) {
        if(cookie.getParent() != null) {
            return;
        }

        // if exists, remove existing cookie
        int childCount = parent .getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if(child instanceof CostomCookView) {
                ((CostomCookView) child).dismiss(new CostomCookView.CostomCookBarBarDismissListener() {
                    @Override
                    public void onDismiss() {
                        parent.addView(cookie);
                    }
                });
                return;
            }
        }

        parent.addView(cookie);
    }

    public static class Builder {

        private final CookieBar.Params params = new CookieBar.Params();

        public final Activity context;

        /**
         * Create a builder for an cookie.
         */
        public Builder(Activity activity) {
            this.context = activity;
        }

        public CookieBar.Builder setIcon(@DrawableRes int iconResId) {
            params.iconResId = iconResId;
            return this;
        }

        public CookieBar.Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public CookieBar.Builder setTitle(@StringRes int resId) {
            params.title = context.getString(resId);
            return this;
        }

        public CookieBar.Builder setMessage(String message) {
            params.message = message;
            return this;
        }

        public CookieBar.Builder setMessage(@StringRes int resId) {
            params.message = context.getString(resId);
            return this;
        }

        public CookieBar.Builder setDuration(long duration) {
            params.duration = duration;
            return this;
        }

        public CookieBar.Builder setTitleColor(@ColorRes int titleColor) {
            params.titleColor = titleColor;
            return this;
        }

        public CookieBar.Builder setMessageColor(@ColorRes int messageColor) {
            params.messageColor = messageColor;
            return this;
        }

        public CookieBar.Builder setBackgroundColor(@ColorRes int backgroundColor) {
            params.backgroundColor = backgroundColor;
            return this;
        }

        public CookieBar.Builder setActionColor(@ColorRes int actionColor) {
            params.actionColor = actionColor;
            return this;
        }

        public CookieBar.Builder setAction(String action, OnActionClickListener onActionClickListener) {
            params.action = action;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public CookieBar.Builder setIconAnimation(@AnimatorRes int iconAnimation) {
            params.iconAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, iconAnimation);
            return this;
        }

        public CookieBar.Builder setAction(@StringRes int resId, OnActionClickListener onActionClickListener) {
            params.action = context.getString(resId);
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public CookieBar.Builder setLayoutGravity(int layoutGravity) {
            params.layoutGravity = layoutGravity;
            return this;
        }

        public CookieBar.Builder setCustomView(@LayoutRes int customView) {
            params.customView = customView;
            return this;
        }

        public CookieBar create() {
            return new CookieBar(context, params);
        }

        public CookieBar show() {
            final CookieBar cookie = create();
            cookie.show();
            return cookie;
        }


    }

    final static class Params {
        public String title;
        public String message;
        public String action;
        public OnActionClickListener onActionClickListener;
        public int iconResId;
        public int backgroundColor;
        public int titleColor;
        public int messageColor;
        public int actionColor;
        public long duration = 2000;
        public long animationDuration = 300;
        public int layoutGravity = Gravity.TOP;
        public AnimatorSet iconAnimator;
        public int customView;
        public int[] animationIn = new int[]{R.anim.slide_in_from_bottom, R.anim.slide_in_from_top};
        public int[] animationOut = new int[]{R.anim.slide_out_to_bottom, R.anim.slide_out_to_top};
    }
}
