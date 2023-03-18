package com.alpha.hotel.hotelbookingbackend.controller;

import com.alpha.hotel.hotelbookingbackend.dto.UserTypeDto;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.JwtService;
import com.alpha.hotel.hotelbookingbackend.service.UserTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user-type")
public class UserTypeController {
    private final Logger logger = LoggerFactory.getLogger(UserTypeController.class);
    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private JwtService jwtService;

    @GetMapping(path = "/fetch-all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserTypeDto>> userLogin(HttpServletRequest request) throws HotelBookingException {
        logger.info("Request received to fetch all user types");
        if(jwtService.validateToken(jwtService.extractJwtTokenFromHeader(request))){
            logger.info("JWT is Valid!");
        }else{
            logger.error("User is Unauthorized to use the service");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        //in future we can define specific permissions here
        List<UserTypeDto> userTypeDtoList = userTypeService.getAllUserTypes();
        logger.info("userTypeDtoList:{}", userTypeDtoList);
        return new ResponseEntity<>(userTypeDtoList, HttpStatus.OK);
    }
}