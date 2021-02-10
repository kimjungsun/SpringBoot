package com.ecommerce.j3.controller.api;


import com.ecommerce.j3.controller.dto.BodyData;
import com.ecommerce.j3.controller.dto.CartDto;
import com.ecommerce.j3.controller.dto.CartDto.CartApiRequest;
import com.ecommerce.j3.controller.dto.CartDto.CartApiResponse;
import com.ecommerce.j3.controller.dto.CartItemDto;
import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Cart;
import com.ecommerce.j3.service.AccountService;
import com.ecommerce.j3.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = {"03. Cart"})
@Slf4j
@RestController
//@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartApiController implements CrudInterface<CartApiRequest, CartApiResponse> {
    private final CartService cartService;
    private final AccountService accountService;
    @ApiOperation(value = "카트 추가", notes = "카트를 추가한다.")
    @Override
    @PostMapping
    public BodyData<CartDto.CartApiResponse> create(@RequestBody CartDto.CartApiRequest request) {
        CartDto.CartApiResponse cartApiResponse = cartService.save(request);
        return BodyData.OK(cartApiResponse);
    }

    @ApiOperation(value = "카트 조회", notes = "카트를 조회한다.")
    @GetMapping("")
    @Override
//    @GetMapping
    public BodyData<CartDto.CartApiResponse> read(Long id) {
        return null;
    }

    @ApiOperation(value = "카트 갱신", notes = "카트를 갱신한다.")
    @Override
    @PutMapping
    public BodyData<CartDto.CartApiResponse> update(@RequestBody CartDto.CartApiRequest request) {
        return null;
    }

    @ApiOperation(value = "카트 삭제", notes = "카트를 삭제한다.")
    @Override
    @DeleteMapping
    public BodyData delete(Long id) {
        return null;
    }

    /******************* 카트 생성, 카트 삭제  **************/

    @ApiOperation(value = "카트 추가", notes = "카트를 추가한다.")
    @PostMapping("/api/{idx}/carts")
    public BodyData<CartDto.createCartApiResponse> cartCreate(@PathVariable("idx") long cartidx) {
        Account account = accountService.findById2(cartidx).get();
        CartDto.createCartApiResponse createCartApiResponse = new CartDto.createCartApiResponse(cartService.makeCart(account));
        return BodyData.OK(createCartApiResponse);
    }

    @ApiOperation(value = "카트 삭제", notes = "카트를 삭제한다.")
    @DeleteMapping({"/api/carts/{cartidx}"})
    public BodyData<CartDto.deleteCartApiResponse> cartDelete( @PathVariable("cartidx") long cartidx) {
        cartService.remove(cartidx);
        CartDto.deleteCartApiResponse deleteCartApiResponse = new CartDto.deleteCartApiResponse(cartidx);
        return BodyData.OK(deleteCartApiResponse);
    }

    /******************* 카트 아이템 추가 , 카트 아이템 삭제  **************/

    @ApiOperation(value = "카트아이템 추가", notes = "카트아이템을 추가한다.")
    @PostMapping("/api/carts/{cartIdx}/cartItem")
    public BodyData<CartItemDto.createCartItemApiResponse> cartItemCreate(@PathVariable("cartIdx") long cartidx,@RequestBody  CartItemDto.createCartItemApiRequest createCartItemApiRequest) {
        CartItemDto.createCartItemApiResponse createCartItemApiResponse = new CartItemDto.createCartItemApiResponse(cartService.addItem(cartidx,createCartItemApiRequest.getProductid(),createCartItemApiRequest.getQuantity()));
        return BodyData.OK(createCartItemApiResponse);
    }

    @ApiOperation(value = "카트아이템 삭제", notes = "카트아이템을 삭제한다.")
    @DeleteMapping("/api/carts/{cartIdx}/cartItem/{cartitemIdx}")
    public BodyData<CartItemDto.deleteCartItemApiResponse> cartItemDelete(@PathVariable("cartIdx") long cartidx,@PathVariable("cartitemIdx") long cartitemidx) {
        CartItemDto.deleteCartItemApiResponse deleteCartItemApiResponse = new CartItemDto.deleteCartItemApiResponse(cartService.delete(cartidx,cartitemidx));
        return BodyData.OK(deleteCartItemApiResponse);
    }
}
