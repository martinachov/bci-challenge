package spock.test.usecase

import com.martinachov.bci.challenge.application.domain.Phone
import com.martinachov.bci.challenge.application.domain.User
import com.martinachov.bci.challenge.application.domain.UserRegistrationData
import com.martinachov.bci.challenge.application.exception.UserNotFoundException
import com.martinachov.bci.challenge.application.port.output.UserRepository
import com.martinachov.bci.challenge.application.usecase.RetrieveUserUseCase
import com.martinachov.bci.challenge.application.usecase.SignUpUserUseCase
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class RetrieveUserUseCaseSpec extends Specification{

    @Shared RetrieveUserUseCase retrieveUserUseCase
    private UserRepository userRepository = Mock(UserRepository)

    def setup() {
        retrieveUserUseCase = new RetrieveUserUseCase(userRepository)
    }

    @Unroll
    def "getUserByEmail() should return user with test@mail"() {

        given:
        def phones = new ArrayList<Phone>()
        def userData = new UserRegistrationData("martin","test@mail.com","password",phones)
        def retrieved_user = new User(UUID.randomUUID(),"martin","test@mail.com","password",phones,"token",true, LocalDateTime.now(),LocalDateTime.now())
        retrieveUserUseCase.userRepository.findByEmail(userData.email) >> Optional.of(retrieved_user)

        when:
        def result = retrieveUserUseCase.getUserByEmail("test@mail.com")

        then:
        result.email==userData.email

    }

    @Unroll
    def "getUserById should return user with UUID"() {
        given:
        def phones = new ArrayList<Phone>()
        def userData = new UserRegistrationData("martin","test@mail.com","password",phones)
        def userId = UUID.randomUUID()
        def retrieved_user = new User(userId,"martin","test@mail.com","password",phones,"token",true, LocalDateTime.now(),LocalDateTime.now())
        retrieveUserUseCase.userRepository.findById(userId) >> Optional.of(retrieved_user)

        when:
        def result = retrieveUserUseCase.getUserById(userId)

        then:
        result.email==userData.email
    }

    @Unroll
    def "getUserByEmail() should retrieve UserNotFoundException when user not exist"() {

        given:
        def phones = new ArrayList<Phone>()
        def userData = new UserRegistrationData("otro_usuario","otro_email@mail.com","password",phones)
        retrieveUserUseCase.userRepository.findByEmail(userData.email) >> Optional.empty()

        when:
        def result = retrieveUserUseCase.getUserByEmail(userData.email)

        then:
        thrown(UserNotFoundException)

    }
}
