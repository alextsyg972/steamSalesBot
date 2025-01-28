package ws.task.steamdiscounttgbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws.task.steamdiscounttgbot.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
