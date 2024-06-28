package depth.hackerthon.team3.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; //사용자아이디

    private String deviceId; //디바이스아이디

    @Builder
    public User(String deviceId) {
        this.deviceId = deviceId;
    }
}