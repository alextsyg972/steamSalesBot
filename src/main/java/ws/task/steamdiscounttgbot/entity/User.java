package ws.task.steamdiscounttgbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Entity
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long chatId;

    private String username;

    private String steamLogin;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> product = new HashSet<>();

}
