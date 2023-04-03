package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.entity.UserType;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.repository.UserTypeRepository;
import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserTypeServiceImplTest {

    @InjectMocks
    private UserTypeServiceImpl userTypeService;

    @Mock
    private UserTypeRepository userTypeRepository;

    @DisplayName("Should get user type by type successfully")
    @Test
    public void shouldGetUserTypeByType() throws HotelBookingException {
        when(userTypeRepository.findByUserType(any())).thenReturn(getUserType());
        UserType userTypeResponse = userTypeService.getUserTypeByType(UserTypeEnum.CUSTOMER);
        assertNotNull(userTypeResponse);
        assertEquals(UserTypeEnum.CUSTOMER, userTypeResponse.getUserType());
        assertEquals(Long.valueOf(1), userTypeResponse.getId());
    }

    @DisplayName("Should throw HotelBookingException when user type object not found")
    @Test(expected = HotelBookingException.class)
    public void shouldThrowHotelBookingExceptionWhenUserTypeNotFound() throws HotelBookingException {
        when(userTypeRepository.findByUserType(any())).thenReturn(null);
        UserType userTypeResponse = userTypeService.getUserTypeByType(UserTypeEnum.CUSTOMER);
    }

    private UserType getUserType() {
        UserType userType = new UserType();
        userType.setId(1L);
        userType.setUserType(UserTypeEnum.CUSTOMER);
        userType.setUserList(new ArrayList<>());
        return userType;
    }
}