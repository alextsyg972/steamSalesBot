package ws.task.steamdiscounttgbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageService {

    SendMessage botCommandHandler(Update update);

}
