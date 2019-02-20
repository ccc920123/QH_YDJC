package com.scxd.lawqinghai.bean;

import java.io.Serializable;

public class ResponseRootVo implements Serializable {

    private BaseResponseHeadVO head;
    private BaseResponseBodyVO body;

    /**
     * @param head
     * @param body
     */
    public ResponseRootVo(BaseResponseHeadVO head, BaseResponseBodyVO body) {
        super();
        this.head = head;
        this.body = body;
    }

    /**
     * @return the head
     */
    public BaseResponseHeadVO getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(BaseResponseHeadVO head) {
        this.head = head;
    }

    /**
     * @return the body
     */
    public BaseResponseBodyVO getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(BaseResponseBodyVO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseRootVo [head=" + head + ", body=" + body + "]";
    }


}
