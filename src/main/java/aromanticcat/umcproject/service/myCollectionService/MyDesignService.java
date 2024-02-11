package aromanticcat.umcproject.service.myCollectionService;

import aromanticcat.umcproject.S3.S3Service;
import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.MyLetterPaper;
import aromanticcat.umcproject.entity.MyStamp;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.MyLetterPaperRepository;
import aromanticcat.umcproject.repository.MyStampRepository;
import aromanticcat.umcproject.web.dto.Letterbox.LetterboxResponse;
import aromanticcat.umcproject.web.dto.MyDesign.MyDesignGetResponse;
import aromanticcat.umcproject.web.dto.MyDesign.MyDesignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyDesignService {
    @Autowired
    private MyLetterPaperRepository myLetterPaperRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MyStampRepository myStampRepository;
    @Autowired
    private S3Service s3Service;

    public Long createMyLetterPaper(MyDesignRequest myDesignRequest, MultipartFile file) throws IOException {
        String url = s3Service.uploadFile1(file);
        Member member = memberRepository.findById(myDesignRequest.getMember_id()).orElseThrow(() -> new IllegalArgumentException("Member not found. id=" + myDesignRequest.getMember_id()));
        MyLetterPaper myLetterPaper = MyLetterPaper.builder()
                .name(myDesignRequest.getName())
                .imageUrl(url)
                .member(member)
                .build();
        MyLetterPaper saveMyLetterPaper = myLetterPaperRepository.save(myLetterPaper);
        return saveMyLetterPaper.getMPaper_id();
    }

    public Long createMyStamp(MyDesignRequest myDesignRequest, MultipartFile file) throws IOException {
        String url = s3Service.uploadFile2(file);
        Member member = memberRepository.findById(myDesignRequest.getMember_id()).orElseThrow(() -> new IllegalArgumentException("Member not found. id=" + myDesignRequest.getMember_id()));
        MyStamp myStamp = MyStamp.builder()
                .name(myDesignRequest.getName())
                .imageUrl(url)
                .member(member)
                .build();
        MyStamp saveMyStamp = myStampRepository.save(myStamp);
        return saveMyStamp.getMStamp_id();
    }

    public List<MyDesignGetResponse> getMyStamps(Long memberId) {
        Member findMEmber = memberRepository.findById(memberId).orElse(null);
        List<MyStamp> stamps = myStampRepository.findMyStampsByMember(findMEmber).orElse(null);

        List<MyDesignGetResponse> responses = new ArrayList<>();
        for (MyStamp stamp : stamps) {
            responses.add(MyDesignGetResponse.from(stamp));
        }
        return responses;
    }

    public List<MyDesignGetResponse> getMyLetterPapers(Long memberId) {
        Member findMEmber = memberRepository.findById(memberId).orElse(null);
        List<MyLetterPaper> myLetterPapers = myLetterPaperRepository.findMyLetterPapersByMember(findMEmber).orElse(null);

        List<MyDesignGetResponse> responses = new ArrayList<>();
        for (MyLetterPaper myLetterPaper : myLetterPapers) {
            responses.add(MyDesignGetResponse.from(myLetterPaper));
        }
        return responses;
    }
}
