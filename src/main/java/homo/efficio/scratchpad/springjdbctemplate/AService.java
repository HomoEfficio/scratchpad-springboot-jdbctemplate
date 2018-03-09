package homo.efficio.scratchpad.springjdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-03-09
 */
@Service
@Transactional
@Slf4j
public class AService {

    private ARepository aRepository;

    public AService(@Autowired ARepository aRepository) {
        this.aRepository = aRepository;
    }

    public int[] batchInsert(List<Member> members) {
//        for(Member member: members) {
//            log.warn(member.toString());
//        }
//
//        return members.size();

        int[] affectedRows = this.aRepository.batchUpdate(members);

        return affectedRows;
    }

    public int insertFromSelect() {
        return this.aRepository.transfer();
    }
}
