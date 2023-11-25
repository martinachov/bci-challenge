package spock.test.usecase

import com.martinachov.bci.challenge.application.domain.Phone
import com.martinachov.bci.challenge.application.domain.User
import com.martinachov.bci.challenge.application.domain.UserRegistrationData
import com.martinachov.bci.challenge.application.exception.UserAlreadyExistException
import com.martinachov.bci.challenge.application.port.output.UserRepository
import com.martinachov.bci.challenge.application.usecase.SignUpUserUseCase
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class SignUpUserUseCaseSpec extends Specification{

    @Shared SignUpUserUseCase signUpUserUseCase
    private UserRepository userRepository = Mock(UserRepository)
    private PasswordEncoder passwordEncoder = Mock(PasswordEncoder)

    def setup() {
        signUpUserUseCase = new SignUpUserUseCase(userRepository,passwordEncoder)
    }

    @Unroll
    def "signUp() should create a new user"() {

        given:
        def phones = new ArrayList<Phone>()
        def userData = new UserRegistrationData("martin","test@mail.com","password",phones)
        def newUser = new User(UUID.randomUUID(),"martin","test@mail.com","password",phones,"token",true, LocalDateTime.now(),LocalDateTime.now())
        def authentication = Mock(Authentication)
        authentication.isAuthenticated() >> true
        SecurityContextHolder.getContext().setAuthentication(authentication)

        when:
        def result = signUpUserUseCase.signUp(userData)

        then:
        1 * signUpUserUseCase.userRepository.findByEmail(userData.email) >> Optional.empty()
        1 * signUpUserUseCase.userRepository.save(_) >> newUser
        result.email==newUser.email

    }

    @Unroll
    def "signUp() should throw UserAlreadyExistException if email exist"() {

        given:
        def phones = new ArrayList<Phone>()
        def userData = new UserRegistrationData("martin","test@mail.com","password",phones)
        def existingUser = new User(UUID.randomUUID(),"martin","test@mail.com","password",phones,"token",true, LocalDateTime.now(),LocalDateTime.now())
        def authentication = Mock(Authentication)
        authentication.isAuthenticated() >> true
        SecurityContextHolder.getContext().setAuthentication(authentication)
        signUpUserUseCase.userRepository.findByEmail(userData.email) >> Optional.of(existingUser)

        when:
        def result = signUpUserUseCase.signUp(userData)

        then:
        thrown(UserAlreadyExistException)
    }
}

