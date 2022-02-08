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

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewOrder(@Valid @RequestBody Order order, BindingResult result){
        ResponseEntity<?> hasErrors= mapValidationErrorService.MapValidationService(result);
        if(hasErrors==null){
            Order savedOrder = orderService.saveOrder(order);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + savedOrder.getId()).build().toUri();
            // return new ResponseEntity<Object>(uri, HttpStatus.CREATED);
            return ResponseEntity.created(uri).build();
        }else{
            return hasErrors;
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId){
        Order order = orderService.findOrderByOrderId(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

}
