package depth.hackerthon.team3.board.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long board_id;//아이디
    private String board_password;//비번
    private String shaved_ice_name; //빙수이름
    private String description; //빙수설명
    private Integer good_reaction; //좋은반응
    private Integer bad_reaction; //나쁜반응
    private String item1; //재료1
    private String item2; //재료2
    private String item3; //재료3

}
