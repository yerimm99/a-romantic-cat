package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;
import aromanticcat.umcproject.web.dto.NangmanPostBoxResponseDTO;

public class NangmanPostBoxConverter {

    //낭만 레터 엔티티 -> DTO 생성
    public static NangmanPostBoxResponseDTO.SendLetterResultDTO toSendLetterResultDTO(NangmanLetter nangmanLetter){

        return NangmanPostBoxResponseDTO.SendLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .senderNickname(nangmanLetter.getSenderNickname())
                .createdAt(nangmanLetter.getCreatedAt())
                .build();
    }

    //낭만 레터 DTO -> 엔티티 생성
    public static NangmanLetter toNangmanLetterResult(NangmanPostBoxRequestDTO.SendLetterDTO request){
        return NangmanLetter.builder()
                .isPublic(request.getIsPublic())
                .content(request.getContent())
                .senderNickname(request.getSenderRandomNickname())
//                .member(request.getMember())
                .build();
    }

    //낭만 레터 엔티티 -> 프리뷰 낭만 레터 DTO 생성
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

    //낭만 레터 엔티티 + 랜덤 닉네임 -> 낭만 리플라이 DTO 생성
    public static NangmanPostBoxResponseDTO.SelectedLetterResultDTO toReplyLetterResultDTO(NangmanLetter nangmanLetter, String randomNickname){
        return NangmanPostBoxResponseDTO.SelectedLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .nangmanLetterContent(nangmanLetter.getContent())
                .senderNickname(nangmanLetter.getSenderNickname())
                .replySenderNickname(randomNickname)
                .build();
    }

    // 낭만 리플라이 DTO -> 낭만 리플라이 엔티티
    public static NangmanReply toNangmanReplyResult(NangmanPostBoxRequestDTO.ReplyLetterDTO request, NangmanLetter nangmanLetter){
        return NangmanReply.builder()
                .content(request.getReplyContent())
                .replySenderNickname(request.getReplySenderNickname())
                .nangmanLetter(nangmanLetter)
//                .member(request.getMember())
                .build();

    }

    public static NangmanPostBoxResponseDTO.SendReplyResultDTO toSendReplyResultDTO(NangmanReply nangmanReply){
        return NangmanPostBoxResponseDTO.SendReplyResultDTO.builder()
                .nangmanReplyId(nangmanReply.getId())
                .nangmanLetterId(nangmanReply.getNangmanLetter().getId())
                .replySenderNickname(nangmanReply.getReplySenderNickname())
                .createdAt(nangmanReply.getCreatedAt())
                .build();
    }



}
