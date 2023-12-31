package com.ll.sbbmission4.sbb_question;

import com.ll.sbbmission4.sbb_answer.Answer;
import com.ll.sbbmission4.user.Site_User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    private LocalDateTime createDate;

    @ManyToOne
    private Site_User author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<Site_User> voter;
}
