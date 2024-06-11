package trevo.maquinas.api.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.maquinas.api.dto.OrderDadosDTO;
import trevo.maquinas.api.model.Order;
import trevo.maquinas.api.repository.OrderRepository;
import trevo.maquinas.api.response.ResponseModelMessage;
import trevo.maquinas.api.response.ResponseModelObject;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<?> register(@RequestBody @Valid OrderDadosDTO dto) {
        Order order = new Order(dto);
        orderRepository.save(order);
        return new ResponseEntity<>(new ResponseModelObject("Produto cadastrado", order), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()){
            return new ResponseEntity<>(new ResponseModelMessage("Lista de produto está vazia"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de produtos", orders), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(UUID id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelMessage("Produto foi deletado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMessage("Produto não encontrado"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid OrderDadosDTO dto) {
        Order order = orderRepository.findById(id).orElse(null);
        if (orderRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelMessage("Produto com nome " + dto.name() + " já existe"), HttpStatus.BAD_REQUEST);
        }
        if (!orderRepository.existsById(id) || order == null) {
            return new ResponseEntity<>(new ResponseModelMessage("Produto não encontrado"), HttpStatus.BAD_REQUEST);
        }
        order.update(dto);
        orderRepository.save(order);
        return new ResponseEntity<>(new ResponseModelObject("Produto atualizado", order), HttpStatus.OK);
    }
}
