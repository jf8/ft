package com.kyanite.ft.dingtalk.aes;

public class DingTalkJssdkConfig {
    private String jsticket;
    private String signature;
    private String nonceStr;
    private long timeStamp;
    private String corpId;
    private String agentid;
    private String queryString;

    public String getJsticket() {
        return jsticket;
    }

    public void setJsticket(String jsticket) {
        this.jsticket = jsticket;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public String toString() {
        return "DingTalkJssdkConfig{" +
            "jsticket='" + jsticket + '\'' +
            ", signature='" + signature + '\'' +
            ", nonceStr='" + nonceStr + '\'' +
            ", timeStamp=" + timeStamp +
            ", corpId='" + corpId + '\'' +
            ", agentid='" + agentid + '\'' +
            ", queryString='" + queryString + '\'' +
            '}';
    }
}
