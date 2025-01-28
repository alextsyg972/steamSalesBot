package ws.task.steamdiscounttgbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ws.task.steamdiscounttgbot.service.MessageService;
import ws.task.steamdiscounttgbot.service.impl.MessageServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private MessageService messageService;
    private final String botToken;
    private final TelegramClient telegramClient;

    public MyBot(@Value("${bot_key}") String botToken) {
        this.botToken = botToken;
        telegramClient = new OkHttpTelegramClient(getBotToken());
        List<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(BotCommand.builder().command("/start").description("qwe").build());
        botCommands.add(BotCommand.builder().command("/regsteam").description("add steam profile").build());
        botCommands.add(BotCommand.builder().command("/delsteam").description("deleteSteam").build());
        try {
            telegramClient.execute(
                    new SetMyCommands(botCommands, new BotCommandScopeDefault(), null)
            );
        } catch (TelegramApiException e) { //need add logs
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = messageService.botCommandHandler(update);
            try {
                telegramClient.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
