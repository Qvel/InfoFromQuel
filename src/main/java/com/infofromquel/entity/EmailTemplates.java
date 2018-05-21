package com.infofromquel.entity;

public enum EmailTemplates {

    REGISTRATION_TEMPLATE ("<h3 style='color:red' >Hello World!</h3>" +
                "<a href=0>Please follow this link to continue registration </a>"),
    REGISTRATION_SUBJECT("Thanks for your registration on InfoQvel "),
    ;


    private final String template;

    EmailTemplates(String template) {
        this.template = template;
    }



    public String getTemplate() {
        return template;
    }

}
