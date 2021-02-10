package com.ecommerce.j3.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity @Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

@Table ( schema = "SHOP")

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="cart",cascade = CascadeType.ALL, orphanRemoval = true)    // 01-18 Megan
    private List<CartItem> cartItems;

    private String sessionId;

    private String token;

    @Column(columnDefinition = "enum('READY', 'ORDER', 'CANCEL')")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer itemPriceTotal;

    private Integer itemDiscount;

    private Integer tax;

    private Integer shipping;

    private Integer userDiscount;

    private Integer grandTotal;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String roadAddress;;

    private String address;

    private String city;

    private String province;

    private String country;

    private Integer zipCode;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Cart createCart(Account account){
        Cart cart = new Cart();
        cart.account = account;
        cart.email = account.getEmail();
        cart.firstName = account.getFirstName();
        cart.lastName = account.getLastName();
        cart.phoneNumber = account.getPhoneNumber();

        return cart;
    }
}