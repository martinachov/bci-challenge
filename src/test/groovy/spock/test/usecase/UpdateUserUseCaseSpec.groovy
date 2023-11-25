package spock.test.usecase

import com.martinachov.bci.challenge.UserApiApplication
import com.martinachov.bci.challenge.application.domain.Phone
import com.martinachov.bci.challenge.application.domain.User
import com.martinachov.bci.challenge.application.domain.UserRegistrationData
import com.martinachov.bci.challenge.application.port.output.UserRepository
import com.martinachov.bci.challenge.application.usecase.SignUpUserUseCase
import com.martinachov.bci.challenge.application.usecase.UpdateUserUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

@SpringBootTest(classes = UserApiApplication)
class UpdateUserUseCaseSpec extends Specification{

    @Autowired
    private UpdateUserUseCase updateUserUseCase

    @Autowired
    private UserRepository userRepository

    @Autowired
    private PasswordEncoder passwordEncoder

    def "updateUserData() should update user data"() {

        given: "An existing user"
        def phone1 = Phone.builder().build()
        def phone2 = Phone.builder().build()
        def list_2_phones = new ArrayList<Phone>()
        list_2_phones.add(phone1)
        list_2_phones.add(phone2)
        UUID id = UUID.randomUUID()
        User user = new User()
        user.setEmail("johndoe@example.com")
        user.setName("John Doe")
        user.setPassword("password")
        user.setPhones(list_2_phones)
        User newUser = userRepository.save(user)

        and: "New user data"
        String newEmail = "janedoe@example.com"
        String newName = "Jane Doe"
        String newPassword = "newpassword"
        UserRegistrationData newUserData = new UserRegistrationData()
        newUserData.setEmail(newEmail)
        newUserData.setName(newName)
        newUserData.setPassword(newPassword)
        newUserData.setPhones(list_2_phones)

        when: "User data is updated"
        User updatedUser = updateUserUseCase.updateUserData(newUser.id, newUserData)

        then: "Updated user data is persisted"
        updatedUser.getEmail() == newEmail
        updatedUser.getName() == newName

    }
}
