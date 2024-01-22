package aromanticcat.umcproject.web.dto.Friend;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class FriendResponseDTO {

    @Getter
    @Builder
    public static class FriendDTO{
        Long friend_id;
        String friend_name;
        boolean are_we_friend;
        boolean are_we_close;
    }
}
