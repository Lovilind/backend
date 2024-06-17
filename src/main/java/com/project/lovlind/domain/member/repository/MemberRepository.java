package com.project.lovlind.domain.member.repository;

import com.project.lovlind.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);
  void deleteById(Long id);

  Member getMemberById(long id);
}
