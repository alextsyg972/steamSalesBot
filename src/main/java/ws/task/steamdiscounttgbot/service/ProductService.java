package ws.task.steamdiscounttgbot.service;

import ws.task.steamdiscounttgbot.entity.Product;
import ws.task.steamdiscounttgbot.entity.User;

public interface ProductService {

    void addProduct(Product product);

    void deleteProductById(Long id);

    Product findProductById(Long id);
}
