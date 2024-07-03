package dio.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import dio.domain.model.User;
import dio.domain.repository.UserRepository;
import dio.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        // userToCreate.getId() !=  null && userRepository.existsById(userToCreate.getId()
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
           throw new IllegalArgumentException("This User ID already exists.");
        }
        return userRepository.save(userToCreate);
    }
    
}
