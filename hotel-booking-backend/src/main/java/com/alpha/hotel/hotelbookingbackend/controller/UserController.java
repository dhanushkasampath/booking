package com.alpha.hotel.hotelbookingbackend.controller;

import com.alpha.hotel.hotelbookingbackend.dto.UserDto;
import com.alpha.hotel.hotelbookingbackend.dto.ResponseDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginResponseDto;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.UserService;
import com.alpha.hotel.hotelbookingbackend.service.impl.UserServiceImpl;
import com.alpha.hotel.hotelbookingbackend.util.UserLoginTypeEnum;
import com.alpha.hotel.hotelbookingbackend.util.VarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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

    @Autowired
    private ResponseDto responseDTO;

    @PostMapping( path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity <UserLoginResponseDto> userLogin(
            @RequestParam
            UserLoginTypeEnum userLoginType,
            @Valid
            @RequestBody
            UserLoginRequestDto userLoginRequestDto, HttpServletRequest request) throws HotelBookingException {

        logger.info("Request received to authenticate, username : {} ", userLoginRequestDto.getUsername());

        if ( userLoginType.toString().equals(UserLoginTypeEnum.GENERAL_LOGIN.toString()) ) {
            UserLoginResponseDto userLoginResponseDto = userService.userGeneralLogin(userLoginRequestDto);
            logger.info("User authenticated successfully for username : {}", userLoginRequestDto.getUsername());
            return new ResponseEntity <>(userLoginResponseDto, HttpStatus.OK);
        } else if ( userLoginType.toString().equals(UserLoginTypeEnum.FIRST_LOGIN.toString()) ) {

//            auditService.initializeAuditData("Login for the first time with user name " + userLoginRequestDto.getUsername(), request);
//
//            userService.userLogin(userLoginRequestDto, LoginApiType.FIRST_LOGIN, request);
//
//            logger.info("Initial login successfully completed for username : {}", userLoginRequestDto.getUsername());
//            auditService.create(Constants.SUCCESS);
//            return new ResponseEntity <>(HttpStatus.OK);

        } else if ( userLoginType.toString().equals(UserLoginTypeEnum.FORGET_PASSWORD_LOGIN.toString()) ) {

//            auditService.initializeAuditData("Login to set a new password for forget password with user name " + userLoginRequestDto.getUsername(), request);
//
//            userService.userLogin(userLoginRequestDto, LoginApiType.FORGET_PASSWORD_LOGIN, request);
//
//            logger.info("Forget password change successful for username : {}", userLoginRequestDto.getUsername());
//            auditService.create(Constants.SUCCESS);
//            return new ResponseEntity <>(HttpStatus.OK);

        } else {
            logger.error("Provided login type is not valid:{}", userLoginType);
            throw new HotelBookingException(HttpStatus.BAD_REQUEST, "Invalid Login Type");
        }
        return null;
    }

    @PostMapping(value = "/save-customer")
    public ResponseEntity<ResponseDto> saveEmployee(@RequestBody UserDto userDTO) {
        ResponseDto responseDTO = new ResponseDto();
        try {
            String res = userService.saveCustomer(userDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}