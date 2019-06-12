package com.example.videolive.ui.activitys

import android.content.Intent
import android.view.View
import com.example.videolive.MainActivity
import com.example.videolive.R
import com.example.videolive.mvp.presenter.LoginPresenter
import com.example.videolive.mvp.view.LoginView
import com.example.videolive.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter, LoginView>(), View.OnClickListener,LoginView {
    override fun getlayoutId(): Int {

        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {
        et_phone.setText("18637051978")
        et_password.setText("ww111111")
    }

    override fun initListener() {
        btn_login.setOnClickListener(this::onClick)
        tv_forgetpwd.setOnClickListener(this::onClick)
        tv_register.setOnClickListener(this::onClick)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {//登录
               login()
            }
            R.id.tv_forgetpwd -> {//忘记密码
                startActivity(Intent(mContext, ForgetPwdActivity::class.java))

            }
            R.id.tv_register -> {//注册
                startActivity(Intent(mContext, RegisterActivity::class.java))

            }

        }
    }

    private fun login() {
        val phone = et_phone.text.toString().trim()
        val pwd = et_password.text.toString().trim()
        presenter.login(phone,pwd)
    }

    override fun loginSuccess() {
        startActivity(Intent(mContext,MainActivity::class.java))
        finish()
    }

    override fun createPresenter(): LoginPresenter? {
        return LoginPresenter(mvpView)

    }
}
