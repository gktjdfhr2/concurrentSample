package com.example.concurrentsample.repository;

import com.example.concurrentsample.entity.Member;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryCustom {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Member> findByMemberIdForLock();
}
