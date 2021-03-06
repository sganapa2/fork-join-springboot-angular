package com.barath.app.service;

import com.barath.app.entity.User;
import com.barath.app.exception.UserNotFoundException;
import com.barath.app.repository.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
    	
    	if(logger.isInfoEnabled()) { logger.info(" saving user with user details {}",Objects.toString(user)); } 
        return this.userRepository.save(user);
    }
    
    public List<User> saveUsers(List<User> users){
    	
    	if(logger.isInfoEnabled()) { logger.info(" saving users with users details {}",Objects.toString(users)); } 
    	return this.userRepository.saveAll(users);
    }

    public User getUser(Long userId){
    	
    	if(logger.isInfoEnabled()) { logger.info(" getting user with userid {}",userId); } 
        Optional<User> bankOptional= this.userRepository.findById(userId);
        bankOptional.orElseThrow( () -> new UserNotFoundException("User with user id "+userId+" not found"));
        return bankOptional.get();
    }

    public User getUserByUserName(String userName){
    	
    	if(logger.isInfoEnabled()) { logger.info(" getting user with username {}",userName); } 
        return this.userRepository.findByUserName(userName);
    }

    public List<User> getUsers(){
    	
    	if(logger.isInfoEnabled()) { logger.info(" getting all the users"); } 
    	List<User> users = this.userRepository.findAll();
        if(logger.isInfoEnabled()){
            users.forEach(user -> logger.info(user.toString()));
        }
        return users;
    }

    @PostConstruct
    public void init(){

        User user1 = new User(1L,"Barath",26,User.Gender.MALE);
        User user2 = new User(2L,"Barath1",36,User.Gender.MALE);
        User user3 = new User(3L,"Barath2",46,User.Gender.MALE);
        User user4 = new User(4L,"Barath3",56,User.Gender.MALE);
        User user5 = new User(5L,"Barath4",66,User.Gender.MALE);
        User user6 = new User(6L,"Barath5",16,User.Gender.MALE);
        User user7 = new User(7L,"Barath6",26,User.Gender.MALE);
        User user8 = new User(8L,"Barath7",46,User.Gender.MALE);
        User user9 = new User(9L,"Barath8",23,User.Gender.MALE);
        User user10 = new User(10L,"Barath9",26,User.Gender.MALE);


        Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10)
                .forEach(this::saveUser);

    }
}
