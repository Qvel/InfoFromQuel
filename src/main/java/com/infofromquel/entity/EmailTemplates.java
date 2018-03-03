package com.infofromquel.entity;



public enum EmailTemplates {
    ;

    private String template;

    EmailTemplates(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    String REGISTRATION_TEMPLATE = "<h3 style='color:red' >Hello World!</h3>" +
                                                 "<a href=0>Please follow this link to continue registration </a>";

    String REGISTRATION_SUBJECT = "Thanks for your registration on InfoQvel ";


}
