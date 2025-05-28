package com.FuelBee.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FuelBeeApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGODB_URI"));
		System.setProperty("spring.data.mongodb.database", dotenv.get("MONGODB_DATABASE"));

		System.setProperty("spring.security.user.name", dotenv.get("SPRING_SECURITY_USERNAME"));
		System.setProperty("spring.security.user.password", dotenv.get("SPRING_SECURITY_PASSWORD"));

		System.setProperty("cloudinary.cloud-name", dotenv.get("CLOUDINARY_CLOUD_NAME"));
		System.setProperty("cloudinary.api-key", dotenv.get("CLOUDINARY_API_KEY"));
		System.setProperty("cloudinary.api-secret", dotenv.get("CLOUDINARY_API_SECRET"));

		System.setProperty("twilio.account-sid", dotenv.get("TWILIO_ACCOUNT_SID"));
		System.setProperty("twilio.auth-token", dotenv.get("TWILIO_AUTH_TOKEN"));
		System.setProperty("twilio.phone-number", dotenv.get("TWILIO_PHONE_NUMBER"));
		System.setProperty("app.otp.expiry-minutes", dotenv.get("OTP_EXPIRY_MINUTES"));

		SpringApplication.run(FuelBeeApplication.class, args);
	}

}
