package trevo.maquinas.api.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.maquinas.api.dto.AddressDadosDTO;
import trevo.maquinas.api.model.Address;
import trevo.maquinas.api.repository.AddressRepository;
import trevo.maquinas.api.response.ResponseModelMessage;
import trevo.maquinas.api.response.ResponseModelObject;

import java.util.List;
import java.util.UUID;

@Service
public class AddresService {
    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<?> register(@RequestBody @Valid AddressDadosDTO dto) {
        Address address = new Address(dto);
        addressRepository.save(address);
        return new ResponseEntity<>(new ResponseModelObject("Endereço cadastrado", address), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()){
            return new ResponseEntity<>(new ResponseModelMessage("Lista de endereço está vazia"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de produtos", addresses), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(UUID id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelMessage("Produto foi deletado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMessage("Produto não encontrado"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid AddressDadosDTO dto) {
        Address address = addressRepository.findById(id).orElse(null);
        if (addressRepository.existsByStreet(dto.street())) {
            return new ResponseEntity<>(new ResponseModelMessage("Endereço com nome " + dto.street() + " já existe"), HttpStatus.BAD_REQUEST);
        }
        if (!addressRepository.existsById(id) || address == null) {
            return new ResponseEntity<>(new ResponseModelMessage("Endereço não encontrado"), HttpStatus.BAD_REQUEST);
        }
        address.update(dto);
        addressRepository.save(address);
        return new ResponseEntity<>(new ResponseModelObject("Endereço atualizado", address), HttpStatus.OK);
    }
}
