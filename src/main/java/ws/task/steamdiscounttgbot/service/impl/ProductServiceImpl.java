package ws.task.steamdiscounttgbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.task.steamdiscounttgbot.entity.Product;
import ws.task.steamdiscounttgbot.repository.ProductRepository;
import ws.task.steamdiscounttgbot.repository.UserRepository;
import ws.task.steamdiscounttgbot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }


}
