package com.project.lovlind.domain.member.exception;

import com.project.lovlind.conmon.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 조회 실패"),
  USER_EXIST(HttpStatus.CONFLICT, "회원이 이미 존재"),
  USER_NOT_SAME(HttpStatus.BAD_REQUEST, "사용자 정보가 동일하지 않습니다"),
  EMAIL_OCCUPY_CODE_FAIL(HttpStatus.BAD_REQUEST, "이메일 소유 확인이 되지 않았습니다.");

  private final HttpStatus httpStatus;
  private final String message;
}
