package com.qhit.artActive.pojo;


/** 
* Created by GeneratorCode on 2019/05/10
*/ 

public class ArtActive { 
    private Integer acid;    //主键 
    private String title;    //标题 
    private Integer fid;    //分类 
    private Integer aid;    //用户 
    private String creattime;    //创建 
    private Integer start;    //状态 
    private String acont;    //状态

    public String getacont() {
        return acont;
    }

    public void setacont(String acont) {
        this.acont = acont;
    }

    public Integer getAcid() {
        return acid;
    }

    public void setAcid(Integer acid) { 
        this.acid = acid;
    } 

    public String getTitle() { 
        return title;
    }

    public void setTitle(String title) { 
        this.title = title;
    }
    public Integer getFid() { 
        return fid;
    }

    public void setFid(Integer fid) { 
        this.fid = fid;
    } 

    public Integer getAid() { 
        return aid;
    }

    public void setAid(Integer aid) { 
        this.aid = aid;
    } 

    public String getCreattime() { 
        return creattime;
    }

    public void setCreattime(String creattime) { 
        this.creattime = creattime;
    }
    public Integer getStart() { 
        return start;
    }

    public void setStart(Integer start) { 
        this.start = start;
    } 


 }