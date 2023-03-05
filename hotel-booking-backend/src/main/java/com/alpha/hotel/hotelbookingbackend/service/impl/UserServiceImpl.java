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
import com.alpha.hotel.hotelbookingbackend.service.EmailService;
import com.alpha.hotel.hotelbookingbackend.service.EncryptDecryptService;
import com.alpha.hotel.hotelbookingbackend.service.UserService;
import com.alpha.hotel.hotelbookingbackend.util.EmailConstants;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Value("${user.password.encryption.key}")
    private String secretKey;
    @Value( "${invitation.email.forget.password.url}" )
    private String forgetPasswordLink;
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
    @Autowired
    private EmailService emailService;

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

        String providedEncryptedPassword = userLoginRequestDto.getPassword();
//        String password = encryptDecryptService.encrypt(providedEncryptedPassword, secretKey);
        String userNameProvided = userLoginRequestDto.getUsername();
        User user = userRepository.findByUserName(userNameProvided);

        if (user == null) {
            logger.error("User Authentication Failed");
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "User Authentication Failed");
        }

        String persistPassword = user.getPassword();//this is already an encrypted one
        if (Boolean.FALSE.equals(passwordEncoder.matches(providedEncryptedPassword, persistPassword))) {
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
        String providedEncryptedPassword = userLoginRequestDto.getPassword();//should be an encoded one
        String providedUserName = userLoginRequestDto.getUsername();

        if ( userLoginType.equals(UserLoginTypeEnum.INITIAL_LOGIN) ) {

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
            validateAndUpdatePassword(providedEncryptedPassword, user);
        } else {
            logger.error("User authentication is invalid. Please try again.");
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "User authentication is invalid. Please try again.");
        }
    }

    @Override
    public void forgetPassword(String email) throws HotelBookingException {
        User user = userRepository.findOneByEmail(email);
        if ( user != null ) {
            String content;
            if ( user.isPasswordCreated() ) {
                String token = userJwtTokenCreator.generateJwtToken(user, JwtTokenTypeEnum.INVITATION_TOKEN);
                String resetLink = forgetPasswordLink.concat(encodeValue(token));
                content = String.format(EmailConstants.INVITATION_EMAIL_CONTENT, resetLink);
            } else {
                content = EmailConstants.INVITATION_EMAIL_CONTACT_ADMIN_CONTENT;
            }
            emailService.sendEmail(email, content, "Forgot password");
        }
        else {
            logger.error("User with email:{} not found.", email);
            throw new HotelBookingException(HttpStatus.BAD_REQUEST, String.format("User with email:%s not found.", email));
        }
    }

    private String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private void validateAndUpdatePassword(String providedEncryptedPassword, User user) throws HotelBookingException {
        try {
            encryptDecryptService.decrypt(providedEncryptedPassword, secretKey);
        } catch (HotelBookingException e) {
            logger.error("Password entered is invalid. Please enter a valid one:{}", providedEncryptedPassword);
            throw new HotelBookingException(HttpStatus.BAD_REQUEST, "Password entered is invalid. Please enter a valid one");
        }
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        User userToBeUpdated = null;
        if(optionalUser.isPresent()){
            userToBeUpdated = optionalUser.get();
            userToBeUpdated.setPassword(providedEncryptedPassword);
            userToBeUpdated.setPasswordCreated(true);
            userRepository.save(userToBeUpdated);
        }else{
            logger.error("Password update failed.User not found for Id:{}", user.getUserId());
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Password update failed. User not found");
        }
    }
}
