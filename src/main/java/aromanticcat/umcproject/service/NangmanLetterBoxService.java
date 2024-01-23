package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;

import java.util.List;

public interface NangmanLetterBoxService {

    NangmanLetter writeAndSendLetter(NangmanLetterBoxRequestDTO.WriteLetterDTO requestDTO);

    List<NangmanLetter> getLetterList();

    NangmanLetter getLetterById(Long id);

    NangmanReply writeAndSendReply(NangmanLetterBoxRequestDTO.WriteReplyDTO requestDTO, Long id);

//    NangmanLetterDTO readOne(Long id);
//
//    void receivedReply(NangmanLetterDTO nangmanLetterDTO);
}
