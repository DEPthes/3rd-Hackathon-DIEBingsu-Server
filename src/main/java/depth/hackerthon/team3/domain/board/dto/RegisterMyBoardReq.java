package depth.hackerthon.team3.domain.board.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterMyBoardReq {

    @Pattern(regexp = "^(?=.*[a-zA-Z])?(?=.*\\d)[a-zA-Z\\d]{4,8}$", message = "비밀번호 형식은 숫자 또는 숫자, 영문 조합 최소 4자 최대 8자입니다.")
    private String boardPassword;//비번

    @Size(min = 2, max = 12, message = "제목은 2~12자 사이여야 합니다.")
    private String shavedIceName; //빙수이름

    @Size(min = 2, max = 90, message = "내용은 2~90자 사이여야 합니다.")
    private String description; //빙수설명
    private String item1; //재료1
    private String item2; //재료2
    private String item3; //재료3
    private String deviceId;
}
