package aromanticcat.umcproject.web.dto.MyDesign;

import aromanticcat.umcproject.entity.MyLetterPaper;
import aromanticcat.umcproject.entity.MyStamp;
import aromanticcat.umcproject.entity.SlowLetter;
import aromanticcat.umcproject.web.dto.slowLetter.SlowLetterGetResponse;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record MyDesignGetResponse(
        Long myDesignId,
        String imageUrl,
        String name
) {
    public static MyDesignGetResponse from(MyStamp myStamp) {
        return MyDesignGetResponse.builder()
                .myDesignId(myStamp.getMStamp_id())
                .imageUrl(myStamp.getImageUrl())
                .name(myStamp.getName())
                .build();
    }

    public static MyDesignGetResponse from(MyLetterPaper myLetterPaper) {
        return MyDesignGetResponse.builder()
                .myDesignId(myLetterPaper.getMPaper_id())
                .imageUrl(myLetterPaper.getImageUrl())
                .name(myLetterPaper.getName())
                .build();
    }
}