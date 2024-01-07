package ru.kovalev.restdemoapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")

class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;
  private Pageable mockPageable;

  @BeforeEach
  void setUp() {
     mockPageable = mock(Pageable.class);

  }

  @Test
  void findProductById_IsPresent() {
    assertTrue(productRepository.getProductBy(14L).isPresent());
  }

  @Test
  void findProductById_IsNotPresent() {
    assertTrue(productRepository.getProductBy(1L).isEmpty());
  }

  @Test
  void findProductByName_EqualPreAssignedName() {
    String name = "43\" Телевизор Samsung UE43CU7100UXRU, Crystal UHD, 4K Ultra HD, черный, СМАРТ ТВ, Tizen OS";
    assertEquals(name,productRepository.getProductBy(14L).get().getName());
  }


  @Test
  void findAllProductWithPagination_CorrectTotalCount() {
    Pageable firstPage = PageRequest.of(0,5);

    assertEquals(15, productRepository.findAllProductWithPagination(firstPage).getTotalElements());

  }
  @Test
  void findAllProductWithPagination_CorrectCountPerPage() {
    Pageable firstPage = PageRequest.of(0,5);
    assertEquals(5, productRepository.findAllProductWithPagination(firstPage).getContent().size());
  }

  @Test
  void findAllProductWithPagination_OutOfBound() {
    Pageable firstPage = PageRequest.of(10,5);
    assertEquals(0, productRepository.findAllProductWithPagination(firstPage).getContent().size());
  }

  @Test
  void findAllProductWithFilterAndPagination_CorrectResultCount() {
    Pageable firstPage = PageRequest.of(0,5);
    assertEquals(3, productRepository.findAllProductWithFilterAndPagination(List.of(5L),List.of("SUNWIND"),1,firstPage).getContent().size());
  }

  @Test
  void findAllProductWithFilterAndPagination_IncorrectFilterCondition() {
    Pageable firstPage = PageRequest.of(0,5);
    assertEquals(0, productRepository.findAllProductWithFilterAndPagination(List.of(5L),List.of("SUNWIND1"),1,firstPage).getContent().size());
  }

  @Test
  void findAllProductWithFilterAndPagination_IncorrectFilterIdentificator() {
    Pageable firstPage = PageRequest.of(0,5);
    assertEquals(0, productRepository.findAllProductWithFilterAndPagination(List.of(505L),List.of("SUNWIND"),1,firstPage).getContent().size());
  }


}