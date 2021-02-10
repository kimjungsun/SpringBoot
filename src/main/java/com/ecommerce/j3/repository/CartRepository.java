package com.ecommerce.j3.repository;

import com.ecommerce.j3.domain.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


}
