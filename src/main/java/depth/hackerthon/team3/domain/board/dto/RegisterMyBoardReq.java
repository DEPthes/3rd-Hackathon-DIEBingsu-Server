package depth.hackerthon.team3.domain.board.dto;

import lombok.Getter;

@Getter
public class RegisterMyBoardReq {

    private String boardPassword;//비번
    private String image;
    private String shavedIceName; //빙수이름
    private String description; //빙수설명
    private String item1; //재료1
    private String item2; //재료2
    private String item3; //재료3
    private String deviceId;
}
