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
import java.util.stream.Collectors;

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

    // 사용자 ID로 해당 사용자가 작성한 편지 목록 조회
    @Override
    @Transactional
    public List<NangmanLetterBoxResponseDTO.PreviewLetterResultDTO> getMyLetterList(Long userId, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<NangmanLetter> myLetterPage = nangmanLetterRepository.findByMemberId(userId, pageable);

        List<NangmanLetter> myLetterList = myLetterPage.getContent();

        return myLetterList.stream().map( myLetter -> NangmanLetterBoxConverter.toPreviewLetterResultDTO(myLetter))
                .collect(Collectors.toList());

    }


    @Override
    @Transactional
    public NangmanLetterBoxResponseDTO.PreviewReplyResultDTO getPreviewReceivedReply(Long userId, Long nangmanLetterId){

        //특정 편지 조회
        NangmanLetter nangmanLetter = nangmanLetterRepository.findByMemberIdAndId(userId, nangmanLetterId);

        if(nangmanLetter == null) {
            throw new IllegalArgumentException("해당 사용자에게 권한이 없거나, 존재하지 않는 편지입니다.");
        }

        //특정 편지에 대한 답장이 있는지 확인
        if (nangmanLetter.getHasResponse()) {

            return NangmanLetterBoxConverter.toPreviewReplyResultDTO(nangmanLetter.getNangmanReply());
        } else {
            // 답장이 없는 경우 메세지를 담은 Optional.empty() 반환
            throw new IllegalArgumentException("아직 답장이 없습니다.");
        }

    }

    @Override
    @Transactional
    public  List<NangmanLetterBoxResponseDTO.PreviewBothResultDTO> getMyReplyList(Long userId, int page, int pageSize){

        Pageable pageable = PageRequest.of(page, pageSize);

        // 사용자가 답장한 목록 조회
        Page<NangmanReply> myReplyPage = nangmanReplyRepository.findByMemberId(userId, pageable);

        // 리스트로 변환
        List<NangmanReply> myReplyList = myReplyPage.getContent();

        // 각 답장(+ 연결된 편지)에 대해 미리보기로 생성
        return myReplyList.stream()
                .map(reply -> NangmanLetterBoxConverter.toPreviewBothResultDTO(reply.getNangmanLetter(), reply))
                .collect(Collectors.toList());
    }
}
