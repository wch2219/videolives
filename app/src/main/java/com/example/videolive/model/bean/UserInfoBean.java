package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoBean extends BaseResult{


    /**
     * data : {"code":0,"info":[{"agent_switch":"1","avatar":"http://www.tlimit.to/default.jpg","avatar_thumb":"http://www.tlimit.to/default_thumb.jpg","birthday":"","can_view_videos":"10","city":"","coin":"200000","consumption":"0","family_switch":"1","fans":"0","follows":"0","id":"24203","level":"1","level_anchor":"1","liang":{"name":"0"},"lives":0,"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户5678","view_videos":"7","vip":{"type":"0"},"votes":"0.00","votestotal":"0"}],"msg":""}
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
         * info : [{"agent_switch":"1","avatar":"http://www.tlimit.to/default.jpg","avatar_thumb":"http://www.tlimit.to/default_thumb.jpg","birthday":"","can_view_videos":"10","city":"","coin":"200000","consumption":"0","family_switch":"1","fans":"0","follows":"0","id":"24203","level":"1","level_anchor":"1","liang":{"name":"0"},"lives":0,"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"手机用户5678","view_videos":"7","vip":{"type":"0"},"votes":"0.00","votestotal":"0"}]
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
             * agent_switch : 1
             * avatar : http://www.tlimit.to/default.jpg
             * avatar_thumb : http://www.tlimit.to/default_thumb.jpg
             * birthday :
             * can_view_videos : 10
             * city :
             * coin : 200000
             * consumption : 0
             * family_switch : 1
             * fans : 0
             * follows : 0
             * id : 24203
             * level : 1
             * level_anchor : 1
             * liang : {"name":"0"}
             * lives : 0
             * province :
             * sex : 2
             * signature : 这家伙很懒，什么都没留下
             * user_nicename : 手机用户5678
             * view_videos : 7
             * vip : {"type":"0"}
             * votes : 0.00
             * votestotal : 0
             */

            private String agent_switch;
            private String avatar;
            private String avatar_thumb;
            private String birthday;
            private int can_view_videos;
            private String city;
            private String coin;
            private String consumption;
            private String family_switch;
            private String fans;
            private String follows;
            private String id;
            private String level;
            private String level_anchor;
            private LiangBean liang;
            private int lives;
            private String province;
            private String sex;
            private String signature;
            private String user_nicename;
            private int view_videos;
            private VipBean vip;
            private String votes;
            private String votestotal;
            private String invitationcode;

            public String getInvitationcode() {
                return invitationcode;
            }

            public void setInvitationcode(String invitationcode) {
                this.invitationcode = invitationcode;
            }

            public String getAgent_switch() {
                return agent_switch;
            }

            public void setAgent_switch(String agent_switch) {
                this.agent_switch = agent_switch;
            }

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

            public int getCan_view_videos() {
                return can_view_videos;
            }

            public void setCan_view_videos(int can_view_videos) {
                this.can_view_videos = can_view_videos;
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

            public String getFamily_switch() {
                return family_switch;
            }

            public void setFamily_switch(String family_switch) {
                this.family_switch = family_switch;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public String getFollows() {
                return follows;
            }

            public void setFollows(String follows) {
                this.follows = follows;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public int getLives() {
                return lives;
            }

            public void setLives(int lives) {
                this.lives = lives;
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

            public int getView_videos() {
                return view_videos;
            }

            public void setView_videos(int view_videos) {
                this.view_videos = view_videos;
            }

            public VipBean getVip() {
                return vip;
            }

            public void setVip(VipBean vip) {
                this.vip = vip;
            }

            public String getVotes() {
                return votes;
            }

            public void setVotes(String votes) {
                this.votes = votes;
            }

            public String getVotestotal() {
                return votestotal;
            }

            public void setVotestotal(String votestotal) {
                this.votestotal = votestotal;
            }

            public static class LiangBean {
                /**
                 * name : 0
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
