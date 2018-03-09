package homo.efficio.scratchpad.springjdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-03-09
 */
@Repository
@Slf4j
public class ARepository {

    private final JdbcTemplate jdbcTemplate;

    public ARepository(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int transfer() {
        int insertedRows = this.jdbcTemplate.update(
                "INSERT INTO member_impala (id, email, name) " +
                        "SELECT id, email, name " +
                        "FROM member " +
                        "WHERE 1=1 " +
                        "AND length(email) > ? " +
                        "AND length(name) > ?",
                ps -> {
                    ps.setInt(1, 3);
                    ps.setInt(2, 2);
                }
        );
        return insertedRows;
    }


    public int[] batchUpdate(List<Member> members) {
        // int[] 인 이유는 n 번의 쿼리 수행으로 영향받는 레코드 수를 표현하기 위함
        // {1, 3, 2, 8, 9} 이면 쿼리를 5번 수행했는데,
        // 1번쨰 쿼리 결과는 1, 2번째 쿼리 결과는 3, 3번째 쿼리 결과는 2, ...
        int[] insertedRows = this.jdbcTemplate.batchUpdate(
                "INSERT INTO member (id, email, name) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        log.warn("In BatchPreparedStatementSetter, i: {}", i);
                        Member member = members.get(i);
                        ps.setString(2, member.getEmail());
                        ps.setString(3, member.getName());
                    }

                    @Override
                    public int getBatchSize() {
                        return members.size();
                    }
                }
        );
        return insertedRows;
    }
}
