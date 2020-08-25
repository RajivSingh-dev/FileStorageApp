package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer fileid;
    private String docname;
    private String contentype;
    private String docsize;
    private Integer userid;
private byte[] filedata;

    public File(Integer fileid, String docname, String contenttype, String doctype, String doctize, Integer userid, byte[] filedata) {
        this.fileid = fileid;
        this.docname = docname;
        this.contentype = contenttype;
        this.docsize = docsize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileid;
    }

    public void setFileId(Integer fileId) {
        this.fileid = fileId;
    }

    public String getDocName() {
        return docname;
    }

    public void setDocName(String docName) {
        this.docname = docName;
    }

    public String getContentype() {
        return contentype;
    }

    public void setContentype(String contentype) {
        this.contentype = contentype;
    }
    public String getDocsize() {
        return docsize;
    }

    public void setDocsize(String docsize) {
        this.docsize = docsize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
