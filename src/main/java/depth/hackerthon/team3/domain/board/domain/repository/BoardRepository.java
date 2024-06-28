package depth.hackerthon.team3.domain.board.domain.repository;

import depth.hackerthon.team3.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}