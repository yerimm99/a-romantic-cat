package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;
import aromanticcat.umcproject.web.dto.NangmanPostBoxResponseDTO;

public class NangmanPostBoxConverter {

    public static NangmanPostBoxResponseDTO.SendLetterResultDTO toSendLetterResultDTO(NangmanLetter nangmanLetter){

        return NangmanPostBoxResponseDTO.SendLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .senderNickname(nangmanLetter.getSender_nickname())
                .createdAt(nangmanLetter.getCreatedAt())
                .build();
    }

    public static NangmanLetter toNangmanLetter(NangmanPostBoxRequestDTO.SendLetterDTO request){
        return NangmanLetter.builder()
                .is_public(request.getIsPublic())
                .content(request.getContent())
                .sender_nickname(request.getSenderRandomNickname())
//                .member(request.getMember())
                .build();
    }
}
