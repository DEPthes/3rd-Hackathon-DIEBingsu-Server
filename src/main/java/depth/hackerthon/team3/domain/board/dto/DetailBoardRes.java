package depth.hackerthon.team3.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DetailBoardRes {
    private Long boardId;//아이디
    private String image;
    private String item1; //재료1
    private String item2; //재료2
    private String item3; //재료3
    private String shavedIceName; //빙수이름
    private String description; //빙수설명
    private int reaction1; //환상적이에요
    private int reaction2; //웃겨요
    private int reaction3; //먹고싶어요
    private int reaction4; //이상해요
    private int reaction5; //별로예요
    private boolean isValid;  // 본인 확인
}


