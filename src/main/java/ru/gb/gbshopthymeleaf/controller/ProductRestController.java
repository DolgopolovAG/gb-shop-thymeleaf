package ru.gb.gbshopthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.product.api.ProductGateway;
import ru.gb.api.product.dto.ProductDto;
import ru.gb.gbshopthymeleaf.entity.Cart;
import ru.gb.gbshopthymeleaf.entity.Product;
import ru.gb.gbshopthymeleaf.service.CartService;
import ru.gb.gbshopthymeleaf.web.dto.mapper.ProductMapper;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {


    private final ProductGateway productGateway;

    private final ProductMapper productMapper;

    private final CartService cartService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('product.read')")
    public String getProductList(Model model) {
        model.addAttribute("products",productGateway.getProductList());
        return "product-list";
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAuthority('product.read')")
    public String info(Model model, @PathVariable(name = "productId") Long id) {
        ResponseEntity<? extends ProductDto> productRe;
        ProductDto product;
        if (id != null) {
            productRe = productGateway.getProduct(id);
            product = productRe.getBody();
        } else {
            return "redirect:/product/all";
        }
        model.addAttribute("product", product);
        return "product-info";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        ResponseEntity<? extends ProductDto> productRe;
        ProductDto product;

        if (id != null) {
            productRe = productGateway.getProduct(id);
            product = productRe.getBody();
        } else {
            product = new ProductDto();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveProduct(Product product) {
        productGateway.handlePost(productMapper.toProductDto(product));
        return "redirect:/product/all";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('product.delete')")
    public String deleteById(@RequestParam(name = "id") Long id) {
        productGateway.deleteById(id);
        return "redirect:/product/all";
    }

    @GetMapping("/cart")
    public String getCartList(Model model) {
        model.addAttribute("cart", getCurrentCart());
        return "product-cart";
    }

    @GetMapping("/cart/delete")
    public String deleteСartById(@RequestParam(name = "cartId") Long cartId, @RequestParam(name = "id") Long id) {
        ResponseEntity<? extends ProductDto> productRe = productGateway.getProduct(id);
        ProductDto product = productRe.getBody();
        Cart cart = cartService.findById(cartId);
        cart.delProduct(productMapper.toProduct(product));
        cartService.save(cart);
        return "redirect:/product/cart";
    }

    @GetMapping("/cart/add")
    public String deleteСartById(@RequestParam(name = "id") Long id) {
        ResponseEntity<? extends ProductDto> productRe = productGateway.getProduct(id);
        ProductDto product = productRe.getBody();
        Cart cart = getCurrentCart();
        cart.addProduct(productMapper.toProduct(product));
        cartService.save(cart);
        return "redirect:/product/all";
    }

    public Cart getCurrentCart(){
        return cartService.findById(1L); // создана одна корзина
    }



}
