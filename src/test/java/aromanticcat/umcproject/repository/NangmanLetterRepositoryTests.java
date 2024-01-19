package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.NangmanLetter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NangmanLetterRepositoryTests {

    @Autowired
    private NangmanLetterRepository nangmanLetterRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void testInsertNangmanLetter(){

        Member member1 = Member.builder()
                .email("test@email.com")
                .nickname("member1")
                .coin(0)
                .build();
        memberRepository.save(member1);

        NangmanLetter nangmanLetter = NangmanLetter.builder()
                .is_public(false)
                .sender_nickname("낭만적인 고양이")
                .content("제 고민을 들어주세요")
                .member(member1)
                .build();
        NangmanLetter result = nangmanLetterRepository.save(nangmanLetter);

        System.out.println(result);
    }
}
