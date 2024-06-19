package com.example.concurrentsample.service;

import com.example.concurrentsample.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class DbLockTest {

    @Autowired
    private SyncronizeService syncronizeService;

    @Autowired
    private NormalService normalService;

    @Autowired
    private LockService lockService;

    @Autowired
    private DbLockService dbLockService;

    @Test
    public void testConcurrentReservation() throws InterruptedException {
        int totalThreads = 100;
        int perThread = 1;
        CountDownLatch latch = new CountDownLatch(totalThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);

        for (int i = 0; i < totalThreads; i++) {
            final int finalI = i;
            executorService.submit(() -> {
                try {
                    for(int j = 0; j < perThread; j++) {
                        MemberDto memberDto = MemberDto.builder()
                                .number(finalI)
                                .build();
                        dbLockService.post(memberDto);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }

}