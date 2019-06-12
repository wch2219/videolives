package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowListBean extends BaseResult {


    /**
     * data : {"code":0,"info":[{"avatar":"http://yunbaoqiniu.yhcdy.top/20181113093623_2863f82a234dff54039cbadad558690e?imageView2/2/w/600/h/600","avatar_thumb":"http://yunbaoqiniu.yhcdy.top/20181113093623_2863f82a234dff54039cbadad558690e?imageView2/2/w/200/h/200","birthday":"","city":"好像在火星","coin":"896092414","consumption":"4110415","id":"21783","isattention":1,"issuper":"0","level":"20","level_anchor":"7","liang":{"name":"6666"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"弓长清女宛","user_status":"1","vip":{"type":"0"},"votestotal":"21415"},{"avatar":"http://yunbaoqiniu.yhcdy.top/20190111144541_395f35370d906fcdcf950a1475b009fd?imageView2/2/w/600/h/600","avatar_thumb":"http://yunbaoqiniu.yhcdy.top/20190111144541_395f35370d906fcdcf950a1475b009fd?imageView2/2/w/200/h/200","birthday":"","city":"泰安市","coin":"131833","consumption":"69953","id":"21824","isattention":1,"issuper":"0","level":"11","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"菇凉","user_status":"1","vip":{"type":"0"},"votestotal":"0"},{"avatar":"http://thirdqq.qlogo.cn/qqapp/1105507061/9B3CD50C0B2E415CD3745559CAFB8769/100","avatar_thumb":"http://thirdqq.qlogo.cn/qqapp/1105507061/9B3CD50C0B2E415CD3745559CAFB8769/100","birthday":"","city":"泰安市","coin":"138640","consumption":"62130","id":"21832","isattention":1,"issuper":"0","level":"11","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"一勺晚安","user_status":"1","vip":{"type":"0"},"votestotal":"0"},{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/To2Wj6RDKbjicDgZKiaCbo4jicFtxvIy46Z42YGmpQUKOmH5jKvWzgnIFlr11WnqC5zK10B2q15l2ibI9OianHACGZg/132","avatar_thumb":"http://thirdwx.qlogo.cn/mmopen/vi_32/To2Wj6RDKbjicDgZKiaCbo4jicFtxvIy46Z42YGmpQUKOmH5jKvWzgnIFlr11WnqC5zK10B2q15l2ibI9OianHACGZg/64","birthday":"","city":"好像在火星","coin":"200550","consumption":"20","id":"22365","isattention":1,"issuper":"0","level":"1","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"风中承诺","user_status":"1","vip":{"type":"0"},"votestotal":"0"}],"msg":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 0
         * info : [{"avatar":"http://yunbaoqiniu.yhcdy.top/20181113093623_2863f82a234dff54039cbadad558690e?imageView2/2/w/600/h/600","avatar_thumb":"http://yunbaoqiniu.yhcdy.top/20181113093623_2863f82a234dff54039cbadad558690e?imageView2/2/w/200/h/200","birthday":"","city":"好像在火星","coin":"896092414","consumption":"4110415","id":"21783","isattention":1,"issuper":"0","level":"20","level_anchor":"7","liang":{"name":"6666"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"弓长清女宛","user_status":"1","vip":{"type":"0"},"votestotal":"21415"},{"avatar":"http://yunbaoqiniu.yhcdy.top/20190111144541_395f35370d906fcdcf950a1475b009fd?imageView2/2/w/600/h/600","avatar_thumb":"http://yunbaoqiniu.yhcdy.top/20190111144541_395f35370d906fcdcf950a1475b009fd?imageView2/2/w/200/h/200","birthday":"","city":"泰安市","coin":"131833","consumption":"69953","id":"21824","isattention":1,"issuper":"0","level":"11","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"菇凉","user_status":"1","vip":{"type":"0"},"votestotal":"0"},{"avatar":"http://thirdqq.qlogo.cn/qqapp/1105507061/9B3CD50C0B2E415CD3745559CAFB8769/100","avatar_thumb":"http://thirdqq.qlogo.cn/qqapp/1105507061/9B3CD50C0B2E415CD3745559CAFB8769/100","birthday":"","city":"泰安市","coin":"138640","consumption":"62130","id":"21832","isattention":1,"issuper":"0","level":"11","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"一勺晚安","user_status":"1","vip":{"type":"0"},"votestotal":"0"},{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/To2Wj6RDKbjicDgZKiaCbo4jicFtxvIy46Z42YGmpQUKOmH5jKvWzgnIFlr11WnqC5zK10B2q15l2ibI9OianHACGZg/132","avatar_thumb":"http://thirdwx.qlogo.cn/mmopen/vi_32/To2Wj6RDKbjicDgZKiaCbo4jicFtxvIy46Z42YGmpQUKOmH5jKvWzgnIFlr11WnqC5zK10B2q15l2ibI9OianHACGZg/64","birthday":"","city":"好像在火星","coin":"200550","consumption":"20","id":"22365","isattention":1,"issuper":"0","level":"1","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"风中承诺","user_status":"1","vip":{"type":"0"},"votestotal":"0"}]
         * msg :
         */

        private int code;
        @SerializedName("msg")
        private String msgX;
        private List<InfoBean> info;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsgX() {
            return msgX;
        }

        public void setMsgX(String msgX) {
            this.msgX = msgX;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * avatar : http://yunbaoqiniu.yhcdy.top/20181113093623_2863f82a234dff54039cbadad558690e?imageView2/2/w/600/h/600
             * avatar_thumb : http://yunbaoqiniu.yhcdy.top/20181113093623_2863f82a234dff54039cbadad558690e?imageView2/2/w/200/h/200
             * birthday :
             * city : 好像在火星
             * coin : 896092414
             * consumption : 4110415
             * id : 21783
             * isattention : 1
             * issuper : 0
             * level : 20
             * level_anchor : 7
             * liang : {"name":"6666"}
             * province :
             * sex : 2
             * signature : 这家伙很懒，什么都没留下
             * user_nicename : 弓长清女宛
             * user_status : 1
             * vip : {"type":"0"}
             * votestotal : 21415
             */

            private String avatar;
            private String avatar_thumb;
            private String birthday;
            private String city;
            private String coin;
            private String consumption;
            private String id;
            private int isattention;
            private String issuper;
            private String level;
            private String level_anchor;
            private LiangBean liang;
            private String province;
            private String sex;
            private String signature;
            private String user_nicename;
            private String user_status;
            private VipBean vip;
            private String votestotal;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAvatar_thumb() {
                return avatar_thumb;
            }

            public void setAvatar_thumb(String avatar_thumb) {
                this.avatar_thumb = avatar_thumb;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }

            public String getConsumption() {
                return consumption;
            }

            public void setConsumption(String consumption) {
                this.consumption = consumption;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIsattention() {
                return isattention;
            }

            public void setIsattention(int isattention) {
                this.isattention = isattention;
            }

            public String getIssuper() {
                return issuper;
            }

            public void setIssuper(String issuper) {
                this.issuper = issuper;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getLevel_anchor() {
                return level_anchor;
            }

            public void setLevel_anchor(String level_anchor) {
                this.level_anchor = level_anchor;
            }

            public LiangBean getLiang() {
                return liang;
            }

            public void setLiang(LiangBean liang) {
                this.liang = liang;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
            }

            public String getUser_status() {
                return user_status;
            }

            public void setUser_status(String user_status) {
                this.user_status = user_status;
            }

            public VipBean getVip() {
                return vip;
            }

            public void setVip(VipBean vip) {
                this.vip = vip;
            }

            public String getVotestotal() {
                return votestotal;
            }

            public void setVotestotal(String votestotal) {
                this.votestotal = votestotal;
            }

            public static class LiangBean {
                /**
                 * name : 6666
                 */

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class VipBean {
                /**
                 * type : 0
                 */

                private String type;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
