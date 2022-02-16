package ca.wonderfish.filmstreamingapi.controllers;

import ca.wonderfish.filmstreamingapi.domains.User;
import ca.wonderfish.filmstreamingapi.payload.JWTLoginSuccessResponse;
import ca.wonderfish.filmstreamingapi.payload.LoginRequest;
import ca.wonderfish.filmstreamingapi.security.JwtTokenProvider;
import ca.wonderfish.filmstreamingapi.services.MapValidationErrorService;
import ca.wonderfish.filmstreamingapi.services.UserService;
import ca.wonderfish.filmstreamingapi.validators.UserValidater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static ca.wonderfish.filmstreamingapi.security.SecurityConstant.TOKEN_PREFIX;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserValidater userValidater;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        //validate passwords match
        userValidater.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap!=null){
            return errorMap;
        }

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED) ;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap!=null){
            return errorMap;
        }

        User user = userService.userAuth(loginRequest);

        String jwt  = TOKEN_PREFIX+jwtTokenProvider.generateTokenForUser(user);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));

    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUserWithToken(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap!=null){
            return errorMap;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt  = TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));

    }
}
