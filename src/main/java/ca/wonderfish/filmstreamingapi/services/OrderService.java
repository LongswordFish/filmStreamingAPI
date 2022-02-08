package ca.wonderfish.filmstreamingapi.services;

import ca.wonderfish.filmstreamingapi.domains.Order;
import ca.wonderfish.filmstreamingapi.domains.OrderItem;
import ca.wonderfish.filmstreamingapi.exceptions.OrderIdException;
import ca.wonderfish.filmstreamingapi.repositories.OrderItemRepository;
import ca.wonderfish.filmstreamingapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public Order saveOrder(Order newOrder){
        Order savedOrder = orderRepository.save(newOrder);
        List<OrderItem> orderItemList = newOrder.getOrderItems();
        for(OrderItem item:orderItemList){
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }

    public Order findOrderByOrderId(Long orderId){
        Order order =  orderRepository.findOrderById(orderId);
        if(order == null){
            throw new OrderIdException("order id "+orderId+" doesn't exist");
        }
        return order;
    }

}
