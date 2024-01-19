package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.NangmanLetter;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
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

    @Test
    public void testSelectById(){
        Long id = 3L;

        Optional<NangmanLetter> result = nangmanLetterRepository.findById(id);
        NangmanLetter nangmanLetter = result.orElseThrow();

        // 프록시 초기화
        Hibernate.initialize(nangmanLetter.getMember().getNangmanLetters());

        // 영속성 컨텍스트에서 엔티티를 가져오는 것을 확인
        assertNotNull(nangmanLetter.getId());

        // 출력
        System.out.println(nangmanLetter);
    }

    @Test
    public void testUpdateHasResponse(){
        Long id = 1L;

        Optional<NangmanLetter> result = nangmanLetterRepository.findById(id);

        NangmanLetter nangmanLetter = result.orElseThrow();

        nangmanLetter.change(true);

        nangmanLetterRepository.save(nangmanLetter);
    }

    @Test
    public void testDeleteNangmanLetter(){
        Long id = 1L;

        nangmanLetterRepository.deleteById(id);
    }
}
