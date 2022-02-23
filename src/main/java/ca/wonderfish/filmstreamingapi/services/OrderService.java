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

    public Order findOrderByOrderId(Long orderId,String userEmail){
        Order order =  orderRepository.findOrderById(orderId);
        if(!userEmail.equals(order.getCustomerEmail())){
            throw new OrderIdException("order id "+orderId+" doesn't belong to user "+userEmail);
        }
        if(order == null){
            throw new OrderIdException("order id "+orderId+" doesn't exist");
        }
        return order;
    }

    public List<Order> findOrdersByUser(String userEmail){
        List<Order> ordersByCustomerEmail = orderRepository.findOrdersByCustomerEmail(userEmail);
        ordersByCustomerEmail.sort((order1,order2)->order2.getCreated_at().compareTo(order1.getCreated_at()));
        if(ordersByCustomerEmail == null){
            throw new OrderIdException("No order for user: "+userEmail);
        }
        return ordersByCustomerEmail;
    }

}
