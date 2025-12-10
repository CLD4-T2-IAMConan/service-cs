package com.company.template.faq.dto;

public class FaqRequest {

    private String category;
    private String question;
    private String answer;
    private Integer sortOrder;

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }
}