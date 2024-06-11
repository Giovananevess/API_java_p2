package trevo.maquinas.api.product;

import org.assertj.core.api.Assertions;
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
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void whenCreate() {
        Order order = new Order();
        order.setName("teste");
        order.setDescription("Teste");
        order.setStatus(StatusEnum.DISPONIVEL);
        order.setCategory(CategoryEnum.COMBUSTIVEL);
        order.setPrice(135.5);
        Order save = orderRepository.save(order);
        Assertions.assertThat(save.getId()).isNotNull();
        assertThat(order.getId()).isNotNull();
        assertThat(order.getName()).isEqualTo("teste");
        assertThat(order.getDescription()).isEqualTo("Teste");
        assertThat(order.getCategory()).isEqualTo(CategoryEnum.COMBUSTIVEL);
        assertThat(order.getPrice()).isEqualTo(135.5);
        assertThat(order.getStatus()).isEqualTo(StatusEnum.DISPONIVEL);
    }
}
