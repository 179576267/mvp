package com.wzf.mvpdemo.http.model.request;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhenfei
 * 2017-03-13 15:34
 * 删除菜品列表
 */
public class DeleteOrderVegetableDto {

    private List<DeleteOrderVegetableInfo>  deleteOrderVegetableInfos;

    public List<DeleteOrderVegetableInfo> getDeleteOrderVegetableInfos() {
        return deleteOrderVegetableInfos;
    }

    public void setDeleteOrderVegetableInfos(List<DeleteOrderVegetableInfo> deleteOrderVegetableInfos) {
        this.deleteOrderVegetableInfos = deleteOrderVegetableInfos;
    }

    @Override
    public String toString() {
        return "DeleteOrderVegetableDto{" +
                "deleteOrderVegetableInfos=" + deleteOrderVegetableInfos +
                '}';
    }

    public static void main(String[] args) {
        DeleteOrderVegetableDto dto = new DeleteOrderVegetableDto();
        List<DeleteOrderVegetableInfo>  deleteOrderVegetableInfos = new ArrayList<DeleteOrderVegetableInfo>();
        deleteOrderVegetableInfos.add(new DeleteOrderVegetableInfo(31, 11));
        dto.setDeleteOrderVegetableInfos(deleteOrderVegetableInfos);
//        System.out.println(JsonUtils.toJson(dto));

    }


    /**
     * @author wangzhenfei
     *         2017-03-13 16:03
     */
    public static class DeleteOrderVegetableInfo {
        private long orderId;
        private long id;

        public DeleteOrderVegetableInfo() {
        }

        public DeleteOrderVegetableInfo(long orderId, long id) {
            this.orderId = orderId;
            this.id = id;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DeleteVegetableInfo{" +
                    "orderId=" + orderId +
                    ", id=" + id +
                    '}';
        }
    }

}
