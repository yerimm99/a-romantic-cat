package aromanticcat.umcproject.service.nangmanLetterBoxService;

import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanCollectionResponseDTO;

import java.util.List;

public interface NangmanCollectionService {

    List<NangmanCollectionResponseDTO.PreviewBothResultDTO> findCollection(int page, int pageSize);

    NangmanCollectionResponseDTO.BothResultDTO findCollectionDetails(Long nangmanLetterId);

    void likeCollection(Long nangmanLetterId,String emojiType);

    List<NangmanCollectionResponseDTO.PreviewBothResultDTO> getMyLetterList(Long userId, int page, int pageSize);

    List<NangmanCollectionResponseDTO.PreviewBothResultDTO> getMyReplyList(Long userId, int page, int pageSize);

}
