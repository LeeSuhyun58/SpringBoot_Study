package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// <대상 엔티티, 기본 타입> : 대상 엔티티의 이름과 해당 엔티티의 @Id 필드 타입을 지정
public interface ProductRepository extends JpaRepository<Product, Long> {

}
