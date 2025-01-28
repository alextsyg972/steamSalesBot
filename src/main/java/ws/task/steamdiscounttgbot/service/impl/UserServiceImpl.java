package ws.task.steamdiscounttgbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ws.task.steamdiscounttgbot.entity.User;
import ws.task.steamdiscounttgbot.repository.UserRepository;
import ws.task.steamdiscounttgbot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(Update update, String steamLogin) {
        User user = new User();
        Long chatId = update.getMessage().getChatId();
        user.setChatId(chatId);
        user.setUsername(update.getMessage().getFrom().getUserName());
        user.setSteamLogin(steamLogin);
        userRepository.save(user);
    }

    @Override
    public void addSteam(Long chatId, String username, String steamLogin) {
        User user = findUserByChatIdAndUsername(chatId,username);
        user.setSteamLogin(steamLogin);
        userRepository.save(user);
    }


    @Override
    public User findUserByChatIdAndUsername(Long chatId, String username) {
        return userRepository.findUserByChatIdAndUsername(chatId, username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUserByChatId(Long chatId) {
        userRepository.deleteUserByChatId(chatId);
    }

    @Override
    public void deleteSteam(Long chatId, String steamLogin) {
        User user = userRepository.findUserByChatId(chatId);
        user.setSteamLogin(null);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByChatIdAndUsername(Long chatId, String username) {
        userRepository.deleteUserByChatIdAndUsername(chatId, username);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
