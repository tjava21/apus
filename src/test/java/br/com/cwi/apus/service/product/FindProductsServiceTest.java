package br.com.cwi.apus.service.product;

import br.com.cwi.apus.domain.Product;
import br.com.cwi.apus.mapper.ProductMapper;
import br.com.cwi.apus.repository.ProductRepository;
import br.com.cwi.apus.web.response.product.ProductResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FindProductsServiceTest {

    @InjectMocks
    private FindProductsService tested;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Test
    public void shouldFindAllProducts() {

        // #1 - ARRANGE
        Product product1 = Product.builder().id(1L).price(BigDecimal.ONE).description("Agua").quantity(50).volume(1).build();
        Product product2 = Product.builder().id(2L).price(BigDecimal.TEN).description("Trakinas").quantity(30).volume(1).build();
        List<Product> listProducts = Arrays.asList(product1, product2);

        Mockito.when(repository.findAll()).thenReturn(listProducts);


        // #2 - ACT
        List<ProductResponse> results = tested.execute();


        // #3 - ASSERT

        // mockito (verifica se foi chamado)
        Mockito.verify(repository).findAll();
        Mockito.verify(mapper).toProductResponse(product1);
        Mockito.verify(mapper).toProductResponse(product2);

        // junit (verifica o valor)
        Assert.assertEquals(2, results.size());
    }
}