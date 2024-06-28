package depth.hackerthon.team3.domain.board.domain;

import depth.hackerthon.team3.domain.common.BaseEntity;
import depth.hackerthon.team3.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Board extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long boardId;//아이디
    private String boardPassword;//비번
    private String image;
    private String shavedIceName; //빙수이름
    private String description; //빙수설명
    private int reaction1; //환상적이에요
    private int reaction2; //웃겨요
    private int reaction3; //먹고싶어요
    private int reaction4; //이상해요
    private int reaction5; //별로예요
    private String item1; //재료1
    private String item2; //재료2
    private String item3; //재료3

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Board(String boardPassword, String image, String shavedIceName, String description, int reaction1, int reaction2, int reaction3, int reaction4, int reaction5, String item1, String item2, String item3) {
        this.boardPassword = boardPassword;
        this.description = description;
        this.image = image;
        this.reaction1 = reaction1;
        this.reaction2 = reaction2;
        this.reaction3 = reaction3;
        this.reaction4 = reaction4;
        this.reaction5 = reaction5;
        this.shavedIceName = shavedIceName;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
 }
