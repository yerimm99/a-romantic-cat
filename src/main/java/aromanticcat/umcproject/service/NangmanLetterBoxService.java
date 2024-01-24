package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxResponseDTO;

import java.util.List;
import java.util.Optional;

public interface NangmanLetterBoxService {

    NangmanLetter writeAndSendLetter(NangmanLetterBoxRequestDTO.WriteLetterDTO requestDTO);

    List<NangmanLetter> getLetterList();

    NangmanLetter getLetterById(Long nangmanLetterid);

    NangmanReply writeAndSendReply(NangmanLetterBoxRequestDTO.WriteReplyDTO requestDTO, Long id);

    List<NangmanLetter> getNangmanLettersByUserId(Long userId);

    NangmanLetterBoxResponseDTO.PreviewReplyResultDTO getPreviewReplyForLetter(Long userId, Long nangmanLetterId);
    Optional<NangmanReply> getReplyForLetter(Long userId, Long nangmanLetterId);


//    NangmanLetterDTO readOne(Long id);
//
//    void receivedReply(NangmanLetterDTO nangmanLetterDTO);
}
