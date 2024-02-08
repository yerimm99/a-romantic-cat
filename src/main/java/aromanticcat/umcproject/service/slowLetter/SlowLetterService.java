package aromanticcat.umcproject.service.slowLetter;

import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.SlowLetter;
import aromanticcat.umcproject.repository.LetterBoxRepository;
import aromanticcat.umcproject.repository.LetterRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.SlowLetterRepository;
import aromanticcat.umcproject.web.dto.Letterbox.LetterboxRequest;
import aromanticcat.umcproject.web.dto.SlowLetterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SlowLetterService {
    private final SlowLetterRepository slowLetterRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SlowLetter save(Long memberId, SlowLetterRequest request) {
        SlowLetter slowLetter = SlowLetterRequest.toEntity(request);
        Member member = memberRepository.findById(memberId).orElse(null);
        slowLetter.updateMember(member);
        return slowLetterRepository.save(slowLetter);
    }
}
