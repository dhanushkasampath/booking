package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.entity.UserType;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.repository.UserTypeRepository;
import com.alpha.hotel.hotelbookingbackend.service.UserTypeService;
import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    private final Logger logger = LoggerFactory.getLogger(UserTypeServiceImpl.class);
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Override
    public UserType getUserTypeByType(UserTypeEnum userTypeEnum) throws HotelBookingException {
        logger.debug("getUserTypeByType method started|userType:{}", userTypeEnum);
        UserType userType = userTypeRepository.findByUserType(userTypeEnum);

        if ( userType == null ) {
            logger.error("UserType not found type:{}", userTypeEnum);
            throw new HotelBookingException(HttpStatus.BAD_REQUEST, "UserType not found");
        }

        logger.debug("getUserTypeByType method ended|userType id:{}", userType.getId());
        return userType;
    }
}
