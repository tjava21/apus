package br.com.cwi.apus.service.basket;

import br.com.cwi.apus.domain.Basket;
import br.com.cwi.apus.repository.BasketRepository;
import br.com.cwi.apus.web.request.basket.UpdateBasketShippingRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.cwi.apus.utils.DomainUtils.toExternalId;

@SpringBootTest
public class UpdateBasketShippingServiceTest {

    @InjectMocks
    private UpdateBasketShippingService tested;

    @Mock
    private FindBasketByIdService findBasketByIdService;

    @Mock
    private RefreshBasketService refreshBasketService;

    @Mock
    private BasketRepository repository;

    @Test
    public void shouldSetAddress() {

        Basket basket1 = Basket.builder().id(1L).build();
        UpdateBasketShippingRequest request = new UpdateBasketShippingRequest();

        Mockito.when(findBasketByIdService.getDomain(toExternalId(basket1.getId()))).thenReturn(basket1);

        request.setAddress("teste, 435");
        request.setZip("123456789");

        tested.execute(toExternalId(basket1.getId()),request);

        Mockito.verify(findBasketByIdService).getDomain(toExternalId(basket1.getId()));
        Mockito.verify(refreshBasketService).execute(basket1);
        Mockito.verify(repository).save(basket1);
    }
}
