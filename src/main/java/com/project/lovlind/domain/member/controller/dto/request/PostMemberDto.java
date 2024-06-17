package com.project.lovlind.domain.member.controller.dto.request;

import com.project.lovlind.domain.member.enums.LoginType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostMemberDto {
  private String email;
  private String password;
  private String code;
  private LoginType loginType = LoginType.O01;


//  private String nickName;
//  private String address;
//  private String birthday;
//  private String image;
//

}
