package com.tmall.controller;

import com.tmall.pojo.Order;
import com.tmall.service.OrderItemService;
import com.tmall.service.OrderService;
import com.tmall.util.Page4Navigator;
import com.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public Page4Navigator<Order> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                      @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Page4Navigator<Order> page =orderService.list(start, size, 5);
        orderItemService.fill(page.getContent());
        orderService.removeOrderFromOrderItem(page.getContent());
        return page;
    }
    @RequestMapping(value = "deliveryOrder/{oid}",method = RequestMethod.PUT)
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        Order o = orderService.get(oid);
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }
    @RequestMapping(value = "refundOrder/{oid}",method = RequestMethod.PUT)
    public Object refundOrder(@PathVariable int oid) throws IOException {
        Order o = orderService.get(oid);
        o.setRefund(2);
        o.setRefundDate(new Date());
        orderService.update(o);
        return Result.success();
    }
    @RequestMapping(value = "unrefundOrder/{oid}",method = RequestMethod.PUT)
    public Object unrefundOrder(@PathVariable int oid) throws IOException {
        Order o = orderService.get(oid);
        o.setRefund(-1);
        orderService.update(o);
        return Result.success();
    }
}