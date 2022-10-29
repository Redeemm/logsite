package com.example.domain.user;

import com.example.domain.exception.HttpHandler.NotAcceptableException;
import com.example.domain.exception.HttpHandler.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserManagementController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN, STAFF')") //comment it
    public List<User> getUsers() {
        return userService.getUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws NotAcceptableException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotAcceptableException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userID,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userID));

        user.setUserName(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());



        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping(path = "delete/{Id}")
    public void deleteUser(@PathVariable("Id") Long userID) {
        userService.deleteUser(userID);
    }


}
