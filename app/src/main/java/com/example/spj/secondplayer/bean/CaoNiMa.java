package com.example.spj.secondplayer.bean;

import java.util.List;

/**
 * Created by spj on 2016/9/14.
 */
public class CaoNiMa {

    /**
     * count : 4286
     * np : 1.473813602E9
     */

    private InfoBean info;
    /**
     * status : 4
     * comment : 50
     * top_comments : [{"voicetime":0,"precid":0,"content":"上班苦，上班累，不如参加黑社会，天天被人轮着睡","like_count":107,"u":{"header":["http://wimg.spriteapp.cn/profile/large/2016/09/13/57d79fe47cfbb_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/09/13/57d79fe47cfbb_mini.jpg"],"sex":"m","uid":"13905236","name":"百吊朝天"},"preuid":0,"passtime":"2016-09-13 09:47:06","voiceuri":"","id":63580766},{"voicetime":0,"precid":0,"content":"爸爸们顶我上去我要日她","like_count":5,"u":{"header":["http://qzapp.qlogo.cn/qzapp/100336987/38FF4C78CD0F51DA5B2F3551106D1D6E/100","http://qzapp.qlogo.cn/qzapp/100336987/38FF4C78CD0F51DA5B2F3551106D1D6E/100"],"sex":"m","uid":"18369432","name":"不在线。82Z"},"preuid":0,"passtime":"2016-09-13 14:05:17","voiceuri":"","id":63596242}]
     * tags : [{"id":1,"name":"搞笑"},{"id":55,"name":"微视频"},{"id":117,"name":"美女"}]
     * bookmark : 149
     * text : 上班苦，上班累，不如参加黑涩会
     * up : 731
     * share_url : http://a.f.budejie.com/share/20618206.html?wx.qq.com
     * down : 161
     * forward : 174
     * u : {"header":["http://img.spriteapp.cn/profile/20151222142903.png","http://img.spriteapp.cn/profile/20151222142903.png"],"is_v":false,"uid":"16967421","is_vip":false,"name":"女神来了"}
     * passtime : 2016-09-14 13:01:02
     * video : {"playfcount":11186,"height":640,"width":480,"video":["http://wvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://dv.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://bvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4"],"download":["http://wvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://dv.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://bvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4"],"duration":18,"playcount":53135,"thumbnail":["http://wimg.spriteapp.cn/picture/2016/0913/57d753b82dbb9__b_73.jpg","http://dimg.spriteapp.cn/picture/2016/0913/57d753b82dbb9__b_73.jpg"],"thumbnail_small":["http://wimg.spriteapp.cn/crop/150x150/picture/2016/0913/57d753b82dbb9__b_73.jpg","http://dimg.spriteapp.cn/crop/150x150/picture/2016/0913/57d753b82dbb9__b_73.jpg"]}
     * type : video
     * id : 20618206
     */

    private List<ListBean> list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        private int count;
        private double np;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getNp() {
            return np;
        }

        public void setNp(double np) {
            this.np = np;
        }
    }

    public static class ListBean {
        private int status;
        private String comment;
        private String bookmark;
        private String text;
        private String up;
        private String share_url;
        private int down;
        private int forward;
        /**
         * header : ["http://img.spriteapp.cn/profile/20151222142903.png","http://img.spriteapp.cn/profile/20151222142903.png"]
         * is_v : false
         * uid : 16967421
         * is_vip : false
         * name : 女神来了
         */

        private UBean u;
        private String passtime;
        /**
         * playfcount : 11186
         * height : 640
         * width : 480
         * video : ["http://wvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://dv.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://bvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4"]
         * download : ["http://wvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://dv.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4","http://bvideo.spriteapp.cn/video/2016/0913/57d753b84c5b9_wpd.mp4"]
         * duration : 18
         * playcount : 53135
         * thumbnail : ["http://wimg.spriteapp.cn/picture/2016/0913/57d753b82dbb9__b_73.jpg","http://dimg.spriteapp.cn/picture/2016/0913/57d753b82dbb9__b_73.jpg"]
         * thumbnail_small : ["http://wimg.spriteapp.cn/crop/150x150/picture/2016/0913/57d753b82dbb9__b_73.jpg","http://dimg.spriteapp.cn/crop/150x150/picture/2016/0913/57d753b82dbb9__b_73.jpg"]
         */

        private VideoBean video;
        private GifEntity gif;
        private ImageEntity image;
        private String type;
        private String id;

        /**
         * voicetime : 0
         * precid : 0
         * content : 上班苦，上班累，不如参加黑社会，天天被人轮着睡
         * like_count : 107
         * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/09/13/57d79fe47cfbb_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/09/13/57d79fe47cfbb_mini.jpg"],"sex":"m","uid":"13905236","name":"百吊朝天"}
         * preuid : 0
         * passtime : 2016-09-13 09:47:06
         * voiceuri :
         * id : 63580766
         */

        private List<TopCommentsBean> top_comments;

        public GifEntity getGif() {
            return gif;
        }

        public void setGif(GifEntity gif) {
            this.gif = gif;
        }

        public ImageEntity getImage() {
            return image;
        }

        public void setImage(ImageEntity image) {
            this.image = image;
        }

        /**
         * id : 1
         * name : 搞笑
         */

        private List<TagsBean> tags;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public UBean getU() {
            return u;
        }

        public void setU(UBean u) {
            this.u = u;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<TopCommentsBean> getTop_comments() {
            return top_comments;
        }

        public void setTop_comments(List<TopCommentsBean> top_comments) {
            this.top_comments = top_comments;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class UBean {
            private boolean is_v;
            private String uid;
            private boolean is_vip;
            private String name;
            private List<String> header;

            public boolean isIs_v() {
                return is_v;
            }

            public void setIs_v(boolean is_v) {
                this.is_v = is_v;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isIs_vip() {
                return is_vip;
            }

            public void setIs_vip(boolean is_vip) {
                this.is_vip = is_vip;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getHeader() {
                return header;
            }

            public void setHeader(List<String> header) {
                this.header = header;
            }
        }

        public static class VideoBean {
            private int playfcount;
            private int height;
            private int width;
            private int duration;
            private int playcount;
            private List<String> video;
            private List<String> download;
            private List<String> thumbnail;
            private List<String> thumbnail_small;

            public int getPlayfcount() {
                return playfcount;
            }

            public void setPlayfcount(int playfcount) {
                this.playfcount = playfcount;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getPlaycount() {
                return playcount;
            }

            public void setPlaycount(int playcount) {
                this.playcount = playcount;
            }

            public List<String> getVideo() {
                return video;
            }

            public void setVideo(List<String> video) {
                this.video = video;
            }

            public List<String> getDownload() {
                return download;
            }

            public void setDownload(List<String> download) {
                this.download = download;
            }

            public List<String> getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(List<String> thumbnail) {
                this.thumbnail = thumbnail;
            }

            public List<String> getThumbnail_small() {
                return thumbnail_small;
            }

            public void setThumbnail_small(List<String> thumbnail_small) {
                this.thumbnail_small = thumbnail_small;
            }
        }

        public static class TopCommentsBean {
            private int voicetime;
            private int precid;
            private String content;
            private int like_count;
            /**
             * header : ["http://wimg.spriteapp.cn/profile/large/2016/09/13/57d79fe47cfbb_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/09/13/57d79fe47cfbb_mini.jpg"]
             * sex : m
             * uid : 13905236
             * name : 百吊朝天
             */

            private UBean u;
            private int preuid;
            private String passtime;
            private String voiceuri;
            private int id;

            public int getVoicetime() {
                return voicetime;
            }

            public void setVoicetime(int voicetime) {
                this.voicetime = voicetime;
            }

            public int getPrecid() {
                return precid;
            }

            public void setPrecid(int precid) {
                this.precid = precid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public UBean getU() {
                return u;
            }

            public void setU(UBean u) {
                this.u = u;
            }

            public int getPreuid() {
                return preuid;
            }

            public void setPreuid(int preuid) {
                this.preuid = preuid;
            }

            public String getPasstime() {
                return passtime;
            }

            public void setPasstime(String passtime) {
                this.passtime = passtime;
            }

            public String getVoiceuri() {
                return voiceuri;
            }

            public void setVoiceuri(String voiceuri) {
                this.voiceuri = voiceuri;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class UBean {
                private String sex;
                private String uid;
                private String name;
                private List<String> header;

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getHeader() {
                    return header;
                }

                public void setHeader(List<String> header) {
                    this.header = header;
                }
            }
        }

        public static class TagsBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class GifEntity{
            private List<String> download_url;
            private List<String> gif_thumbnail;
            private int height;
            private int width;
            private List<String> images;

            public List<String> getDownload_url() {
                return download_url;
            }

            public void setDownload_url(List<String> download_url) {
                this.download_url = download_url;
            }

            public List<String> getGif_thumbnail() {
                return gif_thumbnail;
            }

            public void setGif_thumbnail(List<String> gif_thumbnail) {
                this.gif_thumbnail = gif_thumbnail;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class ImageEntity{
            private List<String> big;
            private List<String> download_url;
            private List<String> medium;
            private List<String> small;
            private int width;
            private int height;

            public List<String> getBig() {
                return big;
            }

            public void setBig(List<String> big) {
                this.big = big;
            }

            public List<String> getDownload_url() {
                return download_url;
            }

            public void setDownload_url(List<String> download_url) {
                this.download_url = download_url;
            }

            public List<String> getMedium() {
                return medium;
            }

            public void setMedium(List<String> medium) {
                this.medium = medium;
            }

            public List<String> getSmall() {
                return small;
            }

            public void setSmall(List<String> small) {
                this.small = small;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
