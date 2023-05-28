package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.dto.SmsAccessTokenGenerateRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.SmsAccessTokenGenerateResponseDto;
import com.alpha.hotel.hotelbookingbackend.dto.SmsSendRequestDto;
import com.alpha.hotel.hotelbookingbackend.exception.ServiceCallException;
import com.alpha.hotel.hotelbookingbackend.service.MessageService;
import com.alpha.hotel.hotelbookingbackend.service.api.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private HttpService httpService;

    @Value("${service.provider.mask}")
    private String mask;

    @Value("${service.provider.campaign.name}")
    private String campaignName;

    @Value("${generate.sms.access.token.username}")
    private String userName;

    @Value("${generate.sms.access.token.password}")
    private String password;

    @Value("${service.provider.url.for.sms.access.token}")
    private String urlForSmsAccessToken;

    @Value("${service.provider.url.for.send.sms}")
    private String urlForSendSms;

    @Override
    public void sendSms(String phoneNumber, String content) throws ServiceCallException, JsonProcessingException {
        String accessToken = generateAccessToken();
        logger.info("accessToken:{}", accessToken);
        if(!"".equals(accessToken)){
            notifyUser(phoneNumber, content, accessToken);
        }else{
            logger.error("Failed to send the OTP to:{}", phoneNumber);
        }
    }

    private void notifyUser(String phoneNumber, String content, String accessToken) throws ServiceCallException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-API-VERSION", "v1");
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        SmsSendRequestDto smsSendRequestDto = new SmsSendRequestDto();
        smsSendRequestDto.setMask(mask);
        smsSendRequestDto.setContent(content);
        smsSendRequestDto.setNumbers(phoneNumber);
        smsSendRequestDto.setCampaignName(campaignName);

        ResponseEntity<String> responseEntity = httpService
                .post(urlForSendSms, httpHeaders, smsSendRequestDto);

        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            String response = responseEntity.getBody();
            logger.info("OTP successfully sent to {}|{}", phoneNumber, response);
        } else {
            logger.error("Failed to send OTP to {}", phoneNumber);
        }
    }

    private String generateAccessToken() throws ServiceCallException, JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-API-VERSION", "v1");
        httpHeaders.add("Content-Type", "application/json");

        SmsAccessTokenGenerateRequestDto smsAccessTokenGenerateRequestDto = new SmsAccessTokenGenerateRequestDto();
        smsAccessTokenGenerateRequestDto.setUsername(userName);
        smsAccessTokenGenerateRequestDto.setPassword(password);

        ResponseEntity<String> responseEntity = httpService
                .post(urlForSmsAccessToken, httpHeaders, smsAccessTokenGenerateRequestDto);

        if(HttpStatus.OK.equals(responseEntity.getStatusCode())){
            String response = responseEntity.getBody();
            logger.info("Access Token Generated to send SMS:{}", response);
            SmsAccessTokenGenerateResponseDto smsAccessTokenGenerateResponseDto = objectMapper.readValue(response, SmsAccessTokenGenerateResponseDto.class);
            return smsAccessTokenGenerateResponseDto.getAccessToken();
        } else {
            logger.error("Access token has not been generated");
        }
        return "";
    }
}
