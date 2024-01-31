package aromanticcat.umcproject.service;

import aromanticcat.umcproject.web.dto.MyCollectionResponseDTO;

import java.util.List;

public interface MyCollectionService {

    List<MyCollectionResponseDTO.AcquiredLetterPaperResultDTO>  findLetterPaperList(Long memberId, int page, int pageSize);
    List<MyCollectionResponseDTO.AcquiredStampResultDTO>  findStampList(Long memberId, int page, int pageSize);

}
