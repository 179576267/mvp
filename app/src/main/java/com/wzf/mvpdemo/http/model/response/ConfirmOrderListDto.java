package com.wzf.mvpdemo.http.model.response;

import java.util.List;

/**
 * Created by kjg.
 */
public class ConfirmOrderListDto {

    private boolean isChoose;//自添加字段
    /**
     * orderCreateTime : 1490096029000
     * shopTableNo : 001
     * shopOrderNo : 20170321193349000
     * dishesList : [{"vegetableId":2,"vegetableName":"回锅肉","num":1,"price":30,"type":1,"createTime":1490096029000,"code":""},{"vegetableId":2,"vegetableName":"回锅肉","num":2,"price":30,"type":0,"createTime":1490164189000,"code":""},{"vegetableId":16,"vegetableName":"麻辣牛肚","num":2,"price":56,"type":0,"createTime":1490164189000,"code":""},{"vegetableId":16,"vegetableName":"麻辣牛肚","num":1,"price":56,"type":0,"createTime":1490164214000,"code":""},{"vegetableId":17,"vegetableName":"毛血旺","num":1,"price":87,"type":0,"createTime":1490164214000,"code":""},{"vegetableId":18,"vegetableName":"凉拌猪耳朵","num":1,"price":86,"type":0,"createTime":1490164214000,"code":""},{"vegetableId":17,"vegetableName":"毛血旺","num":2,"price":87,"type":0,"createTime":1490164248000,"code":""},{"vegetableId":16,"vegetableName":"麻辣牛肚","num":5,"price":56,"type":0,"createTime":1490164286000,"code":""},{"vegetableId":16,"vegetableName":"麻辣牛肚","num":3,"price":56,"type":0,"createTime":1490164312000,"code":""},{"vegetableId":16,"vegetableName":"麻辣牛肚","num":3,"price":56,"type":0,"createTime":1490164313000,"code":""},{"vegetableId":17,"vegetableName":"毛血旺","num":3,"price":87,"type":0,"createTime":1490164494000,"code":""},{"vegetableId":18,"vegetableName":"凉拌猪耳朵","num":1,"price":86,"type":0,"createTime":1490164578000,"code":""}]
     */

    private long orderCreateTime;
    private String shopTableNo;
    private String shopOrderNo;
    private String orderId;
    /**
     * vegetableId : 2
     * vegetableName : 回锅肉
     * num : 1
     * price : 30
     * type : 1
     * createTime : 1490096029000
     * code :
     */

    private List<DishesListBean> dishesList;


    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(long orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getShopTableNo() {
        return shopTableNo;
    }

    public void setShopTableNo(String shopTableNo) {
        this.shopTableNo = shopTableNo;
    }

    public String getShopOrderNo() {
        return shopOrderNo;
    }

    public void setShopOrderNo(String shopOrderNo) {
        this.shopOrderNo = shopOrderNo;
    }

    public List<DishesListBean> getDishesList() {
        return dishesList;
    }

    public void setDishesList(List<DishesListBean> dishesList) {
        this.dishesList = dishesList;
    }

    @Override
    public String toString() {
        return "ConfirmOrderListDto{" +
                "isChoose=" + isChoose +
                ", orderCreateTime=" + orderCreateTime +
                ", shopTableNo='" + shopTableNo + '\'' +
                ", shopOrderNo='" + shopOrderNo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", dishesList=" + dishesList +
                '}';
    }

    public static class DishesListBean {
        private String vegetableId;
        private String dishId;
        private String vegetableName;
        private int num;
        private String price;
        private String type;
        private long createTime;
        private String code;
        private boolean isChoose;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

        public String getDishId() {
            return dishId;
        }

        public void setDishId(String dishId) {
            this.dishId = dishId;
        }

        public String getVegetableId() {
            return vegetableId;
        }

        public void setVegetableId(String vegetableId) {
            this.vegetableId = vegetableId;
        }

        public String getVegetableName() {
            return vegetableName;
        }

        public void setVegetableName(String vegetableName) {
            this.vegetableName = vegetableName;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "DishesListBean{" +
                    "vegetableId='" + vegetableId + '\'' +
                    ", dishId='" + dishId + '\'' +
                    ", vegetableName='" + vegetableName + '\'' +
                    ", num=" + num +
                    ", price='" + price + '\'' +
                    ", type='" + type + '\'' +
                    ", createTime=" + createTime +
                    ", code='" + code + '\'' +
                    ", isChoose=" + isChoose +
                    '}';
        }
    }
}
