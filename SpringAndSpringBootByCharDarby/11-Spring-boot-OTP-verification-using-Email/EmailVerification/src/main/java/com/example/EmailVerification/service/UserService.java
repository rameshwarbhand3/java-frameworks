package com.example.EmailVerification.service;

import com.example.EmailVerification.dto.LoginDto;
import com.example.EmailVerification.dto.RegisterDto;
import com.example.EmailVerification.entity.User;
import com.example.EmailVerification.repository.UserRepository;
import com.example.EmailVerification.util.EmailUtil;
import com.example.EmailVerification.util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import com.example.EmailVerification.entity.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisterDto registerDto;

    public String register(RegisterDto registerDto) {
        String otp = otpUtil.generator();
        try {
            emailUtil.sendOtpEmail(registerDto.getEmail(),otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "User Registration successful";
    }


    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setActive(true);
            userRepository.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generator();
        try {
            emailUtil.sendOtpEmail(email,otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent...Please verify account within 1 minute";
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
        if(!user.getPassword().equals(loginDto.getPassWord())){
            return "PassWord is inCorrect";
        }else if(!user.isActive()){
            return "Your account is not verified.";
        }else{
            return "Login Successful";
        }
    }
}
