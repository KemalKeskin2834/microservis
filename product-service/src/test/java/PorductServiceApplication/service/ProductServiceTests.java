package PorductServiceApplication.service;

import com.kemalkeskin.product.business.Dtos.CreateProductRequest;
import com.kemalkeskin.product.business.concretes.ProductManager;
import com.kemalkeskin.product.model.Product;
import com.kemalkeskin.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductManager productManager;

    @Test
    public void productManager_addProduct(){
        Product product=new Product("coffe","Ice latte",100);

        CreateProductRequest productRequest= new CreateProductRequest("coffe","Ice latte",100);

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        CreateProductRequest addProducts=productManager.add(productRequest,2);
        Assertions.assertThat(addProducts).isNotNull();


    }

}
