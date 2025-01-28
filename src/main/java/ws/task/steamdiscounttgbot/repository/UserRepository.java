package ws.task.steamdiscounttgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ws.task.steamdiscounttgbot.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    void deleteUserByChatId(Long chatId);

    User findUserByChatId(Long chatId);

    User findUserByChatIdAndUsername(Long chatId, String username);

    void deleteUserByChatIdAndUsername(Long chatId, String username);

}
