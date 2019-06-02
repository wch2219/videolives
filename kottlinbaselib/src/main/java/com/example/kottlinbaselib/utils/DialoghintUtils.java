package com.example.kottlinbaselib.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.StringRes;
import com.example.kottlinbaselib.R;


public class DialoghintUtils implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private ViewHolder mViewHolder;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mAlertDialog;

    public DialoghintUtils init(Context context) {
        this.mContext = context;
        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setCancelable(false);
        mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_hint, null);
        mViewHolder = new ViewHolder(mView);
        mBuilder.setView(mView);
        mAlertDialog = mBuilder.create();
        Window window = mAlertDialog.getWindow();
        window.setWindowAnimations(R.style.dialogWindowAnim);
       window.setBackgroundDrawableResource(android.R.color.transparent);
        mViewHolder.mBtnClose.setOnClickListener(this);
        mViewHolder.mBtnAff.setOnClickListener(this);
        return this;
    }

    public DialoghintUtils setTitle(@StringRes int title){
        mViewHolder.mTvTitle.setText(title);
        return this;
    }

    public DialoghintUtils setContent(@StringRes int content){
        mViewHolder.mTvContent.setText(content);
        return this;
    }
     public DialoghintUtils setClose(@StringRes int content){
        mViewHolder.mBtnClose.setText(content);
        return this;
    }
     public DialoghintUtils setAff(@StringRes int content){
        mViewHolder.mBtnAff.setText(content);
        return this;
    }
    public DialoghintUtils setOnclick(ButtonOnClickListener buttonOnClickListener){
          this.mButtonOnClickListener = buttonOnClickListener;

        return this;
    }
    public AlertDialog show(){
        mAlertDialog.show();
        if (TextUtils.isEmpty(mViewHolder.mBtnClose.getText().toString())) {
            mViewHolder.mBtnClose.setVisibility(View.GONE);
            mViewHolder.mBtnAff.setBackgroundResource(R.drawable.select_dialog_aff);
        }
        return mAlertDialog;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_close) {
            mAlertDialog.dismiss();
            if (mButtonOnClickListener == null) {
                return;
            }
            mButtonOnClickListener.close(mAlertDialog);

        } else if (i == R.id.btn_aff) {
            mAlertDialog.dismiss();
            if (mButtonOnClickListener == null) {
                return;
            }
            mButtonOnClickListener.aff(mAlertDialog);

        }
    }

    private class ViewHolder {
        public View rootView;
        public TextView mTvTitle;
        public TextView mTvContent;
        public Button mBtnClose;
        public Button mBtnAff;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
            this.mTvContent = (TextView) rootView.findViewById(R.id.tv_content);
            this.mBtnClose = (Button) rootView.findViewById(R.id.btn_close);
            this.mBtnAff = (Button) rootView.findViewById(R.id.btn_aff);
        }

    }
    private ButtonOnClickListener mButtonOnClickListener;
    public interface ButtonOnClickListener{
        void close(AlertDialog alertDialog);
        void aff(AlertDialog alertDialog);
    }

}
