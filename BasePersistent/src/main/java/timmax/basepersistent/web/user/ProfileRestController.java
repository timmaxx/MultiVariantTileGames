package timmax.basepersistent.web.user;

import org.springframework.stereotype.Controller;
import timmax.basepersistent.model.User;

import static timmax.basepersistent.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}