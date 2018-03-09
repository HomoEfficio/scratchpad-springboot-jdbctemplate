package homo.efficio.scratchpad.springjdbctemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-03-09
 */
@Getter
@Setter
@ToString
public class Member {

    public Member(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    private Long id;
    private String email;
    private String name;
}
