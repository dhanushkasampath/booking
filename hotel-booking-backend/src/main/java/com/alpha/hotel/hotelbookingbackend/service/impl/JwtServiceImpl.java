package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.entity.User;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.JwtService;
import com.alpha.hotel.hotelbookingbackend.service.UserService;
import com.alpha.hotel.hotelbookingbackend.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    private final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
    @Value("${security.jwt.sign.key}")
    private String jwtSignKey;
    @Value( "${user.default.password}" )
    private String defaultUserPassword;
    TypeReference<Map<String, Object>> mapType = new TypeReference< Map < String, Object > >() {
    };

    @Autowired
    private UserService userService;

    @Override
    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            logger.error("Jwt not found in header");
            return false;
        }

        try {
            Jwts.parser().setSigningKey(jwtSignKey.getBytes()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    @Override
    public boolean validateTokenWithOneTimeUse(String token) throws HotelBookingException {
        if ( validateToken(token) ) {
            Claims body = Jwts.parser().setSigningKey(jwtSignKey.getBytes()).parseClaimsJws(token).getBody();
            Long userId = (( Number ) body.get(Constants.USER_ID_KEY)).longValue();
            int oneTimeAuthKey = ( int ) body.get(Constants.USER_ONE_TIME_AUTH_KEY);
            User user = userService.findOne(userId);
            String pw = user.getPassword() == null ? defaultUserPassword : user.getPassword();
            if ( ((pw + "_" + user.getCreatedDate().toString()).hashCode()) == (oneTimeAuthKey) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> decodeJwt(String jwtToken) throws HotelBookingException {
        try {
            String payloadValue = new String(Base64.decodeBase64(jwtToken.split("\\.")[ 1 ]));//jwt has 3 sections. among those 3, 2nd one has the data we embed
            Map < String, Object > map = objectMapper.readValue(payloadValue, mapType);

            if ( map != null ) {
                logger.debug("Received JWT is a valid token");
                return map;
            } else {
                throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot find a user for sent JWT");
            }
        }
        catch ( Exception e ) {
            logger.error(e.getMessage());
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "Not a valid jwt key");
        }
    }

    @Override
    public String extractUserPropertyFromJwtToken(String requestUserProperty, HttpServletRequest request) throws HotelBookingException {

        String jwt = extractJwtTokenFromHeader(request);
        String userPropertyValue;

        switch ( requestUserProperty ) {
            case Constants.USER_ID_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_ID_KEY).toString();
                break;
            case Constants.USER_NAME_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_NAME_KEY).toString();
                break;
            case Constants.USER_FIRST_NAME_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_FIRST_NAME_KEY).toString();
                break;
            case Constants.USER_LAST_NAME_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_LAST_NAME_KEY).toString();
                break;
            case Constants.USER_EMAIL_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_EMAIL_KEY).toString();
                break;
            case Constants.USER_DOB_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_DOB_KEY).toString();
                break;
            case Constants.USER_CONTACT_NO_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_CONTACT_NO_KEY).toString();
                break;
            case Constants.USER_DISTRICT_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_DISTRICT_KEY).toString();
                break;
            case Constants.USER_PROVINCE_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_PROVINCE_KEY).toString();
                break;
            case Constants.USER_TOWN_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_TOWN_KEY).toString();
                break;
            case Constants.USER_ONE_TIME_AUTH_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_ONE_TIME_AUTH_KEY).toString();
            case Constants.USER_TYPE_KEY:
                userPropertyValue = this.decodeJwt(jwt).get(Constants.USER_TYPE_KEY).toString();
                break;
            default:
                logger.error("User property:{} is not included in the Jwt token", requestUserProperty);
                throw new HotelBookingException(HttpStatus.BAD_REQUEST, "User property not included in jwt");
        }

        return userPropertyValue;
    }

    @Override
    public String extractJwtTokenFromHeader(HttpServletRequest request) throws HotelBookingException {
        if ( request == null || request.getHeader(Constants.AUTHORIZATION_HEADER) == null) {
            logger.error("Request header not found.");
            throw new HotelBookingException(HttpStatus.UNAUTHORIZED, "Request header not found.");
        }

        return request.getHeader(Constants.AUTHORIZATION_HEADER).replace("Bearer ", "");
    }
}
