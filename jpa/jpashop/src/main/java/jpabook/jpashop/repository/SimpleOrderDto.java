package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name= order.getMember().getName();
        orderDate= order.getOrderDate(); //주문시간
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }

}
