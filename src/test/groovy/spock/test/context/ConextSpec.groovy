package spock.test.context

import com.martinachov.bci.challenge.UserApiApplication
import com.martinachov.bci.challenge.application.usecase.SignUpUserUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = UserApiApplication)
class ContextSpec extends Specification{

    @Autowired
    ApplicationContext context

    def "context is as expected"() {
        expect:
        context
        context.getBeansOfType(SignUpUserUseCase)
    }

}
