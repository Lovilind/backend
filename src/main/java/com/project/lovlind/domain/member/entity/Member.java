package com.project.lovlind.domain.member.entity;

import com.project.lovlind.domain.auditing.BaseTime;
import com.project.lovlind.domain.member.enums.Gender;
import com.project.lovlind.domain.member.enums.MBTI;
import com.project.lovlind.domain.member.enums.UserGrade;
import com.project.lovlind.domain.member.enums.UserStatus;
import com.project.lovlind.domain.member.vo.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "members_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Embedded private Address address;

  private LocalDate birthDate;

  // ENUM 변경 여부 체크 필요
  private Boolean isAdult;

  private String phone;

  @Enumerated(value = EnumType.STRING)
  private UserGrade userGrade;

  @Enumerated(value = EnumType.STRING)
  private Gender gender;

  @Enumerated(value = EnumType.STRING)
  private UserStatus userStatus;

  @Enumerated(value = EnumType.STRING)
  private MBTI mbti;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "member_authentication_id")
  MemberAuthentication memberAuthentication;

  @OneToOne(cascade = CascadeType.ALL)
  MemberProfile profile;

  @OneToMany(mappedBy = "member", orphanRemoval = true)
  List<MemberHobby> memberHobbyList = new ArrayList<>();

  public Member(Long memberId) {
    this.id = memberId;
  }
}
