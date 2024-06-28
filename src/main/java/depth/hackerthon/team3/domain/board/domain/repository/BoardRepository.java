package depth.hackerthon.team3.domain.board.domain.repository;

import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser(User user);
}
