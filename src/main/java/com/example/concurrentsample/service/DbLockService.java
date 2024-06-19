package com.example.concurrentsample.service;

import com.example.concurrentsample.dto.MemberDto;
import com.example.concurrentsample.entity.Member;
import com.example.concurrentsample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DbLockService {

    private final MemberRepository memberRepository;


    @Transactional
    public void post(MemberDto memberDto) {
        Optional<Member> member = memberRepository.findByMemberIdForLock();

        if(member.isPresent()) {
           return;
        }
        Member inputMember = memberDto.toModel();
        memberRepository.save(inputMember);
    }

}
