package out.mapper;

import model.client.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import out.entity.client.ClientEntity;

@Mapper(componentModel = "spring", uses = {
        UserAccountEntityMapper.class,
        BankingDetailsEntityMapper.class})
public interface ClientEntityMapper {

    @Mapping(target = "cart", ignore = true)
    ClientEntity toClientEntity(Client client);

    @Mapping(target = "pickUpRadiusKm", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    Client toClient(ClientEntity client);
}
