package trevo.maquinas.api.product;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import trevo.maquinas.api.ApiApplication;
import trevo.maquinas.api.dto.CategoryEnum;
import trevo.maquinas.api.dto.StatusEnum;
import trevo.maquinas.api.model.Order;
import trevo.maquinas.api.repository.OrderRepository;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc

public class RepositoryOrderTest {


    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void whenCreateProduct_thenPersistenseDate() {
        Order order = new Order();
        order.setName("Teste1");
        order.setDescription("Esse produto é um pulverizador");
        order.setCategory(CategoryEnum.COMBUSTIVEL);
        order.setPrice(1354.5);
        order.setStatus(StatusEnum.DISPONIVEL);
        orderRepository.save(order);
        assertThat(order.getId()).isNotNull();
        assertThat(order.getName()).isEqualTo("Teste1");
        assertThat(order.getDescription()).isEqualTo("Esse produto é um pulverizador");
        assertThat(order.getCategory()).isEqualTo(CategoryEnum.COMBUSTIVEL);
        assertThat(order.getPrice()).isEqualTo(1354.5);
        assertThat(order.getStatus()).isEqualTo(StatusEnum.DISPONIVEL);
    }

}
