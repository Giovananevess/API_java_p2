package trevo.maquinas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.maquinas.api.model.Address;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Boolean existsByStreet(String street);
}
