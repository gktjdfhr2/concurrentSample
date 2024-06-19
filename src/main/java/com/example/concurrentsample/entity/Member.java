package com.example.concurrentsample.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "MEMBER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private int number;
}