package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.config.security.UserJwtTokenCreator;
import com.alpha.hotel.hotelbookingbackend.dto.UserDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginResponseDto;
import com.alpha.hotel.hotelbookingbackend.entity.Customer;
import com.alpha.hotel.hotelbookingbackend.entity.User;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.repo.CustomerRepository;
import com.alpha.hotel.hotelbookingbackend.repo.UserRepository;
import com.alpha.hotel.hotelbookingbackend.service.EncryptDecryptService;
import com.alpha.hotel.hotelbookingbackend.service.UserService;
import com.alpha.hotel.hotelbookingbackend.util.JwtTokenTypeEnum;
import com.alpha.hotel.hotelbookingbackend.util.UserLoginTypeEnum;
import com.alpha.hotel.hotelbookingbackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Value("${user.password.encryption.key}")
    private String secretKey;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserJwtTokenCreator userJwtTokenCreator;
    @Autowired
    private EncryptDecryptService encryptDecryptService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveCustomer(UserDto userDTO) {
        if (customerRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.RSP_DUPLICATED;
        } else {
            Customer customer = modelMapper.map(userDTO, Customer.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            customer.setDateOfBirth(LocalDateTime.parse(userDTO.getDateOfBirth(), formatter));
            customerRepository.save(customer);
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public UserLoginResponseDto userGeneralLogin(UserLoginRequestDto userLoginRequestDto) throws HotelBookingException {
        logger.debug("userGeneralLogin method started. Login requested user_name : {}", userLoginRequestDto.getUsername());

        String providedPassword = userLoginRequestDto.getPassword();
        String password = encryptDecryptService.encrypt(providedPassword, secretKey);
        String userNameProvided = userLoginRequestDto.getUsername();
        User user = userRepository.findByUserName(userNameProvided);

        if (user == null) {
            logger.error("User Authentication Failed");
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "User Authentication Failed");
        }

        String persistPassword = user.getPassword();
        if (Boolean.FALSE.equals(passwordEncoder.matches(password, persistPassword))) {
            logger.error("Password not matched for user name:{}", userLoginRequestDto.getUsername());
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "Invalid User Credentials");
        } else {
            String token = userJwtTokenCreator.generateJwtToken(user, JwtTokenTypeEnum.AUTHORIZED_TOKEN);
            logger.debug("user successfully logged in.");
            return new UserLoginResponseDto(token);
        }
    }

    @Override
    public void userSpecialLogin(UserLoginRequestDto userLoginRequestDto, UserLoginTypeEnum userLoginType) throws HotelBookingException {
        logger.debug("userSpecialLogin method started. Login requested user_name : {}", userLoginRequestDto.getUsername());
        User user = null;
        String loginType = userLoginType.toString();
        String providedPassword = userLoginRequestDto.getPassword();//should be an encoded one
//        String password = encryptDecryptService.decrypt(providedPassword, secretKey);
//        userLoginRequestDto.setPassword(password);
        String providedUserName = userLoginRequestDto.getUsername();
//        userLoginRequestDto.setUsername(username);

        if ( userLoginType.equals(UserLoginTypeEnum.FIRST_LOGIN) ) {

            user = userRepository.findByUserName(providedUserName);

            if ( user.getPassword() != null ) {
                logger.error("Password already exist for user, userId: {}, Access Denied", user.getUserId());
                throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "Password already exist for user");
            }

        } else if ( userLoginType.equals(UserLoginTypeEnum.FORGET_PASSWORD_LOGIN) ) {
            user = userRepository.findOneByEmail(providedUserName);
            if ( user != null && !user.isPasswordCreated() ) {
                logger.error("User has not completed initial login, userId: {}", user.getUserId());
                throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "User has not completed initial login");
            }
        }

        if ( user != null ) {
            updatePassword(providedPassword, user);
        } else {
            logger.error("User authentication is invalid. Please try again.");
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "User authentication is invalid. Please try again.");
        }
    }

    private void updatePassword(String providedPassword, User user) {
    }
}
