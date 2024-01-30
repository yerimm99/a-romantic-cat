package aromanticcat.umcproject.service.nangmanLetterBoxService;

import aromanticcat.umcproject.converter.NangmanLetterBoxConverter;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.NangmanLetterRepository;
import aromanticcat.umcproject.repository.NangmanReplyRepository;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NangmanLetterBoxServiceImpl implements NangmanLetterBoxService {

    private final NangmanLetterRepository nangmanLetterRepository;
    private final MemberRepository memberRepository;
    private final NangmanReplyRepository nangmanReplyRepository;
    @Override
    @Transactional
    public NangmanLetter sendLetter(NangmanLetterBoxRequestDTO.WriteLetterDTO request){
        //멤버 엔티티 조회
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다. ID: " + request.getMemberId()));

        NangmanLetter newNangmanLetter = NangmanLetterBoxConverter.toNangmanLetter(request, member);

        return nangmanLetterRepository.save(newNangmanLetter);
    }

    @Override
    @Transactional
    public List<NangmanLetter> getLetterList(int page, int pageSize){
        // 페이지 번호와 페이지 크기를 이용하여 페이징된 편지 목록 조회
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<NangmanLetter> letterPage = nangmanLetterRepository.findByHasResponseFalse(pageable);

        return letterPage.getContent();
    }

    @Override
    @Transactional
    public NangmanLetter getLetter(Long nangmanLetterId){
        return nangmanLetterRepository.findById(nangmanLetterId)
                .orElseThrow(() -> new RuntimeException("편지를 찾을 수 없습니다. ID: " + nangmanLetterId));
    }

    @Override
    @Transactional
    public NangmanLetterBoxResponseDTO.SelectedLetterResultDTO getLetterInfo(Long nangmanLetterId){
        NangmanLetter seledtedLetter = getLetter(nangmanLetterId);

        return NangmanLetterBoxConverter.toSelectedLetterResultDTO(seledtedLetter);
    }

    @Override
    @Transactional
    public  NangmanLetterBoxResponseDTO.WriteReplyResultDTO sendReply(NangmanLetterBoxRequestDTO.WriteReplyDTO request, Long nangmanLetterId){
        //멤버 엔티티 조회
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다. ID: " + request.getMemberId()));

        //사용자가 오늘 이미 답장을 작성했는지 확인
        boolean hasUserRepliedToday = hasUserRepliedToday(member.getId());

        if (hasUserRepliedToday) {
            throw new RuntimeException("오늘은 이미 답장을 작성했습니다.");
        }

        // 특정 편지에 대한 정보 조회
        NangmanLetter nangmanLetter = getLetter(nangmanLetterId);

        // 답장 db저장
        NangmanReply newNangmanReply = NangmanLetterBoxConverter.toNangmanReply(request, nangmanLetter, member);
        nangmanReplyRepository.save(newNangmanReply);

        //편지 답장 상태 업데이트
        nangmanLetter.receivedResponse();
        nangmanLetterRepository.save(nangmanLetter);

        return NangmanLetterBoxConverter.toWriteReplyResultDTO(newNangmanReply);

    }

    public boolean hasUserRepliedToday(Long memberId){
        LocalDate today = LocalDate.now();

        return nangmanReplyRepository.existsByMemberIdAndCreatedAtBetween(
                memberId,
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay().minusSeconds(1)
        );
    }
}
