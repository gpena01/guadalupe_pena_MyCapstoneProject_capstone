package kanban_board.service;

import kanban_board.models.Board;
import kanban_board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }
    public Board getBoardById(long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return board;
        }
        throw new BoardNotFoundException();
    }
    public void deleteBoardById(long boardId) {
        boardRepository.deleteById(boardId);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
}
