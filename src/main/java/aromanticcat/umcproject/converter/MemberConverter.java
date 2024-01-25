package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.web.dto.MemberResponseDTO;

public class MemberConverter {
    public static MemberResponseDTO.MemberJoinResultDTO toMemberDTO(Member member) {

        return MemberResponseDTO.MemberJoinResultDTO.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .coin(member.getCoin())
                .build();
    }

    public static MemberResponseDTO.MemberExceptionDTO toMemberExceptionDTO(String nickname) {
        return MemberResponseDTO.MemberExceptionDTO.builder()
                .nickname(nickname)
                .build();
    }
}
