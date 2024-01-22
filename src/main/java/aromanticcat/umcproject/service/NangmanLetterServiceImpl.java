package aromanticcat.umcproject.service;

import aromanticcat.umcproject.web.dto.NangmanLetterDTO;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.NangmanLetterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NangmanLetterServiceImpl implements NangmanLetterService{

    private final ModelMapper modelMapper;
    private final NangmanLetterRepository nangmanLetterRepository;
    private final MemberRepository memberRepository;
    private final RandomNicknameService randomNicknameService;

    @Override
    public NangmanLetterDTO register(NangmanLetterDTO nangmanLetterDTO){

        // 랜덤 닉네임 생성
        String randomNickname = randomNicknameService.generateRandomNickname();

        // DTO에 랜덤 닉네임 설정
        nangmanLetterDTO.setSender_nickname(randomNickname);

        // DTO를 엔티티로 변환
        NangmanLetter nangmanLetter = modelMapper.map(nangmanLetterDTO, NangmanLetter.class);

        // 엔티티 저장
        Long id = nangmanLetterRepository.save(nangmanLetter).getId();

        nangmanLetterDTO.setId(id);

        return nangmanLetterDTO;
    }

    @Override
    public NangmanLetterDTO readOne(Long id){

        Optional<NangmanLetter> result = nangmanLetterRepository.findById(id);

        NangmanLetter nangmanLetter = result.orElseThrow();

        NangmanLetterDTO nangmanLetterDTO = modelMapper.map(nangmanLetter, NangmanLetterDTO.class);

        return nangmanLetterDTO;
    }



    //답장 받을 때 has_response 업데이트
    @Override
    public void receivedReply(NangmanLetterDTO nangmanLetterDTO) {
        Optional<NangmanLetter> result = nangmanLetterRepository.findById(nangmanLetterDTO.getId());

        NangmanLetter nangmanLetter = result.orElseThrow();

        //has_response 값 업데이트
        nangmanLetter.change(nangmanLetterDTO.getHas_response());

        //is_public 값에 따라 낭만 모음집에 추가
        if(nangmanLetter.getIs_public()){
            addLetterToNangmanCollectoin(nangmanLetter);
        }
        nangmanLetterRepository.save(nangmanLetter);
    }

    private void addLetterToNangmanCollectoin(NangmanLetter nangmanLetter){
        // 낭만 모음집에 해당 편지를 추가하는 로직을 구현
        // 추가되는 방법은 해당 모음집의 컬렉션에 편지를 추가하거나 다른 적절한 방법으로 구현
    }
}
