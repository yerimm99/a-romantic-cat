package aromanticcat.umcproject.service.slowLetter;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.SlowLetter;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.SlowLetterRepository;
import aromanticcat.umcproject.web.dto.slowLetter.SlowLetterCalResponse;
import aromanticcat.umcproject.web.dto.slowLetter.SlowLetterGetResponse;
import aromanticcat.umcproject.web.dto.slowLetter.SlowLetterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<SlowLetterGetResponse> getSlowLetters(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElse(null);
        List<SlowLetter> letters = slowLetterRepository.findSlowLettersByMember(findMember).orElse(null);
        List<SlowLetterGetResponse> responses = new ArrayList<>();
        for (SlowLetter slowLetter : letters) {
            responses.add(SlowLetterGetResponse.from(slowLetter));
        }
        return responses;
    }

    public SlowLetter getSlowLetter(Long letterId) {
        SlowLetter findLetter = slowLetterRepository.findBySlowLetterId(letterId).orElse(null);

        return findLetter;
    }

    public List<SlowLetterCalResponse> getWriteSlowLetters(Long memberId, int month) {
        int year = LocalDate.now().getYear();
        int selectMonth = month;

        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);

        List<SlowLetter> letters = slowLetterRepository.findSlowLettersByDateBetweenAndMemberId(startOfMonth, endOfMonth, memberId).orElse(null);
        List<SlowLetterCalResponse> responses = new ArrayList<>();
        for (SlowLetter slowLetter : letters) {
            responses.add(SlowLetterCalResponse.from(slowLetter));
        }
        return responses;
    }
}
