package aromanticcat.umcproject.service;

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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NangmanLetterBoxServiceImpl implements NangmanLetterBoxService {

    private final NangmanLetterRepository nangmanLetterRepository;
    private final MemberRepository memberRepository;
    private final RandomNicknameService randomNicknameService;
    private final NangmanReplyRepository nangmanReplyRepository;
    @Override
    @Transactional
    public NangmanLetter writeAndSendLetter(NangmanLetterBoxRequestDTO.WriteLetterDTO request){
        //멤버 엔티티 조회
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다. ID: " + request.getMemberId()));

        NangmanLetter newNangmanLetter = NangmanLetterBoxConverter.toNangmanLetter(request, member);

        return nangmanLetterRepository.save(newNangmanLetter);
    }

    @Override
    @Transactional
    public List<NangmanLetter> getLetterList(){

        return nangmanLetterRepository.findByHasResponseFalse();
    }

    @Override
    @Transactional
    public NangmanLetter getLetterById(Long nangmanLetterId){
        return nangmanLetterRepository.findById(nangmanLetterId)
                .orElseThrow(() -> new RuntimeException("편지를 찾을 수 없습니다. ID: " + nangmanLetterId));
    }

    @Override
    @Transactional
    public NangmanReply writeAndSendReply(NangmanLetterBoxRequestDTO.WriteReplyDTO request, Long nangmanLetterId){
        //사용자가 오늘 이미 답장을 작성했는지 확인
        boolean hasUserRepliedToday = hasUserRepliedToday(request.getMemberId());

        if (hasUserRepliedToday) {
            throw new RuntimeException("오늘은 이미 답장을 작성했습니다.");
        }

        //특정 편지에 대한 정보 조회
        NangmanLetter nangmanLetter = getLetterById(nangmanLetterId);

        //멤버 엔티티 조회
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다. ID: " + request.getMemberId()));

        //답장 작성 및 발송
        NangmanReply newNangmanReply = NangmanLetterBoxConverter.toNangmanReply(request, nangmanLetter, member);

        //편지 답장 상태 업데이트
        nangmanLetter.setHasResponse(true);

        return nangmanReplyRepository.save(newNangmanReply);

    }

    public boolean hasUserRepliedToday(Long memberId){
        LocalDate today = LocalDate.now();

        return nangmanReplyRepository.existsByMemberIdAndCreatedAtBetween(
                memberId,
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay().minusSeconds(1)
        );
    }

    @Override
    @Transactional
    public List<NangmanLetter> getNangmanLettersByUserId(Long userId){
        // 사용자 ID로 해당 사용자가 작성한 편지 목록 조회
        return nangmanLetterRepository.findByMemberId(userId);
    }

    @Override
    @Transactional
    public NangmanLetterBoxResponseDTO.PreviewReplyResultDTO getPreviewReplyForLetter(Long userId, Long nangmanLetterId) {
        // 특정 편지에 대한 답장 조회
        Optional<NangmanReply> replyOptional = getReplyForLetter(userId, nangmanLetterId);

        // 답장이 존재할 경우에만 프리뷰로 변환
        return replyOptional.map(NangmanLetterBoxConverter::toPreviewReplyResultDTO)
                .orElseGet(() -> NangmanLetterBoxResponseDTO.PreviewReplyResultDTO.builder().noReply(true).build());
    }


    @Override
    @Transactional
    public Optional<NangmanReply> getReplyForLetter(Long userId, Long nangmanLetterId){

        //특정 편지 조회
        Optional<NangmanLetter> nangmanLetterOptional = nangmanLetterRepository.findByMemberIdAndId(userId, nangmanLetterId);

        if(nangmanLetterOptional.isPresent()){
            NangmanLetter nangmanLetter = nangmanLetterOptional.get();

            //특정 편지에 대한 답장이 있는지 확인
            if (nangmanLetter.getHasResponse()) {
                // 답장이 있는 경우 해당 답장 반환
                return Optional.ofNullable(nangmanLetter.getNangmanReply());
            } else {
                // 답장이 없는 경우 메세지를 담은 Optional.empty() 반환
                return Optional.empty();
            }
        } else {
            // 특정 편지가 존재하지 않는 경우 에러 처리
            throw new IllegalArgumentException("해당 사용자에게 권한이 없거나, 존재하지 않는 편지입니다.");
        }
    }
}
