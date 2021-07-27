package pl.edu.wszib.wallet.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.wallet.model.User;

@Component
@SessionScope
@Getter
@Setter
public class SessionObject {
    private User loggedUser = null;
}
