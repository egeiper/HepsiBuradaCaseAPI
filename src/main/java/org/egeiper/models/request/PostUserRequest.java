package org.egeiper.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
