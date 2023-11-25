package spock.test.usecase

import com.martinachov.bci.challenge.application.domain.Phone
import com.martinachov.bci.challenge.application.domain.User
import com.martinachov.bci.challenge.application.domain.UserRegistrationData
import com.martinachov.bci.challenge.application.exception.UserNotFoundException
import com.martinachov.bci.challenge.application.port.output.UserRepository
import com.martinachov.bci.challenge.application.usecase.DeleteUserUseCase
import com.martinachov.bci.challenge.application.usecase.SignUpUserUseCase
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class DeleteUserUseCaseSpec extends Specification{

    @Shared DeleteUserUseCase deleteUserUseCase
    private UserRepository userRepository = Mock(UserRepository)

    def setup() {
        deleteUserUseCase = new DeleteUserUseCase(userRepository)
    }

    @Unroll
    def "should throw UserNotFoundException when try to delete an unexisting user"() {

        given:
        def userId = UUID.randomUUID()
        deleteUserUseCase.userRepository.findById(userId) >> Optional.empty()

        when:
        deleteUserUseCase.deleteUser(userId)

        then:
        thrown(UserNotFoundException)

    }


}
