package spock.test.context

import com.martinachov.bci.challenge.UserApiApplication
import com.martinachov.bci.challenge.adapter.in.web.UserRestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = UserApiApplication)
class LoadContextSpec extends Specification {

    @Autowired (required = false)
    private UserRestController userRestController

    def "when context is loaded then all expected beans are created"() {
        expect: "the UserRestController is created"
        userRestController
    }
}
