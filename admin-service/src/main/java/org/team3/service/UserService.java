package org.team3.service;

import org.springframework.stereotype.Service;
import org.team3.config.security.JwtTokenManager;
import org.team3.dto.request.DetailInformationRequestDto;
import org.team3.dto.request.UserProfileRequestDto;
import org.team3.dto.request.DoLoginRequestDto;
import org.team3.dto.request.EditProfileRequestDto;
import org.team3.dto.response.DetailInformationResponseDto;
import org.team3.exception.ErrorType;
import org.team3.exception.UserManagerException;
import org.team3.mapper.IUserMapper;
import org.team3.repository.IUserRepository;
import org.team3.repository.entity.User;
import org.team3.utility.ServiceManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository userRepository;
    
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    private final JwtTokenManager jwtTokenManager;

    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
    }


//    public void save(UserProfileRequestDto dto){
//
//        userRepository.save(IUserMapper.INSTANCE.toUser(dto));
//    }

    public Boolean updateProfile(EditProfileRequestDto dto, Long userId){

        Optional<User> optionalUser = userRepository.findOptionalById(userId);
        if(optionalUser.isEmpty()) return false;
        else {
            try {
                User user = User.builder()
                        .id(optionalUser.get().getId())
                        .photo(dto.getPhoto())
                        .name(optionalUser.get().getName())
                        .lastName(optionalUser.get().getLastName())
                        .secondName(optionalUser.get().getSecondName())
                        .secondLastname(optionalUser.get().getSecondLastname())
                        .gender(optionalUser.get().getGender())
                        .department(optionalUser.get().getDepartment())
                        .birthdate(optionalUser.get().getBirthdate())
                        .workStartDate(optionalUser.get().getWorkStartDate())
                        .phoneNumber(dto.getPhoneNumber())
                        .address(dto.getAddress())
                        .mail(optionalUser.get().getMail())
                        .build();
                save(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    

    public DetailInformationResponseDto profileDetail(DetailInformationRequestDto detailInformationRequestDto) {

        if(detailInformationRequestDto.getToken()==null)
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        try{
            Optional<Long> userId = jwtTokenManager.getUserId(detailInformationRequestDto.getToken());
            if(userId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
            User user = userRepository.findById(userId);
            DetailInformationResponseDto dto = IUserMapper.INSTANCE.toDetail(user);
            return dto;
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        
    }

    public Optional<User> doLogin(DoLoginRequestDto dto){
        String encodedPassword = jwtTokenManager.encryptPassword(dto.getPassword());
        return userRepository.findOptionalByMailIgnoreCaseAndPassword(dto.getMail(),
                encodedPassword);
    }
    public void saveNewCompanyManager(UserProfileRequestDto dto) {
        User user = IUserMapper.INSTANCE.toCompanyManager(dto);
        save(user);
    }
    public String forgotPassword(String email) {

        Optional<User> userOptional = userRepository.findByMail(email);

        if (userOptional.isEmpty()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        userRepository.save(user);

        return user.getToken();
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    public String resetPassword(String token, String password) {

        Optional<User> managerOptional = Optional
                .ofNullable(userRepository.findByToken(token));

        if (managerOptional.isEmpty()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = managerOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        User user = managerOptional.get();
        String encodedPassword = jwtTokenManager.encryptPassword(password);
        user.setPassword(encodedPassword);
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return "Your password successfully updated.";
    }
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}
