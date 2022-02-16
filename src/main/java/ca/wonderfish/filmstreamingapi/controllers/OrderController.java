package ca.wonderfish.filmstreamingapi.controllers;

import ca.wonderfish.filmstreamingapi.domains.Film;
import ca.wonderfish.filmstreamingapi.domains.Order;
import ca.wonderfish.filmstreamingapi.services.MapValidationErrorService;
import ca.wonderfish.filmstreamingapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewOrder(@Valid @RequestBody Order order, BindingResult result, Principal principal){
        ResponseEntity<?> hasErrors= mapValidationErrorService.MapValidationService(result);
        if(hasErrors==null){
            order.setCustomerEmail(principal.getName());
            Order savedOrder = orderService.saveOrder(order);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + savedOrder.getId()).build().toUri();
            // return new ResponseEntity<Object>(uri, HttpStatus.CREATED);
            return ResponseEntity.created(uri).build();
        }else{
            return hasErrors;
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId,Principal principal){
        Order order = orderService.findOrderByOrderId(orderId,principal.getName());
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getOrdersByUser(Principal principal){
        List<Order> ordersByUser = orderService.findOrdersByUser(principal.getName());
        return new ResponseEntity<List<Order> >(ordersByUser, HttpStatus.OK);
    }

}
