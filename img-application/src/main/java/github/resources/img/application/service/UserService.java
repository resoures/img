package github.resources.img.application.service;

import github.resources.img.auth.AuthException;

public interface UserService {

    void authUser(String userName, String password) throws AuthException;

}
