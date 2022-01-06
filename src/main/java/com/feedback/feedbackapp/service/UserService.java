package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.model.User;
import com.feedback.feedbackapp.model.UserProfile;
import com.feedback.feedbackapp.model.request.LoginRequest;
import com.feedback.feedbackapp.model.response.LoginResponse;
import com.feedback.feedbackapp.model.retrieve.RetrieveUser;
import com.feedback.feedbackapp.repository.ProfileRepository;
import com.feedback.feedbackapp.repository.UserRepository;
import com.feedback.feedbackapp.security.JWTUtils;
import com.feedback.feedbackapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private UserRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public User createUser(User userObject){
        LOGGER.info("Calling createUser from service!");
        if (userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            throw new InformationExistException("User with email address " +
                    userObject.getEmailAddress() + " already exists!");
        } else {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            String firstName = userObject.getFirstname();
            String lastName = userObject.getLastname();
            String description = userObject.getRole();
            UserProfile userProfileObject = new UserProfile(firstName, lastName, description);
            profileRepository.save(userProfileObject);
            return userRepository.save(userObject);
        }
    }
    public User findUserByEmailAddress(String email){
        return userRepository.findUserByEmailAddress(email);
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }


//    public  ResponseEntity <?> retrieveUser(RetrieveUser retrieveUser){
//        if (profileRepository.existsByEmailAddress(
//    }


    public User getUserProfile(Long profileId) {
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> user = userRepository.findByIdAndprofileId(userDetails.getUser().getId(), profileId);

        return (User) userRepository.findAll();



    }
}
