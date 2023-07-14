package kanban_board.repository;

import kanban_board.models.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Test
    void findByBoardTitle_should_return_list_of_Boards_with_Title() {
        List<Board> board = boardRepository.findByBoardTitle("Kanban Board");

    }
}