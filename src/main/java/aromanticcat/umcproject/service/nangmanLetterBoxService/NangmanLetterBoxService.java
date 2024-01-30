package aromanticcat.umcproject.service.nangmanLetterBoxService;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxResponseDTO;

import java.util.List;

public interface NangmanLetterBoxService {

    NangmanLetter sendLetter(NangmanLetterBoxRequestDTO.WriteLetterDTO requestDTO);

    List<NangmanLetter> getLetterList(int page, int pageSize);

    NangmanLetter getLetter(Long nangmanLetterid);

    NangmanLetterBoxResponseDTO.SelectedLetterResultDTO getLetterInfo(Long nangmanLetterId);

    NangmanLetterBoxResponseDTO.WriteReplyResultDTO sendReply(NangmanLetterBoxRequestDTO.WriteReplyDTO requestDTO, Long id);

}
