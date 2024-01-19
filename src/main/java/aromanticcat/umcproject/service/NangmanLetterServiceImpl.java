package aromanticcat.umcproject.service;

import aromanticcat.umcproject.dto.NangmanLetterDTO;
import aromanticcat.umcproject.entity.NangmanLetter;
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

    @Override
    public Long register(NangmanLetterDTO nangmanLetterDTO){

        NangmanLetter nangmanLetter = modelMapper.map(nangmanLetterDTO, NangmanLetter.class);

        Long id = nangmanLetterRepository.save(nangmanLetter).getId();

        return id;
    }

}
