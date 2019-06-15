package com.example.videolive.ui.activitys

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import com.example.videolive.R
import com.example.videolive.model.bean.AuthCodeBean
import com.example.videolive.mvp.presenter.RegisterPresenter
import com.example.videolive.mvp.view.RegisterView
import com.example.videolive.ui.base.BaseActivity
import com.hg.kotlin.api.ApiContents
import kotlinx.android.synthetic.main.activity_forget_pwd.*


class ForgetPwdActivity : BaseActivity<RegisterPresenter,RegisterView>(),RegisterView, View.OnClickListener {
    var sending:Boolean = false
    override fun getlayoutId(): Int {
        return R.layout.activity_forget_pwd
    }

    override fun initView() {

    }

    override fun initData() {

    }
    override fun initListener() {
        tv_auth.setOnClickListener(this::onClick)
        btn_register.setOnClickListener(this::onClick)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_auth ->{
                val phone = et_phone.text.trim().toString()
                if (!sending) {
                    presenter.getAuth(phone,ApiContents.ForPWdAuthCode)
                }
            }
            R.id.btn_register ->{
                registerApi()

            }
        }
    }

    private fun registerApi() {
        val phone = et_phone.text.trim().toString()
        val authcode = et_authcode.text.trim().toString()
        val password = et_newpwd.text?.trim().toString()
        val affpwd = et_affpwd.text?.trim().toString()


        presenter.register(phone,password,affpwd,authcode,ApiContents.ForPWd,"")
    }

    @SuppressLint("SetTextI18n")
    override fun authSuccess(t: AuthCodeBean) {
        et_authcode.setText( t.data.msgX.replace("验证码为：",""))
        mCountDownTimer.start()
    }
    var mCountDownTimer = object : CountDownTimer(30*1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            sending = true
            tv_auth.text = String.format("%dS后重发",millisUntilFinished/1000)
        }
        override fun onFinish() {
            sending = false
            tv_auth.text = "重新发送"

        }
    }

    override fun cleanPwd() {
        et_affpwd.setText("")
        et_newpwd.setText("")
    }

    override fun registerSuccess() {
        //注册成功
        finish()
    }

    override fun createPresenter(): RegisterPresenter? {
        return RegisterPresenter(mvpView)
    }

    override fun setInvitation(user_agent_code: String?) {

    }

    override fun onDestroy() {

        mCountDownTimer.cancel()
        super.onDestroy()
    }
}
