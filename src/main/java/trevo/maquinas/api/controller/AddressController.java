package trevo.maquinas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.maquinas.api.dto.AddressDadosDTO;
import trevo.maquinas.api.service.AddresService;

import java.util.UUID;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddresService addresService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar um novo produto", tags = "Produto")
    public ResponseEntity<?> register(@RequestBody @Valid AddressDadosDTO dto) {
        return addresService.register(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos já cadastrados", tags = "Produto")
    public ResponseEntity<?> list() {
        return addresService.list();
    }

    @DeleteMapping ("/delete/{id}")
    @Operation(summary = "Deletar um endereço", tags = "Endereços")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return addresService.delete(id);
    }

    @PutMapping ("/update/{id}")
    @Transactional
    @Operation(summary = "Atualizar um Endereço", tags = "Endereço")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid AddressDadosDTO dto) {
        return addresService.update(id,dto);
    }
}
