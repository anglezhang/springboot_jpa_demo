package cn.com.doone.tx.cloud.service.config.evt.channel;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

/**
 * Created by APPLE on 2017/2/22.
 */
public class QueryConfigChannelEvt extends QueryEvt{

    private Long id;

    private Long parentId;

    private String channelCode;

    private String channelName;

    private String channelType;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
