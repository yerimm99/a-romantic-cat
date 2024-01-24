package aromanticcat.umcproject.web.dto.Friend;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class FriendResponseDTO {

    @Getter
    @Builder
    public static class FriendDTO{
        Long friendId;
        String friendName;
        boolean isFriend;
        boolean isCloseFriend;
    }
}
