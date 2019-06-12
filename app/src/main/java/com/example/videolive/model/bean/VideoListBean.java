package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoListBean extends BaseResult{


    /**
     * data : {"code":0,"info":[{"ad_endtime":"0","ad_url":"","addtime":"1560224687","city":"","comments":"0","datetime":"1天前","href":"http://yunbao.tlimit.top/test.mp4","id":"278","is_ad":"0","isattent":"0","isdel":"0","islike":"0","isstep":"0","lat":"","likes":"0","lng":"","music_id":"0","nopass_time":"0","orderno":"0","shares":"0","show_val":"0","status":"1","steps":"0","thumb":"http://www.tlimit.to/data/upload/20190611/5cff23685005e.jpg","thumb_s":"http://www.tlimit.to/data/upload/20190611/5cff23685005e.jpg","title":"首页设置","uid":"24200","userinfo":{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/132","avatar_thumb":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/64","birthday":"","city":"好像在火星","coin":"92298","consumption":"7713","id":"24200","issuper":"1","level":"6","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"疯子","user_status":"1","vip":{"type":"0"},"votestotal":"0"},"views":"1","watch_ok":"1","xiajia_reason":""}],"msg":""}
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
         * info : [{"ad_endtime":"0","ad_url":"","addtime":"1560224687","city":"","comments":"0","datetime":"1天前","href":"http://yunbao.tlimit.top/test.mp4","id":"278","is_ad":"0","isattent":"0","isdel":"0","islike":"0","isstep":"0","lat":"","likes":"0","lng":"","music_id":"0","nopass_time":"0","orderno":"0","shares":"0","show_val":"0","status":"1","steps":"0","thumb":"http://www.tlimit.to/data/upload/20190611/5cff23685005e.jpg","thumb_s":"http://www.tlimit.to/data/upload/20190611/5cff23685005e.jpg","title":"首页设置","uid":"24200","userinfo":{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/132","avatar_thumb":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/64","birthday":"","city":"好像在火星","coin":"92298","consumption":"7713","id":"24200","issuper":"1","level":"6","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"疯子","user_status":"1","vip":{"type":"0"},"votestotal":"0"},"views":"1","watch_ok":"1","xiajia_reason":""}]
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
             * ad_endtime : 0
             * ad_url :
             * addtime : 1560224687
             * city :
             * comments : 0
             * datetime : 1天前
             * href : http://yunbao.tlimit.top/test.mp4
             * id : 278
             * is_ad : 0
             * isattent : 0
             * isdel : 0
             * islike : 0
             * isstep : 0
             * lat :
             * likes : 0
             * lng :
             * music_id : 0
             * nopass_time : 0
             * orderno : 0
             * shares : 0
             * show_val : 0
             * status : 1
             * steps : 0
             * thumb : http://www.tlimit.to/data/upload/20190611/5cff23685005e.jpg
             * thumb_s : http://www.tlimit.to/data/upload/20190611/5cff23685005e.jpg
             * title : 首页设置
             * uid : 24200
             * userinfo : {"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/132","avatar_thumb":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/64","birthday":"","city":"好像在火星","coin":"92298","consumption":"7713","id":"24200","issuper":"1","level":"6","level_anchor":"1","liang":{"name":"0"},"province":"","sex":"2","signature":"这家伙很懒，什么都没留下","user_nicename":"疯子","user_status":"1","vip":{"type":"0"},"votestotal":"0"}
             * views : 1
             * watch_ok : 1
             * xiajia_reason :
             */

            private String ad_endtime;
            private String ad_url;
            private String addtime;
            private String city;
            private String comments;
            private String datetime;
            private String href;
            private String id;
            private String is_ad;
            private String isattent;
            private String isdel;
            private String islike;
            private String isstep;
            private String lat;
            private String likes;
            private String lng;
            private String music_id;
            private String nopass_time;
            private String orderno;
            private String shares;
            private String show_val;
            private String status;
            private String steps;
            private String thumb;
            private String thumb_s;
            private String title;
            private String uid;
            private UserinfoBean userinfo;
            private String views;
            private String watch_ok;
            private String xiajia_reason;

            public String getAd_endtime() {
                return ad_endtime;
            }

            public void setAd_endtime(String ad_endtime) {
                this.ad_endtime = ad_endtime;
            }

            public String getAd_url() {
                return ad_url;
            }

            public void setAd_url(String ad_url) {
                this.ad_url = ad_url;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_ad() {
                return is_ad;
            }

            public void setIs_ad(String is_ad) {
                this.is_ad = is_ad;
            }

            public String getIsattent() {
                return isattent;
            }

            public void setIsattent(String isattent) {
                this.isattent = isattent;
            }

            public String getIsdel() {
                return isdel;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public String getIslike() {
                return islike;
            }

            public void setIslike(String islike) {
                this.islike = islike;
            }

            public String getIsstep() {
                return isstep;
            }

            public void setIsstep(String isstep) {
                this.isstep = isstep;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLikes() {
                return likes;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getMusic_id() {
                return music_id;
            }

            public void setMusic_id(String music_id) {
                this.music_id = music_id;
            }

            public String getNopass_time() {
                return nopass_time;
            }

            public void setNopass_time(String nopass_time) {
                this.nopass_time = nopass_time;
            }

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public String getShares() {
                return shares;
            }

            public void setShares(String shares) {
                this.shares = shares;
            }

            public String getShow_val() {
                return show_val;
            }

            public void setShow_val(String show_val) {
                this.show_val = show_val;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSteps() {
                return steps;
            }

            public void setSteps(String steps) {
                this.steps = steps;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getThumb_s() {
                return thumb_s;
            }

            public void setThumb_s(String thumb_s) {
                this.thumb_s = thumb_s;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public String getWatch_ok() {
                return watch_ok;
            }

            public void setWatch_ok(String watch_ok) {
                this.watch_ok = watch_ok;
            }

            public String getXiajia_reason() {
                return xiajia_reason;
            }

            public void setXiajia_reason(String xiajia_reason) {
                this.xiajia_reason = xiajia_reason;
            }

            public static class UserinfoBean {
                /**
                 * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/132
                 * avatar_thumb : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIZdpuiclSMarfG4cicfaerKwTmcI49NuXJ6s6Qj5B2ib6OHLwXx2ZQU5ic5blic49PkxmfdicsfXfibG7Dw/64
                 * birthday :
                 * city : 好像在火星
                 * coin : 92298
                 * consumption : 7713
                 * id : 24200
                 * issuper : 1
                 * level : 6
                 * level_anchor : 1
                 * liang : {"name":"0"}
                 * province :
                 * sex : 2
                 * signature : 这家伙很懒，什么都没留下
                 * user_nicename : 疯子
                 * user_status : 1
                 * vip : {"type":"0"}
                 * votestotal : 0
                 */

                private String avatar;
                private String avatar_thumb;
                private String birthday;
                private String city;
                private String coin;
                private String consumption;
                private String id;
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
}
