package com.ll.sbbmission4.sbb_question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {

    @GetMapping("/question/list")
    //@ResponseBody
    public String list() {
        return "question_list";
    }
}