package com.example.videolive.ui.activitys

import android.annotation.SuppressLint
import android.view.View
import com.example.videolive.R
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.presenter.RegisterPresenter
import com.example.videolive.mvp.view.RegisterView
import com.example.videolive.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterPresenter, RegisterView>(),View.OnClickListener,RegisterView {

    override fun getlayoutId(): Int {
        return R.layout.activity_register
    }



    override fun initView() {

    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        et_phone.setText("18637051978")
    }

    override fun initListener() {
        tv_auth.setOnClickListener(this::onClick)
        btn_register.setOnClickListener(this::onClick)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_auth ->{
               val phone = et_phone.text.trim().toString()
                presenter.getAuth(phone)
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

        val map= mutableMapOf<String,Any>()
        map[Contents.Phone] =
        presenter.register(phone,password,authcode)
    }

    override fun createPresenter(): RegisterPresenter? {
        return RegisterPresenter(mvpView)
    }
}
