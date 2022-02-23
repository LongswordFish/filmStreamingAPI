package ca.wonderfish.filmstreamingapi.controllers;

import ca.wonderfish.filmstreamingapi.payload.ChargeRequest;
import ca.wonderfish.filmstreamingapi.services.MapValidationErrorService;
import ca.wonderfish.filmstreamingapi.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin
public class CheckOutController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createCharge(@RequestBody ChargeRequest chargeRequest, BindingResult result, Principal principal)
    {

        ResponseEntity<?> hasErrors= mapValidationErrorService.MapValidationService(result);
        System.out.println(chargeRequest);
        if(hasErrors==null){
            if(chargeRequest.getToken() == null)
            {
                throw new RuntimeException("Can't create a charge, try again");
            }

            String chargeId = paymentService.createCharge(chargeRequest.getToken(),chargeRequest.getAmount());

            return new ResponseEntity<String>("Congratulations, your card has been charged", HttpStatus.OK);

        }else{
            return hasErrors;
        }


    }


}
