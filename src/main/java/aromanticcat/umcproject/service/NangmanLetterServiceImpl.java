package aromanticcat.umcproject.service;

import aromanticcat.umcproject.converter.NangmanPostBoxConverter;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.NangmanLetterRepository;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NangmanLetterServiceImpl implements NangmanLetterService{

    private final NangmanLetterRepository nangmanLetterRepository;
    private final MemberRepository memberRepository;
    private final RandomNicknameService randomNicknameService;

    @Override
    @Transactional
    public NangmanLetter writeAndSendLetter(NangmanPostBoxRequestDTO.SendLetterDTO request, String randomNickname){
        NangmanLetter newNangmanLetter = NangmanPostBoxConverter.toNangmanLetter(request);

        return nangmanLetterRepository.save(newNangmanLetter);
    }
//
//    @Override
//    public NangmanLetterDTO readOne(Long id){
//
//        Optional<NangmanLetter> result = nangmanLetterRepository.findById(id);
//
//        NangmanLetter nangmanLetter = result.orElseThrow();
//
//        NangmanLetterDTO nangmanLetterDTO = modelMapper.map(nangmanLetter, NangmanLetterDTO.class);
//
//        return nangmanLetterDTO;
//    }
//
//
//
//    //답장 받을 때 has_response 업데이트
//    @Override
//    public void receivedReply(NangmanLetterDTO nangmanLetterDTO) {
//        Optional<NangmanLetter> result = nangmanLetterRepository.findById(nangmanLetterDTO.getId());
//
//        NangmanLetter nangmanLetter = result.orElseThrow();
//
//        //has_response 값 업데이트
//        nangmanLetter.change(nangmanLetterDTO.getHas_response());
//
//        //is_public 값에 따라 낭만 모음집에 추가
//        if(nangmanLetter.getIs_public()){
//            addLetterToNangmanCollectoin(nangmanLetter);
//        }
//        nangmanLetterRepository.save(nangmanLetter);
//    }
//
//    private void addLetterToNangmanCollectoin(NangmanLetter nangmanLetter){
//        // 낭만 모음집에 해당 편지를 추가하는 로직을 구현
//        // 추가되는 방법은 해당 모음집의 컬렉션에 편지를 추가하거나 다른 적절한 방법으로 구현
//    }
}
