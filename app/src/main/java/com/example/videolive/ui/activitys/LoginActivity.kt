package com.example.videolive.ui.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.videolive.R
import com.example.videolive.model.bean.BaseResult
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.ui.base.BaseActivity
import com.hg.kotlin.api.CustomObserver
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<BasePresenter<IView>, IView>(), View.OnClickListener {
    override fun getlayoutId(): Int {

        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {

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
        val map = mutableMapOf<String,Any>()
        map[Contents.Phone] = "18637051978"
        map[Contents.Password] = "123456"
        val login = Model.getServer().login(Model.getRequestBody(map))
        Model.getObservable(login,object :CustomObserver<BaseResult>(mvpView){
            override fun onNext(t: BaseResult) {

            }
        })
    }
}
