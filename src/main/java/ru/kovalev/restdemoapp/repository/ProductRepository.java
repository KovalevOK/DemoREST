package ru.kovalev.restdemoapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kovalev.restdemoapp.projection.ProductView;
import ru.kovalev.restdemoapp.entity.*;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = """
          select new ru.kovalev.restdemoapp.projection.ProductView(p.id, p.name,p.price) from Product p
                                       left join  p.attributeValueList av
                                       left join  av.valueEnumerate ve
                                       where ve.attribute.id in (:id) and ve.value in (:value)
                                       group by p
                                       having count(p.id)=:quantity
                                       order by p.id
          """)
  Page<ProductView> findAllProductWithFilterAndPagination(
          @Param("id") List<Long> id,
          @Param("value") List<String> value,
          @Param("quantity") Integer quantity,
          Pageable pageable);

  @Query(value = """
            select new ru.kovalev.restdemoapp.projection.ProductView(p.id, p.name,p.price) from Product p order by p.id
          """
  )
  Page<ProductView> findAllProductWithPagination(Pageable pageable);

  @Query(value = """
                 select p from Product p
                      join fetch p.attributeValueList av
                      join fetch av.valueEnumerate ve
                      join fetch ve.attribute
                      where p.id=:id
          """)
  Optional<Product> getProductBy(@Param("id") Long id);
}



