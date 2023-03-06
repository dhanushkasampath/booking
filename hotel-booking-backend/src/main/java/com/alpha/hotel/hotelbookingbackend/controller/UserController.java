package com.alpha.hotel.hotelbookingbackend.controller;

import com.alpha.hotel.hotelbookingbackend.dto.UserDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginResponseDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserResponseDto;
import com.alpha.hotel.hotelbookingbackend.entity.User;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.UserService;
import com.alpha.hotel.hotelbookingbackend.util.UserLoginTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginResponseDto> userLogin(
            @RequestParam
            UserLoginTypeEnum userLoginType,
            @Valid
            @RequestBody
            UserLoginRequestDto userLoginRequestDto, HttpServletRequest request) throws HotelBookingException {

        logger.info("Request received to authenticate, username : {} ", userLoginRequestDto.getUsername());

        if (userLoginType.toString().equals(UserLoginTypeEnum.GENERAL_LOGIN.toString())) {

            UserLoginResponseDto userLoginResponseDto = userService.userGeneralLogin(userLoginRequestDto);
            logger.debug("User authenticated successfully for username : {}", userLoginRequestDto.getUsername());
            return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);

        } else if (userLoginType.toString().equals(UserLoginTypeEnum.INITIAL_LOGIN.toString())) {

            logger.debug("Login for the first time with user name " + userLoginRequestDto.getUsername());
            userService.userSpecialLogin(userLoginRequestDto, UserLoginTypeEnum.INITIAL_LOGIN);
            logger.debug("Initial login successfully completed for username : {}", userLoginRequestDto.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);

        } else if (userLoginType.toString().equals(UserLoginTypeEnum.FORGET_PASSWORD_LOGIN.toString())) {

            logger.debug("Login to set a new password for forget password with userName:{}", userLoginRequestDto.getUsername());
            userService.userSpecialLogin(userLoginRequestDto, UserLoginTypeEnum.FORGET_PASSWORD_LOGIN);
            logger.debug("Password updated successfully for username : {}", userLoginRequestDto.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            logger.error("Provided login type is not valid:{}", userLoginType);
            throw new HotelBookingException(HttpStatus.BAD_REQUEST, "Invalid Login Type");
        }
    }

    @PutMapping(path = "/forget-password")
    public ResponseEntity<String> forgetPassword(
            @RequestParam
            String email) throws HotelBookingException {
        logger.info("Request received to send a mail to {} with invitation link. user is going to reset the password", email);
        userService.forgetPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserResponseDto> createUser(
            @Valid
            @RequestBody UserDto userDto) throws HotelBookingException {
        logger.info("Received request to create a user");
        User createdUser = userService.create(userDto);
        UserResponseDto userResponseDto = userService.map(createdUser, UserResponseDto.class);
        logger.info("Return response after creating the user");
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}