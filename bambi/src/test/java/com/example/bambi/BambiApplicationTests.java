package com.example.bambi;

import com.example.bambi.controller.ProductController;
import com.example.bambi.entity.Product;
import com.example.bambi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BambiApplicationTests {

    @Test
    void testListAllProducts() {
        //Arrange
        String keyword = "test";
        Model model = Mockito.mock(Model.class);
        ProductService productService = Mockito.mock(ProductService.class);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        PageImpl<Product> page = new PageImpl<>(productList, PageRequest.of(0, 1), 1);
        Mockito.when(productService.findPaginated(keyword, 1, 5, "productName", "asc")).thenReturn(page);
        ProductController controller = new ProductController(productService);
        // Act
        String viewName = controller.listAllProducts(keyword, model);
        // Assert
        assertEquals("products", viewName);
        Mockito.verify(model).addAttribute("listProducts", productList);
    }

    @Test
    void testEditProductForm() {
        // Arrange
        Long id = 1L;
        Model model = Mockito.mock(Model.class);
        ProductService productService = Mockito.mock(ProductService.class);
        Product product = new Product();
        Mockito.when(productService.getProductById(id)).thenReturn(product);
        ProductController controller = new ProductController(productService);
        // Act
        String viewName = controller.editProductForm(id, model);
        // Assert
        assertEquals("edit_product", viewName);
        Mockito.verify(model).addAttribute("product", product);
    }
    }
