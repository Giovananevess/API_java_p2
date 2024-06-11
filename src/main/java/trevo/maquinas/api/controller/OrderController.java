package trevo.maquinas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.maquinas.api.dto.OrderDadosDTO;
import trevo.maquinas.api.service.OrderService;
import java.util.UUID;

@RestController
@RequestMapping("product")

public class OrderController {
    @Autowired
    OrderService OrderService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar um novo produto", tags = "Produto")
    public ResponseEntity<?> register(@RequestBody @Valid OrderDadosDTO dto) {
        return OrderService.register(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos já cadastrados", tags = "Produto")
    public ResponseEntity<?> list() {
        return OrderService.list();
    }

    @DeleteMapping ("/delete/{id}")
    @Operation(summary = "Deletar um produto", tags = "Produto")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return OrderService.delete(id);
    }

    @PutMapping ("/update/{id}")
    @Transactional
    @Operation(summary = "Atualizar um produto", tags = "Produto")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid OrderDadosDTO dto) {
        return OrderService.update(id, dto);
    }
}
