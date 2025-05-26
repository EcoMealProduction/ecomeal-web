package out.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import model.shared.Email;
import model.shared.PhoneNumber;
import model.shared.password.Password;
import model.user.UserAccount;
import out.entity.user.UserAccountEntity;

@Mapper(componentModel = "spring")
public interface UserAccountEntityMapper {

    @Mapping(target = "email", source = "email", qualifiedByName = "emailToString")
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "phoneNumberToString")
    @Mapping(target = "password", source = "password", qualifiedByName = "passwordToString")
    UserAccountEntity toUserAccountEntity(UserAccount userAccount);

     @Mapping(target = "id", ignore = true)
     @Mapping(target = "email", source = "email", qualifiedByName = "toEmail")
     @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "toPhoneNumber")
     @Mapping(target = "password", source = "password", qualifiedByName = "toPassword")
     @Mapping(target = "role", source = "role")
     UserAccount toUserAccount(UserAccountEntity userAccountEntity);

    @Named("toEmail")
    default Email toEmail(String value) {
        return new Email(value);
    }

    @Named("toPhoneNumber")
    default PhoneNumber toPhoneNumber(String value) {
        return new PhoneNumber(value);
    }

    @Named("toPassword")
    default Password toPassword(String value) {
        return new Password(value);
    }

    @Named("emailToString")
    default String emailToString(Email email) {
        return email.value();
    }

    @Named("phoneNumberToString")
    default String phoneNumberToString(PhoneNumber phoneNumber) {
        return phoneNumber.value();
    }

    @Named("passwordToString")
    default String passwordToString(Password password) {
        return password.value();
    }
}
