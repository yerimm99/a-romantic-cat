package aromanticcat.umcproject.service;

import aromanticcat.umcproject.dto.NangmanLetterDTO;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NangmanLetterServiceTests {

    @Autowired
    private NangmanLetterService nangmanLetterService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testRegister(){
        System.out.println(nangmanLetterService.getClass().getName());

        Member member1 = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("Member not found"));

        NangmanLetterDTO nangmanLetterDTO = NangmanLetterDTO.builder()
                .content("낭만 편지 샘플2")
                .is_public(false)
                .member(member1)
                .sender_nickname("샘플 닉네임2")
                .build();

        Long id = nangmanLetterService.register(nangmanLetterDTO);

        System.out.println("id: " + id);
    }
}
