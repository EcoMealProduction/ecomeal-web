package out.mapper;

import model.payment.BankingDetails;
import org.mapstruct.Mapper;
import out.entity.payment.BankingDetailsEntity;

@Mapper(componentModel = "spring")
public interface BankingDetailsEntityMapper {
    BankingDetailsEntity toBankingDetailsEntity(BankingDetails bankingDetails);
}
