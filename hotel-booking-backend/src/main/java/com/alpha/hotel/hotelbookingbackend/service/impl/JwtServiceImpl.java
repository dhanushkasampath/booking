package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.entity.User;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.JwtService;
import com.alpha.hotel.hotelbookingbackend.service.UserService;
import com.alpha.hotel.hotelbookingbackend.util.Constants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JwtServiceImpl implements JwtService {
    private final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
    @Value("${security.jwt.sign.key}")
    private String jwtSignKey;
    @Value( "${user.default.password}" )
    private String defaultUserPassword;

    @Autowired
    private UserService userService;

    @Override
    public boolean validateToken(String token) {
        if (StringUtils.hasText(token)) {
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
}
