package com.sohu.sur.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "LOTTERY_SHOW")
public class LotteryShow implements java.io.Serializable {

    private Long lotteryshow_id;

    private String title;

    private String content;

    private String url;

    private String name;

    private String homepage;

    private String stime;

    private Date ctime;

    private String logo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getLotteryshow_id() {
        return lotteryshow_id;
    }

    public void setLotteryshow_id(Long lotteryshow_id) {
        this.lotteryshow_id = lotteryshow_id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "u_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "u_homepage")
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Column(name = "showtime")
    @Index(name="lotteryshow_index")
    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    @Column(name = "ctime")
    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
