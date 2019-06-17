package com.example.videolive.ui.activitys

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.CountDownTimer
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.kottlinbaselib.utils.PermissionUtils
import com.example.videolive.R
import com.example.videolive.model.bean.AuthCodeBean
import com.example.videolive.mvp.presenter.RegisterPresenter
import com.example.videolive.mvp.view.RegisterView
import com.example.videolive.ui.base.BaseActivity
import com.hg.kotlin.api.ApiContents
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class RegisterActivity : BaseActivity<RegisterPresenter, RegisterView>(),View.OnClickListener,RegisterView {
    var sending:Boolean = false

    override fun getlayoutId(): Int {
        return R.layout.activity_register
    }



    override fun initView() {

    }


    override fun initData() {




    }

    override fun setInvitation(user_agent_code: String?) {

    }

    @SuppressLint("MissingPermission")
    private fun getDevNumber() {
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_PHONE_STATE
//            ) !== PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 101)
//            return
//        }
//        //获取手机号码
//        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
////        val deviceid = tm.getDeviceId()//获取智能设备唯一编号
//        var dev = tm.deviceSoftwareVersion
//        presenter.getInvitation(dev)
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
                    presenter.getAuth(phone, ApiContents.GETCODE)
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
        val  invitationcode = et_Invitacode.text?.trim().toString()

        presenter.register(phone, password, affpwd, authcode, ApiContents.REGISTER,invitationcode)
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



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != 101) {
            return
        }

        if (grantResults.size > 0) {
            val deniedPermissionList = ArrayList<String>()
            for (i in grantResults.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissionList.add(permissions[i])
                }
            }

            if (deniedPermissionList.isEmpty()) {
                //已经全部授权
                getDevNumber()
            } else {

                //勾选了对话框中”Don’t ask again”的选项, 返回false
                for (deniedPermission in deniedPermissionList) {
                    var flag = false
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        flag = shouldShowRequestPermissionRationale(deniedPermission)
                    }
                    if (!flag) {
                        //拒绝授权
                        PermissionUtils.setPermission(R.string.permissoncontent,mContext)
                        return
                    }
                }
                //拒绝授权
                val permission = arrayOfNulls<String>(deniedPermissionList.size)
                for (i in deniedPermissionList.indices) {
                    permission[i] = deniedPermissionList[i]
                }

                ActivityCompat.requestPermissions(this, permission, 101)

            }


        }


    }
    override fun createPresenter(): RegisterPresenter? {
        return RegisterPresenter(mvpView)
    }

    override fun onDestroy() {

        mCountDownTimer.cancel()
        super.onDestroy()
    }
}
