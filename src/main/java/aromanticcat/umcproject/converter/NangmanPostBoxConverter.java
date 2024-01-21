package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;
import aromanticcat.umcproject.web.dto.NangmanPostBoxResponseDTO;

public class NangmanPostBoxConverter {

    public static NangmanPostBoxResponseDTO.SendLetterResultDTO toSendLetterResultDTO(NangmanLetter nangmanLetter){

        return NangmanPostBoxResponseDTO.SendLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .senderNickname(nangmanLetter.getSenderNickname())
                .createdAt(nangmanLetter.getCreatedAt())
                .build();
    }

    public static NangmanLetter toNangmanLetter(NangmanPostBoxRequestDTO.SendLetterDTO request){
        return NangmanLetter.builder()
                .isPublic(request.getIsPublic())
                .content(request.getContent())
                .senderNickname(request.getSenderRandomNickname())
//                .member(request.getMember())
                .build();
    }

    public static NangmanPostBoxResponseDTO.LetterSummaryResultDTO toLetterSummaryResultDTO(NangmanLetter nangmanLetter){
        // 편지 내용을 40자 까지만 보이도록
        String content = nangmanLetter.getContent();
        String preview = content.length() <= 40 ? content: content.substring(0, 40) + "...";

        return NangmanPostBoxResponseDTO.LetterSummaryResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .preview(preview)
                .createdAt(nangmanLetter.getCreatedAt())
                .build();
    }
}
