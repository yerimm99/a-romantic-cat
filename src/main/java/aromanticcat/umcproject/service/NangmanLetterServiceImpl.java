package aromanticcat.umcproject.service;

import aromanticcat.umcproject.dto.NangmanLetterDTO;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.NangmanLetterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NangmanLetterServiceImpl implements NangmanLetterService{

    private final ModelMapper modelMapper;
    private final NangmanLetterRepository nangmanLetterRepository;
    private final MemberRepository memberRepository;
    private final RandomNicknameService randomNicknameService;

    @Override
    public Long register(NangmanLetterDTO nangmanLetterDTO){

        //랜덤 닉네임 생성
        String randomNickname = randomNicknameService.generateRandomNickname();

        //DTO에 랜덤 닉네임 설정
        nangmanLetterDTO.setSender_nickname(randomNickname);

        //DTO를 엔티티로 변환
        NangmanLetter nangmanLetter = modelMapper.map(nangmanLetterDTO, NangmanLetter.class);

        //엔티티 저장
        Long id = nangmanLetterRepository.save(nangmanLetter).getId();

        return id;
    }

}
