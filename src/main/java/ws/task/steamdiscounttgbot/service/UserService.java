package ws.task.steamdiscounttgbot.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import ws.task.steamdiscounttgbot.entity.User;

public interface UserService {

    void addUser(Update update, String steamLogin);

    void addSteam(Long chatId, String username, String steamLogin);

    User findUserById(Long id);

    User findUserByChatIdAndUsername(Long chatId, String username);

    void deleteUserById(Long id);

    void deleteUserByChatId(Long chatId);

    void deleteUserByChatIdAndUsername(Long chatId, String username);

    void deleteSteam(Long chatId,String steamLogin);

}
