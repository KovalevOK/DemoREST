package ru.kovalev.restdemoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attribute")
public class Attribute implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(length = 100)
  private String name;

  @Column(length = 100)
  private String type;

  @Column(length = 2, name = "filter_alias")
  private String filterAlias;

  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
  private List<ValueEnumerate> valueEnumerateList = new ArrayList<>();

  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
  private List<AttributeValue> attributeValueList = new ArrayList<>();



  //Эта реализация работает только с Hibernate
  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Attribute attribute = (Attribute) o;
    return getId() != null && Objects.equals(getId(), attribute.getId());
  }

  //Эта реализация работает только с Hibernate
  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
