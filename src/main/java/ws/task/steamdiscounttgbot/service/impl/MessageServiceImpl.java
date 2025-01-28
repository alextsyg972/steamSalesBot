package ws.task.steamdiscounttgbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ws.task.steamdiscounttgbot.service.MessageService;
import ws.task.steamdiscounttgbot.service.UserService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    UserService userService;

    public SendMessage botCommandHandler(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        if (messageText.startsWith("/")) {
            SendMessage sendMessage = switch (messageText) {
                case "/start" -> SendMessage
                        .builder()
                        .chatId(chatId)
                        .text("okay i'm working")
                        .build();
                case "/addsteam" -> {

                    yield SendMessage
                            .builder()
                            .chatId(chatId)
                            .text("added steam")
                            .build();
                }

                case "/regsteam" -> {
                    userService.addUser(update, "asdad"); //add
                    yield SendMessage
                            .builder()
                            .chatId(chatId)
                            .text("regsteam is working")
                            .build();
                }
                case "/delsteam" -> {
                    userService.deleteSteam(update.getMessage().getChatId(), "asdasd"); //add
                    yield SendMessage
                            .builder()
                            .chatId(chatId)
                            .text("delsteam is working")
                            .build();
                }

                default -> SendMessage
                        .builder()
                        .chatId(chatId)
                        .text("error") //add
                        .build();
            };
            return sendMessage;
        }
        return null;
    }

}
