package com.example.concurrentsample.repository;

import com.example.concurrentsample.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.concurrentsample.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByMemberIdForLock() {
        return Optional.ofNullable(
                jpaQueryFactory.select(member)
                        .from(member)
                        .where(member.memberId.goe(30))
                        .fetchFirst()
        );

    }
}
