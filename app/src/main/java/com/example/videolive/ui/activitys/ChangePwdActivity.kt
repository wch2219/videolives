package com.example.videolive.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kottlinbaselib.mvp.presenter.BasePresenter
import com.example.kottlinbaselib.mvp.view.IView
import com.example.kottlinbaselib.utils.SPUtils
import com.example.kottlinbaselib.utils.ToastUtil
import com.example.videolive.R
import com.example.videolive.model.bean.LoginBean
import com.example.videolive.model.utils.Contents
import com.example.videolive.mvp.model.Model
import com.example.videolive.ui.base.BaseActivity
import com.hg.kotlin.api.CustomObserver
import kotlinx.android.synthetic.main.activity_change_pwd.*
import java.util.*

class ChangePwdActivity : BaseActivity<BasePresenter<IView>, IView>() {
    override fun getlayoutId(): Int {
        return R.layout.activity_change_pwd
    }

    override fun initView() {
        isBlack = true
    }

    override fun initData() {
        btn_login.setOnClickListener {

            val oldpwd = et_oldpwd.text.toString().trim()
            val newold = et_newpwd.text.toString().trim()
            val affnewpwd = et_affpwd.text.toString().trim()

            if (newold.isEmpty()) {
                ToastUtil.show("旧密码不能为空")
                return@setOnClickListener
            }
            if (oldpwd.isEmpty()) {
                ToastUtil.show("新密码密码不能为空")
                return@setOnClickListener
            }
            if (affnewpwd.isEmpty()) {
                ToastUtil.show("确认密码不能为空")
                return@setOnClickListener
            }

            if (oldpwd == affnewpwd) {

                ToastUtil.show("两次输入不一致，请重新输入")
                et_affpwd.setText("")
                et_newpwd.setText("")
                return@setOnClickListener
            }
            val map:MutableMap<String,Any?> = mutableMapOf()
            map[Contents.UID] = SPUtils.getString(Contents.UID)
            map[Contents.Token] = SPUtils.getString(Contents.Token)
            map[Contents.OLDPASS] = oldpwd
            map[Contents.PASS] = newold
            map[Contents.PASS2] = affnewpwd
           val changepwd = Model.getServer().changePwd(map)
            Model.getObservable(changepwd,object :CustomObserver<LoginBean>(mvpView){
                override fun success(t: LoginBean) {
                    if (t.data.code == 0) {
                        mvpView.onAgainLogin()
                    }else{
                        ToastUtil.show(t.data.msgX)
                    }
                }
            })
        }
    }


}
