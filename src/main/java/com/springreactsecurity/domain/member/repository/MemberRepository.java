package com.springreactsecurity.domain.member.repository;

import com.springreactsecurity.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);
    Optional<Member> findByNameAndEmail(String name, String email);
    Optional<Member> findByUserIdAndNameAndEmail(String userId, String name, String email);

}
