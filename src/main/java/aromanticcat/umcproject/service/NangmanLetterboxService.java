package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxResponseDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterboxRequestDTO;
import java.util.List;
import java.util.Optional;

public interface NangmanLetterboxService {

    NangmanLetter writeAndSendLetter(NangmanLetterboxRequestDTO.WriteLetterDTO requestDTO);

    List<NangmanLetter> getLetterList();

    NangmanLetter getLetterById(Long nangmanLetterid);

    NangmanReply writeAndSendReply(NangmanLetterboxRequestDTO.WriteReplyDTO requestDTO, Long id);

    List<NangmanLetter> getNangmanLettersByUserId(Long userId);

    Optional<NangmanReply> getReplyForLetter(Long userId, Long nangmanLetterId);

    List<NangmanLetterBoxResponseDTO.PreviewBothResultDTO> getReplyListByUserId(Long userId);
}
