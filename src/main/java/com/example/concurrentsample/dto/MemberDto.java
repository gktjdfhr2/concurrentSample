package com.example.concurrentsample.dto;

import com.example.concurrentsample.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String name;
    private int number;

    public Member toModel() {
        return Member.builder()
                .number(number)
                .build();
    }
}
