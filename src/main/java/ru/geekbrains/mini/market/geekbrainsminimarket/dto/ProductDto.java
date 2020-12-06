package ru.geekbrains.mini.market.geekbrainsminimarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private String categoryName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getTitle();
    }
}
