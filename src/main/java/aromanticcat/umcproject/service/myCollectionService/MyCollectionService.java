package aromanticcat.umcproject.service.myCollectionService;

import aromanticcat.umcproject.web.dto.MyCollectionResponseDTO;
import org.springframework.data.domain.Page;

public interface MyCollectionService {

    Page<MyCollectionResponseDTO.AcquiredLetterPaperResultDTO> findLetterPaperList(int page, int pageSize, boolean onlyMyDesign);
    Page<MyCollectionResponseDTO.AcquiredStampResultDTO>  findStampList(int page, int pageSize, boolean onlyMyDesign);
}
