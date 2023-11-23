package com.ll.sbbmission4.sbb_answer;

import com.ll.sbbmission4.sbb_question.Question;
import com.ll.sbbmission4.user.Site_User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Site_User author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<Site_User> voter;
}