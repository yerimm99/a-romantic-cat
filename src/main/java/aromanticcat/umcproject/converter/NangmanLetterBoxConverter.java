package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxResponseDTO;

public class NangmanLetterBoxConverter {

    public static NangmanLetterBoxResponseDTO.WriteLetterResultDTO toWriteLetterResultDTO(NangmanLetter nangmanLetter) {

        return NangmanLetterBoxResponseDTO.WriteLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .senderNickname(nangmanLetter.getSenderNickname())
                .createdAt(nangmanLetter.getCreatedAt())
                .build();
    }

    public static NangmanLetter toNangmanLetter(NangmanLetterBoxRequestDTO.WriteLetterDTO request, Member member) {

        return NangmanLetter.builder()
                .isPublic(request.getIsPublic())
                .content(request.getContent())
                .senderNickname(request.getSenderRandomNickname())
                .member(member)
                .build();
    }

    public static NangmanLetterBoxResponseDTO.PreviewLetterResultDTO toPreviewLetterResultDTO(
            NangmanLetter nangmanLetter) {
        return NangmanLetterBoxResponseDTO.PreviewLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .preview(getPreviewText(nangmanLetter.getContent()))
                .createdAt(nangmanLetter.getCreatedAt())
                .build();
    }

    public static NangmanLetterBoxResponseDTO.SelectedLetterResultDTO toSelectedLetterResultDTO(
            NangmanLetter nangmanLetter, String randomNickname) {
        return NangmanLetterBoxResponseDTO.SelectedLetterResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .nangmanLetterContent(nangmanLetter.getContent())
                .senderNickname(nangmanLetter.getSenderNickname())
                .replySenderNickname(randomNickname)
                .build();
    }

    public static NangmanReply toNangmanReply(NangmanLetterBoxRequestDTO.WriteReplyDTO request,
                                              NangmanLetter nangmanLetter, Member member) {
        return NangmanReply.builder()
                .content(request.getReplyContent())
                .replySenderNickname(request.getReplySenderNickname())
                .nangmanLetter(nangmanLetter)
                .member(member)
                .build();

    }

    public static NangmanLetterBoxResponseDTO.WriteReplyResultDTO toWriteReplyResultDTO(NangmanReply nangmanReply) {
        return NangmanLetterBoxResponseDTO.WriteReplyResultDTO.builder()
                .nangmanReplyId(nangmanReply.getId())
                .nangmanLetterId(nangmanReply.getNangmanLetter().getId())
                .replySenderNickname(nangmanReply.getReplySenderNickname())
                .createdAt(nangmanReply.getCreatedAt())
                .build();
    }

    public static NangmanLetterBoxResponseDTO.PreviewReplyResultDTO toPreviewReplyResultDTO(NangmanReply nangmanReply) {

        return NangmanLetterBoxResponseDTO.PreviewReplyResultDTO.builder()
                .nangmanReplyId(nangmanReply.getId())
                .nangmanLetterId(nangmanReply.getNangmanLetter().getId())
                .preview(getPreviewText(nangmanReply.getContent()))
                .build();

    }


    private static String getPreviewText(String content) {
        // 답장 내용을 40자로 제한
        return content.length() <= 40 ? content : content.substring(0, 40) + "...";

    }


    public static NangmanLetterBoxResponseDTO.PreviewBothResultDTO toPreviewBothResultDTO(NangmanLetter nangmanLetter,
                                                                                          NangmanReply nangmanReply) {
        return NangmanLetterBoxResponseDTO.PreviewBothResultDTO.builder()
                .nangmanLetterId(nangmanLetter.getId())
                .nangmanReplyId(nangmanReply.getId())
                .previewLetter(getPreviewText(nangmanLetter.getContent()))
                .previewReply(getPreviewText(nangmanReply.getContent()))
                .totalEmojiCount(calculateTotalEmojiCount(nangmanLetter))
                .createAt(nangmanReply.getCreatedAt())
                .build();
    }

    private static Integer calculateTotalEmojiCount(NangmanLetter nangmanLetter) {
        // isPublic이 false이거나 hasResponse가 false이면 이모지 수를 계산하지 않음
        if (!nangmanLetter.getIsPublic() || !nangmanLetter.getHasResponse()) {
            return null;
        }

        // 각 이모지 수를 합산하여 반환
        int thumbsUpCnt = nangmanLetter.getThumbsUpCnt();
        int heartCnt = nangmanLetter.getHeartCnt();
        int cryingCnt = nangmanLetter.getCryingCnt();
        int cloverCnt = nangmanLetter.getCloverCnt();
        int clapCnt = nangmanLetter.getClapCnt();
        int starCnt = nangmanLetter.getStarCnt();

        return thumbsUpCnt + heartCnt + cryingCnt + cloverCnt + clapCnt + starCnt;

    }
}
