package com.FuelBee.backend.util;

import com.FuelBee.backend.model.Entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
public class OTPUtil {
    public static String generateOTP(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static Date getExpiryTime(){
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MINUTE, 5);
        return calender.getTime();
    }

    public void generateAndSendOTP(User user) {
    }
}
