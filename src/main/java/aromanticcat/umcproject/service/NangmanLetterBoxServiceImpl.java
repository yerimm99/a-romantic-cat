package aromanticcat.umcproject.service;

import aromanticcat.umcproject.converter.NangmanLetterBoxConverter;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.NangmanLetterRepository;
import aromanticcat.umcproject.repository.NangmanReplyRepository;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        NangmanLetter newNangmanLetter = NangmanLetterBoxConverter.toNangmanLetter(request);

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
        NangmanLetter nangmanLetter = getLetterById(nangmanLetterId);
        NangmanReply newNangmanReply = NangmanLetterBoxConverter.toNangmanReply(request, nangmanLetter);

        return nangmanReplyRepository.save(newNangmanReply);
    }

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
