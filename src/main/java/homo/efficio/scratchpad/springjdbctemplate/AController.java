package homo.efficio.scratchpad.springjdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-03-09
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class AController {

    private final AService aService;

    public AController(@Autowired AService aService) {
        this.aService = aService;
    }

    @RequestMapping("/batch/transfer")
    public ResponseEntity<String> normalUpdate() {
        List<Member> members = getMembers();
        int affectedRows = this.aService.insertFromSelect();
        log.warn("int affectedRows: " + affectedRows);

        return ResponseEntity.ok("Hello : " + affectedRows);
    }

    @RequestMapping("/batch/insert")
    public ResponseEntity<String> batchUpdate() {
        List<Member> members = getMembers();
        int[] affectedRows = this.aService.batchInsert(members);
        log.warn("int[] affectedRows: " + affectedRows);

        return ResponseEntity.ok("Hello : " + affectedRows.length);
    }

    private List<Member> getMembers() {
        List<Member> members = new ArrayList<>();
        members.add(new Member(1L, "omwomw@sk.com", "세종대왕"));
        members.add(new Member(2L, "hanmomhanda@gmail.com", "뒤태지존"));
        members.add(new Member(3L, "onetouch@gazua.com", "가즈아아아"));
        return members;
    }
}
