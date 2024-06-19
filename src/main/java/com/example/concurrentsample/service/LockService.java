package com.example.concurrentsample.service;

import com.example.concurrentsample.dto.MemberDto;
import com.example.concurrentsample.entity.Member;
import com.example.concurrentsample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LockService {
    private final MemberRepository memberRepository;

    @Transactional
    public void post(MemberDto memberDto) {
        Lock lock = new ReentrantLock();

        try {
            lock.lock();
            List<Member> all = memberRepository.findAll();

            if(all.size() >= 30) {
                return;
            }
        } finally {
            lock.unlock();
        }

        memberRepository.save(memberDto.toModel());
    }
}
