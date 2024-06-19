package com.example.concurrentsample.service;

import com.example.concurrentsample.dto.MemberDto;
import com.example.concurrentsample.entity.Member;
import com.example.concurrentsample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NormalService {
    private final MemberRepository memberRepository;

    @Transactional

    public void post(MemberDto memberDto) {
        List<Member> all = memberRepository.findAll();

        if(all.size() >= 30) {
            return;
        }
        memberRepository.save(memberDto.toModel());
    }
}
