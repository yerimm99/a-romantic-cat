package aromanticcat.umcproject.web.dto;

import aromanticcat.umcproject.entity.Member;
import lombok.Getter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
public class MyDesignRequest {
    @NotNull
    String name;

    @NotNull
    Long member_id;
}
