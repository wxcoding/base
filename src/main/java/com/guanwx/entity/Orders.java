package com.guanwx.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 04:08
 * @Description:
 * @Version: 1.0
 */

@Data
public class Orders {

    //订单ID
    private Long id;
    //订单编号
    private String orderNo;
    //用户ID
    private Long userId;
    //商品ID
    private Long productId;
    //商品名称
    private String productName;
    //购买数量
    private Integer quantity;
    //单价
    private Double unitPrice;
    //总金额
    private Double totalAmount;
    //订单状态：0-待支付，1-已支付，2-已取消，3-已完成
    private Integer status;
    //支付方式：1-微信，2-支付宝，3-银行卡
    private Integer payType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //逻辑删除：0-正常，1-已删除
    private Integer deleteFlag;

}
