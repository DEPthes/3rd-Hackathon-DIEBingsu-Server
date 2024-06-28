package depth.hackerthon.team3.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReactionRes {
    private Long boardId;//아이디
    private Integer reactionNum;//리액션번호
}
