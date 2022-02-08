package ca.wonderfish.filmstreamingapi.services;

import ca.wonderfish.filmstreamingapi.domains.User;
import ca.wonderfish.filmstreamingapi.exceptions.UserNameExistsException;
import ca.wonderfish.filmstreamingapi.payload.LoginRequest;
import ca.wonderfish.filmstreamingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){
        User existingUser = userRepository.findUserByUsername(newUser.getUsername());
        if(existingUser==null){
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        }
        else{
            throw new UserNameExistsException("username "+newUser.getUsername()+" already exists");
        }
    }

    public User userAuth(LoginRequest loginRequest){
        User user = userRepository.findUserByUsername(loginRequest.getUsername());
        if(user==null){
            throw new UserNameExistsException(loginRequest.getUsername()+" doesn't exist");
        }

        if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new UserNameExistsException("password wrong!");
        }

        return user;
    }
}
