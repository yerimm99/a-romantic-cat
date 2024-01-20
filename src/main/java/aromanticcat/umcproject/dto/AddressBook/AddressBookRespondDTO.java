package aromanticcat.umcproject.dto.AddressBook;

import lombok.Getter;

public class AddressBookRespondDTO {

    @Getter
    public static class friendListDTO{

//        List<friendDTO> friends;

    }

    @Getter
    public static class friendDTO{

        Long friend_id;

        String friend_name;

        boolean are_we_friend;

        boolean are_we_close;

    }
}
