package com.alpha.hotel.hotelbookingbackend.dto;

import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeDto implements Serializable {
    private static final long serialVersionUID = -4588237030519047527L;
    private Long id;
    private UserTypeEnum userType;
}
