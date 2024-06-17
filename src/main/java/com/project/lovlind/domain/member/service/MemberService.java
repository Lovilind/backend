package com.project.lovlind.domain.member.service;

import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.conmon.redis.repository.RedisRepository;
import com.project.lovlind.conmon.utils.number.NumberUtils;
import com.project.lovlind.domain.common.NotificationService;
import com.project.lovlind.domain.member.controller.dto.request.PostMemberDto;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.member.exception.MemberExceptionCode;
import com.project.lovlind.domain.member.repository.MemberRepository;
import io.lettuce.core.RedisClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository repository;
  private final NotificationService notificationService;
  private final RedisRepository redisRepository;
  private final PasswordEncoder encoder;


  public Long save(PostMemberDto dto) {

    if (! dto.getPassword().equals(dto.getRePassword())) {
      throw new BusinessLogicException(MemberExceptionCode.PASSWORD_NOT_SAME);
    }

    if (existByEmail(dto.getEmail())) {
      throw new BusinessLogicException(MemberExceptionCode.USER_EXIST);
    }

    if (! isSuccessVerifyEmail(dto.getEmail(), dto.getCode())) {
      throw new BusinessLogicException(MemberExceptionCode.EMAIL_OCCUPY_CODE_FAIL);
    }


    String encodedPassword = encoder.encode(dto.getPassword());
    Member requestMember = new Member(dto.getEmail(), encodedPassword);
    return repository.save(requestMember).getId();
  }

  public void delete(Long id) {

      if (notExistById(id)) {
          throw new BusinessLogicException(MemberExceptionCode.USER_NOT_FOUND);
      }

      repository.deleteById(id);
  }

  public Member getMemberById(Long id) {
      try {
          return repository.getMemberById(id);
      } catch (Exception e) {
          throw new BusinessLogicException(MemberExceptionCode.USER_NOT_FOUND);
      }
  }

  public boolean existByEmail(String email) {
    return repository.findByEmail(email).isPresent();
  }

  public boolean notExistByEmail(String email) {
    return !existByEmail(email);
  }


  public boolean existById(Long id) {
    return repository.findById(id).isPresent();
  }

  public boolean notExistById(Long id) {
    return !existById(id);
  }


  public Boolean sendEmailAndVerifyNumber(String email) {

    String value = NumberUtils.makeRandomNumberString(6);
    int availableMin = 5;

    redisRepository.save("SIGN:EMAIL:" + email, value, availableMin);
    String message = "[LOVIND] 이메일 인증 정보 " + "1234" + " 입니다.";

    return notificationService.sendEmail("이메일 인증", message, email);
  }

  public boolean isSuccessVerifyEmail(String email, String verificationCode) {

    // 테스트용
    if ("010101".equals(verificationCode)) {
      return true;
    }

    String value = redisRepository.findByKey("SIGN:EMAIL:" + email);
    if (Objects.nonNull(value) && value.equals(verificationCode)) {
      return true;
    }

    return false;
  }
}
