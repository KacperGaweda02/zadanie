package org.kacper.library.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kacper.library.user.login.LoginDTO;
import org.kacper.library.user.role.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Transactional
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        try {
            log.info("Creating user: {}", userDTO.getLogin());
            User toCreate = userMapper.fromDTO(userDTO);
            User created = userService.create(toCreate);
            log.info("User {} created with ID: {}", created.getLogin(), created.getId());
            return ResponseEntity.ok(userMapper.toDTO(created));
        } catch (RoleNotFoundException ex) {
            log.error("Cannot create user {}. Role {} not found", userDTO.getLogin(), userDTO.getRole());
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    @GetMapping
    public ResponseEntity<UserDTO> get(@PathVariable String login) {
        return ResponseEntity.of(userService.find(login)
                .map(userMapper::toDTO));
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<UserDTO> delete(@PathVariable String login) {
        try {
            log.info("Deleting user: {}", login);
            User deleted = userService.remove(login);
            log.info("Deleted user {}", login);
            return ResponseEntity.ok(userMapper.toDTO(deleted));
        } catch (UserNotFoundException ex) {
            log.error("Cannot delete user {}. User not found", login);
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PostMapping(path = "/validate")
    public ResponseEntity<UserDTO> validate(@Valid @RequestBody LoginDTO login) {
        User user = userService.login(login.getLogin(), login.getPassword());
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.warn(errors.toString());
        return errors;
    }
}
