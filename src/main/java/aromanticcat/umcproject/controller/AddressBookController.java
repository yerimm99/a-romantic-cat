package aromanticcat.umcproject.controller;


import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.service.AdressBookService.AddressBookQueryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address-book")
@RequiredArgsConstructor
public class AddressBookController {

    private final AddressBookQueryService addressBookQueryService;

//    @GetMapping("/")
//    @ApiOperation("주소록 조회")
//    public ApiResponse<>


}
