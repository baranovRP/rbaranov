package ru.job4j.userapp;

import java.util.Optional;

/**
 * String template to represent User record.
 */
public class UserStringTemplate {

    /**
     * Fill user template
     *
     * @param user       user
     * @param actionPath path
     * @param button     label
     * @return string template
     */
    public String fillUserTemplate(final User user,
                                   final String actionPath, final String button) {
        int id = user.getId();
        String nameLabelId = String.format("name-%d", id);
        String loginLabelId = String.format("login-%d", id);
        String emailLabelId = String.format("email-%d", id);
        String idLabelId = String.format("id-%d", id);

        return String.format(
            "<form action='%s' method='post'>"
                + "<label for='%s'>Name: </label>"
                + "<input type='text' name='name' id='%s' value='%s'>"
                + "<label for='%s'>Login: </label>"
                + "<input type='text' name='login' id='%s' value='%s'>"
                + "<label for='%s'>Email: </label>"
                + "<input type='text' name='email' id='%s' value='%s'>"
                + "<input type='hidden' id='%s' name='id' value='%d'>"
                + "<input type='submit' value='%s'>"
                + "</form>",
            actionPath, nameLabelId, nameLabelId,
            Optional.ofNullable(user.getName()).orElse(""), loginLabelId,
            loginLabelId, Optional.ofNullable(user.getLogin()).orElse(""),
            emailLabelId, emailLabelId,
            Optional.ofNullable(user.getEmail()).orElse(""),
            idLabelId, user.getId(), button);
    }
}
