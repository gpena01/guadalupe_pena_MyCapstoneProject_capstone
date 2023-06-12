package kanban_board.service;

import kanban_board.models.Board;
import kanban_board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }
    public Board updateBoard(Board updatedBoard) {
        Board existingBoard = boardRepository.findById(updatedBoard.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        existingBoard.setBoardTitle(updatedBoard.getBoardTitle());
        return boardRepository.save(existingBoard);
    }
    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
}
