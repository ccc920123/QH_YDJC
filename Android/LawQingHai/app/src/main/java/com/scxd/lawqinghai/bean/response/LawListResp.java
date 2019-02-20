package com.scxd.lawqinghai.bean.response;

import java.util.List;

/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class LawListResp {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"list":[{"id":"74EF83AEB8742D7DE0532402650BBFDB","name":"机动车驾驶证申领和使用规定","status":null,
     * "createDate":null,"createUser":null,"lawsType":null,"content":null},{"id":"74EF83AEB8AHJFH923411302650B25YD",
     * "name":"中华人民共和国道路交通安全法","status":null,"createDate":null,"createUser":null,"lawsType":null,"content":null}],
     * "total":2}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * list : [{"id":"74EF83AEB8742D7DE0532402650BBFDB","name":"机动车驾驶证申领和使用规定","status":null,"createDate":null,
         * "createUser":null,"lawsType":null,"content":null},{"id":"74EF83AEB8AHJFH923411302650B25YD",
         * "name":"中华人民共和国道路交通安全法","status":null,"createDate":null,"createUser":null,"lawsType":null,"content":null}]
         * total : 2
         */

        private String total;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 74EF83AEB8742D7DE0532402650BBFDB
             * name : 机动车驾驶证申领和使用规定
             * status : null
             * createDate : null
             * createUser : null
             * lawsType : null
             * content : null
             */

            private String id;
            private String name;
            private String status;
            private String createDate;
            private String createUser;
            private String lawsType;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getLawsType() {
                return lawsType;
            }

            public void setLawsType(String lawsType) {
                this.lawsType = lawsType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
